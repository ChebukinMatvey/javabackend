package com.nokinobi.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nokinobi.items.*;
import com.nokinobi.services.ItemsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
	
	
	@Autowired
	private ItemsService itemsService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Content content=Content.init(itemsService.getItems());


        req.getSession().setAttribute("firstName",content.getFirstItem().getName());
        req.getSession().setAttribute("firstImg",content.getFirstItem().getImgStr());
        req.getSession().setAttribute("firstPrice",content.getFirstItem().getPrice());
        req.getSession().setAttribute("firstCapacity",content.getFirstItem().getCapacity());

        req.getSession().setAttribute("secondName",content.getSecondItem().getName());
        req.getSession().setAttribute("secondImg",content.getSecondItem().getImgStr());
        req.getSession().setAttribute("secondPrice",content.getSecondItem().getPrice());
        req.getSession().setAttribute("secondCapacity",content.getSecondItem().getCapacity());
        req.getSession().setAttribute("Content",content);
        req.getRequestDispatcher("/pages/index.jsp").forward(req,resp);
    }
}
