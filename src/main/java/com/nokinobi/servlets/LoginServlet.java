package com.nokinobi.servlets;import javax.servlet.ServletConfig;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.context.support.SpringBeanAutowiringSupport;import com.nokinobi.items.User;import com.nokinobi.services.UserService;import java.io.IOException;@WebServlet(name = "LoginServlet", urlPatterns = "/login")public class LoginServlet  extends HttpServlet {	@Autowired	private UserService userService;		@Override	public void init(ServletConfig config) throws ServletException {		super.init(config);		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);	}	    @Override    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        doPost(req,resp);            }    @Override    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        String login = req.getParameter("login");        String pass = req.getParameter("pass");        User user = new User(login, pass);        //boolean exists= DatabaseOperation.<Boolean>ExecuteQuery(UserStatements.FindUserStatement(user),Handlers.<Boolean>FindUserHandler());        user=userService.find(user);                if(user!=null){            req.getSession().setAttribute("User",user);            req.getRequestDispatcher("/pages/index.jsp").forward(req,resp);        }        else{            req.getSession().setAttribute("errorText",ResponseStrings.UndefUser);            req.getRequestDispatcher("/pages/error.jsp").forward(req,resp);        }    }}