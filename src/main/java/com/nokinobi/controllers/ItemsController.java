package com.nokinobi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nokinobi.items.Cart;
import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;
import com.nokinobi.services.OrderService;
import org.w3c.dom.Attr;

@Controller
@SessionAttributes(value = { Attributes.Cart,Attributes.SuccessAttribute,Attributes.ErrorAttribute})
public class ItemsController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/buy.do")
	public String addToCart(@RequestParam(value = Attributes.ItemName) String name,
							@RequestParam(value = Attributes.ItemCapacity) String capacity,
							@RequestParam(value = Attributes.ItemPrice) String price,
							@RequestParam(value = Attributes.ItemImgStr) String imgStr, HttpSession session,
							@ModelAttribute(value=Attributes.Cart) Cart cart,
							Model model) {

		IPhone phone = new IPhone(name, imgStr, Integer.parseInt(price), Integer.parseInt(capacity));
		if (cart == null)
			cart = new Cart();
		cart.add(phone);
		model.addAttribute(Attributes.Cart,cart);
		model.addAttribute(Attributes.SuccessAttribute,ResponseStrings.SuccessBuy);
		return "success";
	}

	@RequestMapping(value = "/delete.do")
	public String deleteFromCart(@RequestParam(value = Attributes.ItemName) String name,
								 @RequestParam(value = Attributes.ItemCapacity) String capacity,
								 @RequestParam(value = Attributes.ItemPrice) String price,
								 @ModelAttribute(value = Attributes.Cart) Cart cart,
								 Model model) {
		IPhone phone = new IPhone(name, Integer.parseInt(capacity), Integer.parseInt(price));
		cart.remove(phone);
		model.addAttribute(Attributes.Cart,cart);
		return "cart";
	}

	@RequestMapping(value = "/order.do")
	public String order(@RequestParam(value = Attributes.UserAddress) String adr,
						@RequestParam(value = Attributes.UserEmail) String email,
						@ModelAttribute(value = Attributes.Cart)Cart cart,
						@ModelAttribute(value = Attributes.UserAttribute)User user,
						Model model) {

		int res=orderService.addOrder(cart, user, adr, email);
		if(res>0) {
			model.addAttribute(Attributes.Cart, new Cart());
			model.addAttribute(Attributes.SuccessAttribute, ResponseStrings.SuccesOrder);
			return "success";
		}
		else {
			model.addAttribute(Attributes.ErrorAttribute, ResponseStrings.OrderError);
			return "error";
		}
	}

}
