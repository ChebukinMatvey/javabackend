package com.nokinobi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.nokinobi.items.Content;
import com.nokinobi.items.IPhone;

@Controller
@SessionAttributes(value= Attributes.ContentAttribute)
public class PagesController {

	@Autowired
	private Gson son;
	
	@RequestMapping(value="/next.do", method=RequestMethod.POST)
	public ResponseEntity<String> next(@ModelAttribute(Attributes.ContentAttribute)Content content, Model model) {
		IPhone first=null;
        IPhone second=null;
        if(content.isNextSelected()) {
            content.selectNextItems();
            first=content.getFirstItem();
            second=content.getSecondItem();
        }
        model.addAttribute(Attributes.ContentAttribute,content);
        return new ResponseEntity<String>(son.toJson(new IPhone[]{first,second}),HttpStatus.OK);
	}
	
	@RequestMapping(value="/prev.do",method=RequestMethod.POST)
	public ResponseEntity<String> prev(@ModelAttribute(Attributes.ContentAttribute)Content content, Model model) {
		IPhone first=null;
        IPhone second=null;
        if(content.isPrevSelected()) {
            content.selectPrevItems();
            first=content.getFirstItem();
            second=content.getSecondItem();
        }
        model.addAttribute(Attributes.ContentAttribute,content);
        return new ResponseEntity<String>(son.toJson(new IPhone[]{first,second}),HttpStatus.OK);
	}
	
}
