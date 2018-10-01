package Servlets;import Database.*;import Items.User;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;@WebServlet(name = "LoginServlet", urlPatterns = "/login")public class LoginServlet  extends HttpServlet {    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        doPost(req,resp);    }    @Override    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        String login = req.getParameter("login");        String pass = req.getParameter("pass");        User user = new User(login, pass);        boolean exists= DatabaseOperation.<Boolean>ExecuteQuery(UserStatements.FindUserStatement(user),Handlers.<Boolean>FindUserHandler());        if(exists){            req.getSession().setAttribute("User",user);            req.getRequestDispatcher("/pages/index.jsp").forward(req,resp);        }        else{            req.getSession().setAttribute("errorText",ResponseStrings.UndefUser);            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);        }    }}