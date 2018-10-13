package servlets;

import items.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ContentStatements;
import database.DatabaseOperation;
import database.Handlers;

import java.io.IOException;
import java.util.ArrayList;



@WebServlet(name = "DoFilterServlet", urlPatterns = "/doFilter")
public class DoFilterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFilter=req.getParameter("name");
        String priceMax=req.getParameter("max");
        String priceMin=req.getParameter("min");
        String capacity=req.getParameter("capacity");
        Filter filter=new Filter(nameFilter,priceMax,priceMin,capacity);


        Content content = Content.init(DatabaseOperation.ExecuteQuery(ContentStatements.GetFilteredItems(filter),Handlers.<ArrayList<IPhone>>getAllItemsHandler()));

        req.getSession().setAttribute("Content",content);

        resp.getWriter().write(SendJson.Send(content.getFirstItem(),content.getSecondItem()));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
