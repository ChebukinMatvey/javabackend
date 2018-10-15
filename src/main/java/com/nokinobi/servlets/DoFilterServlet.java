package com.nokinobi.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nokinobi.items.Content;
import com.nokinobi.items.Filter;
import com.nokinobi.services.ItemsService;



@WebServlet(name = "DoFilterServlet", urlPatterns = "/doFilter")
public class DoFilterServlet extends HttpServlet {
	
	
	
	@Autowired
	private ItemsService itemsService;
    
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFilter=req.getParameter("name");
        String priceMax=req.getParameter("max");
        String priceMin=req.getParameter("min");
        String capacity=req.getParameter("capacity");
        Filter filter=new Filter(nameFilter,priceMax,priceMin,capacity);


        Content content = Content.init(itemsService.getItems(filter));

        req.getSession().setAttribute("Content",content);

        resp.getWriter().write(SendJson.Send(content.getFirstItem(),content.getSecondItem()));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
