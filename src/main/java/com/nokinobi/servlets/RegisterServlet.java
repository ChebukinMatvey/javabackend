package com.nokinobi.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nokinobi.database.*;
import com.nokinobi.items.*;

import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user = new User(login, pass);

        int res= DatabaseOperation.ExecuteUpdate(UserStatements.InsertUser(user));

        if(res>0){
            req.getSession().setAttribute("User",user);
            req.getRequestDispatcher("/pages/index.jsp").forward(req,resp);
        }
        else{
            req.getSession().setAttribute("errorText",ResponseStrings.ErrorWhileRegister);
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
    }
}
