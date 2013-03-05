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
import org.mycompany.dao.models.Poll;

@WebServlet("/PollsServlet")
public class PollsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static final String VUE_CONNECTION = "/WEB-INF/ConnectionForm.jsp";
	public static final String VUE_ALL_POLLS = "/WEB-INF/AllPolls.jsp";
	public static final String VUE_UNVOTERED_POLLS = "/WEB-INF/UnvoteredPolls.jsp";
	
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
        
		if(session == null)
		{	
			PrintWriter out = response.getWriter();
	       
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
	        this.getServletContext().getRequestDispatcher( VUE_ALL_POLLS ).forward( request, response );
		}
		else
		{
			PrintWriter out = response.getWriter();
			
			String username = (String)session.getAttribute("username");
			
			out.println("Bienvenue " + username);
			
			out.println(" <p style=\"text-align:right\"><a href= \"LogoutServlet\" />Se déconnecter </a><p>");
			
			org.mycompany.dao.models.User user = new org.mycompany.dao.models.User();
			
			out.println("<h4> Les Pools que vous avez créé </h4>");
			try
			{
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