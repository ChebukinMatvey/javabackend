package Filters;

import Database.ContentStatements;
import Database.DatabaseOperation;
import Database.Handlers;
import Items.Content;
import Items.IPhone;
import Items.User;
import Servlets.ResponseStrings;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AccessFilter implements Filter {

    private Content content;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        User user= (User) req.getSession().getAttribute("User");
        if(user==null){
            req.getSession().setAttribute("errorText",ResponseStrings.BuyError);
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        else
            filterChain.doFilter(req,resp);
    }

    public void destroy() {

    }
}
