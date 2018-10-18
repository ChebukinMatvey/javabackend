package com.nokinobi.controllers;

import javax.servlet.http.HttpSession;

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
import com.nokinobi.items.Content;
import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;
import com.nokinobi.services.ItemsService;
import com.nokinobi.services.UserService;
import com.nokinobi.controllers.ResponseStrings;

@Controller
@SessionAttributes(value = { Constants.ContentAttribute, Constants.UserAttribute, Constants.errorText })
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
		model.addAttribute(Constants.ContentAttribute, content);
		return "index";
	}

	@RequestMapping(value = "/filter.do", method = RequestMethod.POST)
	public ResponseEntity<String> doFilter(@RequestParam("filterJson") String filterJson, Model model) {
		Filter filter = son.fromJson(filterJson, Filter.class);
		Content content = Content.init(itemsService.getItems(filter));
		model.addAttribute(Constants.ContentAttribute, content);
		return new ResponseEntity<String>(son.toJson(new IPhone[] { content.getFirstItem(), content.getSecondItem() }),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/login.do")
	public String login(@RequestParam(value = Constants.LoginAttribute) String login,
			@RequestParam(value = Constants.PasswordAttribute) String pass, Model model) {
		User user = new User(login, pass);
		user = userService.find(user);
		if (user == null) {
			model.addAttribute(Constants.errorText, ResponseStrings.UndefUser);
			return "error";
		} else {
			model.addAttribute(Constants.UserAttribute, user);
			return "redirect:items.do";
		}
	}

	@RequestMapping(value = "/register.do")
	public String register(@RequestParam(value = Constants.LoginAttribute) String login,
			@RequestParam(value = Constants.PasswordAttribute) String pass, Model model) {
		User user = new User(login, pass);
		int res = userService.add(user);
		if (res > 0) {
			model.addAttribute(Constants.UserAttribute, user);
			return "redirect:items.do";
		} else {
			model.addAttribute(Constants.errorText, ResponseStrings.ErrorWhileRegister);
			return "error";
		}

	}

	@RequestMapping(value = "/logout.do")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:items.do";
	}

}
