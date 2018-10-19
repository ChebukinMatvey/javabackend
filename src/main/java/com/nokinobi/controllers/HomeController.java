package com.nokinobi.controllers;

import com.nokinobi.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.nokinobi.services.ItemsService;
import com.nokinobi.services.UserService;
import org.w3c.dom.Attr;

@Controller
@SessionAttributes(value = { Attributes.ContentAttribute, Attributes.UserAttribute, Attributes.SuccessAttribute, Attributes.ErrorAttribute,Attributes.Cart})
public class HomeController {

	@Autowired
	private Gson son;

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/items.do", method = RequestMethod.GET)
	public String getItems(Model model) {
		Content content = Content.init(itemsService.getItems());
		model.addAttribute(Attributes.ContentAttribute, content);
		return "index";
	}

	@RequestMapping(value = "/filter.do", method = RequestMethod.POST)
	public ResponseEntity<String> doFilter(@RequestParam("filterJson") String filterJson, Model model) {
		Filter filter = son.fromJson(filterJson, Filter.class);
		Content content = Content.init(itemsService.getItems(filter));
		model.addAttribute(Attributes.ContentAttribute, content);
		return new ResponseEntity<String>(son.toJson(new IPhone[] { content.getFirstItem(), content.getSecondItem() }),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/login.do")
	public String login(@RequestParam(value = Attributes.LoginAttribute) String login,
						@RequestParam(value = Attributes.PasswordAttribute) String pass, Model model) {
		User user = new User(login, pass);
		user = userService.find(user);
		if (user.getLogin() == null) {
			model.addAttribute(Attributes.ErrorAttribute, ResponseStrings.UndefUser);
			return "error";
		} else {
			model.addAttribute(Attributes.UserAttribute, user);
			model.addAttribute(Attributes.Cart,new Cart());
			return "redirect:items.do";
		}
	}

	@RequestMapping(value = "/register.do")
	public String register(@RequestParam(value = Attributes.LoginAttribute) String login,
						   @RequestParam(value = Attributes.PasswordAttribute) String pass, Model model) {
		User user = new User(login, pass);
		int res = userService.add(user);
		if (res > 0) {
			model.addAttribute(Attributes.UserAttribute, user);
			return "redirect:items.do";
		} else {
			model.addAttribute(Attributes.ErrorAttribute, ResponseStrings.ErrorWhileRegister);
			return "error";
		}

	}

	@RequestMapping(value = "/logout.do")
	public String logout(Model model) {
		model.addAttribute(Attributes.UserAttribute,new User());
		return "redirect:items.do";
	}

}
