package com.mk.controller;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.mk.dao.UserDao;
 

@WebServlet("/processLogin")
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProcessLogin() {
        super();
        
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		
		UserDao dao=new UserDao();
		RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
		try {
			boolean authenticate=dao.authenticate(username, password);
			if(authenticate) {
				session.setAttribute("username", username);
				rd.forward(request, response);
				
			}
		} catch (Exception e) {
			out.println("Your request cannot be processed at this time, please try later.");
		}
	}
 
}