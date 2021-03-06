package com.nokinobi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nokinobi.items.*;
import com.nokinobi.controllers.ResponseStrings;


public class AccessFilter implements Filter {

    private Content content;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        User user= (User) req.getSession().getAttribute("User");
        if(user.getLogin()==null){
            req.getSession().setAttribute("ErrorAttribute",ResponseStrings.BuyError);
            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);
        }
        else
            filterChain.doFilter(req,resp);
    }

    public void destroy() {

    }
}
