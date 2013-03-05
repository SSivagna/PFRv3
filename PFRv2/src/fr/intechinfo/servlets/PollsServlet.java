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
import org.mycompany.dao.interfaces.IPollDAO;
import org.mycompany.dao.interfaces.IUserDAO;
import org.mycompany.dao.interfaces.IVoteDAO;
import org.mycompany.dao.models.Poll;
import org.mycompany.dao.models.Vote;

@WebServlet("/PollsServlet")
public class PollsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
     
    public PollsServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		DAOFactory daoFactory = DAOFactory.getInstance("polls");
        IPollDAO pollDAO = daoFactory.getPollDAO();
    //    IUserDAO userDAO = daoFactory.getUserDAO();
	//	IVoteDAO voteDAO = daoFactory.getVoteDAO();
        
		if(session == null)
		{	
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	        out.println("<head>");
	
	        String title = "Projet Fil Rouge V2";
	        out.println("<title>" + title + "</title>");
	        out.println("</head>");
	        
	        out.println("<body bgcolor=\"grey\">");
	        out.println("<p>");
	        out.print("<form action=\"");
	        out.print("LoginServlet\" ");
	        out.println("method=POST>");
	        out.println("<label class=\"p\">Username :</label>");
	        out.println("<input type=text size=20 name=username required>");
	        out.println("<label class=\"p\">Password :</label>");
	        out.println("<input type=password size=20 name=password required>");
	        out.println("<input type=submit value=\"Se connecter\">");
	        out.println("</form>");
	        out.println("</p>");
	        out.println("<br>");
	        out.println("<h1><center>Liste des Polls</h1>");
	       
	        try
			{
				for (Poll poll:pollDAO.findAll()) 
				{
					out.println(poll.getTitle());
					out.println("<br>");
				}
			} catch (DAOException e) 
			{
				e.printStackTrace();
			}
	        
	        out.println("</body>");
	        out.println("</html>");
			
			System.out.println();
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<head>");
			out.println("<title>");		
			out.println("Mes Polls");	
			out.println("</title>");
			out.println("</head>");
		
			out.println("<body>");	
			String username = (String)session.getAttribute("username");
			
			out.println("Bienvenue " + username);
			
			out.println(" <p style=\"text-align:right\"><a href= \"LogoutServlet\" />Se déconnecter </a><p>");
			
			org.mycompany.dao.models.User user = new org.mycompany.dao.models.User();
			
			out.println("<h4> Les Pools que vous avez créé </h4>");
			try
			{
				out.print("<form action=\"");
		        out.print("VoteServlet\" ");
		        out.println("method=POST>");
				out.println("<table>");
				
				for (Poll poll:pollDAO.findAllByAuthor(user))
				{
						out.println("<tr>");
							out.println("<td>");
							out.println(poll.getTitle());
							out.println("</td>");
						out.println("</tr>");
				}
				out.println("</table>");

			} catch (DAOException e) 
			{
				e.printStackTrace();
			}
			
			out.println("Liste des polls auxquels vous n'avez pas répondu");
	/*		out.println("<table Table-Layout: Fixed>");
			
			out.println("<tr>");
			out.println("<td><span style = \"color:	green;\">Liste des polls auxquels vous n'avez pas répondu</strong><P></td>");	
			out.println("</tr>");*/
			
			for (Poll poll:pollDAO.getUnvotedPollsForUser(user))
			{
		/*		out.println("<tr>");
				out.println("<td>");*/
				out.println(poll.getTitle());
				out.println("<br/>");
				out.print("<form action=\"");
				out.println("VoteServlet\" method=POST>");
				out.println("Votre vote : <br>");
			/*	out.println("</td>");
				out.println("</tr>");*/
				
			//	out.println("<tr>");
			/*	out.println("<td>*/
			//	out.println("<td>" )
				out.println("<input type=\"radio\" name=\"choice\" value=\"POUR\" id=\"POUR\"/><label for = \"POUR\">OUI</label>");
				out.println("<input type=\"radio\" name=\"choice\" value=\"CONTRE\" id=\"CONTRE\" /><label for=\"CONTRE\">NON</label><br/>");
			//	out.println("<td><input type=\"hidden\" name=\"poll_id\" value=\"");
				out.println("<input type=\"hidden\" name=\"poll_id\" value="+poll.getId()+">");
				out.println("<input type=\"hidden\" name=\"user_id\" value="+user.getId()+">");
				out.println("<input type=submit></form>");
				
				
				
				/*		out.println("<td><input type=\"radio\" name=\"choice\" value=\"CONTRE\">NON</td>");
				out.println("<td><input type=\"hidden\" name=\"poll_id\" value=\"");*/
		/*		out.println(poll.getId());
				out.println("\" /></td>");*/
				
			//	out.println("<td><input type=\"submit\" value=\"Valider\"></td>");
	//			out.println("</tr>");
			}
				
		//	out.println("</table>");
			out.println("</body>");
			out.println("</HTML>");
		}	
//user = userDAO.find(user_id);

	}
}