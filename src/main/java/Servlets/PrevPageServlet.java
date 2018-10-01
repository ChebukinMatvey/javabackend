package Servlets;

import Items.Content;
import Items.IPhone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="PrevPageServlet", urlPatterns = "/prev")
public class PrevPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Content content= (Content) req.getSession().getAttribute("Content");
        IPhone first=null;
        IPhone second=null;
        if(content.isPrevSelected()) {
            content.selectPrevItems();
            first=content.getFirstItem();
            second=content.getSecondItem();
        }
        req.getSession().setAttribute("Content",content);
        resp.getWriter().write(SendJson.Send(first,second));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
