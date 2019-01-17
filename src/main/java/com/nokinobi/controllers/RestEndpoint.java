package com.nokinobi.controllers;


import com.nokinobi.items.IPhone;
import com.nokinobi.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping("api/items")
public class RestEndpoint {

    @Autowired
    private ItemsService itemsService;

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET,produces = "application/json")
    public List<IPhone> getAllItems(){
        return itemsService.getItems();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    public IPhone getItemById(@PathVariable int id){
        return itemsService.getItems().stream().filter((item) -> item.getId() == id).collect(Collectors.toList()).get(0);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET  )
    public List<IPhone> getFiltereItems(@RequestParam("min") int min){
        Stream<IPhone> items = itemsService.getItems().stream().filter((item) -> item.getPrice() > min);
        return  items.collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public IPhone createItem(@RequestBody IPhone item ){
        itemsService.addItem(item);
        return item;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}", produces = "application/json")
    public IPhone updateItem(@RequestBody IPhone item,@PathVariable int id ){
        item.setId(id);
        return itemsService.updateItem(item);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public String deleteItem(@PathVariable int id  ){
        IPhone itemToRemove;
        try {
             itemToRemove= itemsService.getItemById(id);
        }catch (NoResultException ex){
            return "No entity";
        }
        itemsService.deleteItem(itemToRemove);
        return "success";
    }

}
