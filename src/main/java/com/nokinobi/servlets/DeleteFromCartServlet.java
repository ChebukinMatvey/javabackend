package com.nokinobi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nokinobi.items.Cart;
import com.nokinobi.items.IPhone;



import java.io.IOException;

@WebServlet(name = "DeleteFromCartServlet" ,urlPatterns = "/deletefromcart")
public class DeleteFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IPhone p=new IPhone(req.getParameter("name"),
                            Integer.parseInt(req.getParameter("capacity")),
                            Integer.parseInt(req.getParameter("price")));

        Cart cart= (Cart) req.getSession().getAttribute("Cart");
        cart.remove(p);
        req.getSession().setAttribute("Cart",cart);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
