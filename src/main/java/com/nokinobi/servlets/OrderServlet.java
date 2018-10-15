package com.nokinobi.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nokinobi.items.*;
import com.nokinobi.services.OrderService;

import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    
	@Autowired
	private OrderService orderService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("Cart");
        User user = (User) request.getSession().getAttribute("User");
        String adr=request.getParameter("adress");
        String email=request.getParameter("email");

        int res = orderService.addOrder(cart, user, adr, email);

        if (res >0) {
            request.getSession().setAttribute("Cart", new Cart());
            request.getSession().setAttribute("successText",ResponseStrings.SuccesOrder);
            response.sendRedirect("/pages/success.jsp");
        } else {
            request.setAttribute("errorString",ResponseStrings.OrderError);
            response.sendRedirect("/pages/error.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

