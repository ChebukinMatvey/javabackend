package com.nokinobi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nokinobi.items.*;


import java.io.IOException;


@WebServlet(name="NextPageServlet", urlPatterns = "/next")
public class NextPageServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Content content= (Content) req.getSession().getAttribute("Content");
        IPhone first=null;
        IPhone second=null;
        if(content.isNextSelected()) {
            content.selectNextItems();
            first=content.getFirstItem();
            second=content.getSecondItem();
        }
        resp.setContentType("application/json");
        req.getSession().setAttribute("Content",content);
        resp.getWriter().write(SendJson.Send(first,second));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
