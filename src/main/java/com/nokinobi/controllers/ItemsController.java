package com.nokinobi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nokinobi.items.Cart;
import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;
import com.nokinobi.services.OrderService;

@Controller
@SessionAttributes(value = { Constants.Cart })
public class ItemsController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/buy.do")
	public String addToCart(@RequestParam(value = Constants.ItemName) String name,
			@RequestParam(value = Constants.ItemCapacity) String capacity,
			@RequestParam(value = Constants.ItemPrice) String price,
			@RequestParam(value = Constants.ItemImgStr) String imgStr, HttpSession session) {
		IPhone phone = new IPhone(name, imgStr, Integer.parseInt(price), Integer.parseInt(capacity));
		Cart cart = (Cart) session.getAttribute(Constants.Cart);
		if (cart == null)
			cart = new Cart();
		cart.add(phone);
		session.setAttribute(Constants.Cart, cart);
		session.setAttribute(Constants.successText, ResponseStrings.SuccessBuy);
		return "success";
	}

	@RequestMapping(value = "/delete.do")
	public String deleteFromCart(@RequestParam(value = Constants.ItemName) String name,
			@RequestParam(value = Constants.ItemCapacity) String capacity,
			@RequestParam(value = Constants.ItemPrice) String price, HttpSession session) {
		IPhone phone = new IPhone(name, Integer.parseInt(capacity), Integer.parseInt(price));
		Cart cart = (Cart) session.getAttribute(Constants.Cart);
		cart.remove(phone);
		session.setAttribute(Constants.Cart, cart);
		return "cart";
	}

	@RequestMapping(value = "/order.do")
	public String order(@RequestParam(value = Constants.UserAddress) String adr,@RequestParam(value = Constants.UserEmail) String email,HttpSession session) {
		User user = (User) session.getAttribute(Constants.UserAttribute);
		Cart cart = (Cart) session.getAttribute(Constants.Cart);
		int res=orderService.addOrder(cart, user, adr, email);
		if(res>0) {
			session.setAttribute(Constants.Cart, new Cart());
			session.setAttribute(Constants.successText, ResponseStrings.SuccesOrder);
			return "success";
		}
		else {
			session.setAttribute(Constants.errorText, ResponseStrings.OrderError);
			return "error";
		}
	}

}
