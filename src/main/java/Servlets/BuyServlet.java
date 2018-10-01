package Servlets;

import Items.Cart;
import Items.IPhone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BuyServlet", urlPatterns = "/buy")
public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name= req.getParameter("name");
        String capacity = req.getParameter("capacity");
        String price = req.getParameter("price");
        String imgStr=req.getParameter("imgStr");

        IPhone ip=new IPhone(name,imgStr,Integer.parseInt(price),Integer.parseInt(capacity));

        Cart cart= (Cart) req.getSession().getAttribute("Cart");
        if(cart==null)
            cart=new Cart();
        cart.add(ip);

        req.getSession().setAttribute("Cart",cart);
        req.getSession().setAttribute("successText",ResponseStrings.SuccessBuy);
        resp.sendRedirect("/pages/success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
