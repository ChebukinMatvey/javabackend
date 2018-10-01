package Servlets;

import Database.ContentStatements;
import Database.DatabaseOperation;
import Database.Handlers;
import Items.Content;
import Items.IPhone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Content content=Content.init(DatabaseOperation.ExecuteQuery(ContentStatements.GetAllItems(),Handlers.<ArrayList<IPhone>>getAllItemsHandler()));


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
