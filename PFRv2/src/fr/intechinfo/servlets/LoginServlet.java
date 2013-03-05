package fr.intechinfo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mycompany.dao.exceptions.DAOException;
import org.mycompany.dao.factories.DAOFactory;
import org.mycompany.dao.interfaces.IUserDAO;
import org.mycompany.dao.models.Poll;
import org.mycompany.dao.models.User;

import fr.intechinfo.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public LoginServlet()
    {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService a = new UserService();
	
	//	if(a.findUser(username, password) != null)
		try
		{
			if(a.findUser(username, password)!= null)
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				response.sendRedirect("PollsServlet");
			}
			else 
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Erreur");
			}
		}
		catch (DAOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}