package fr.intechinfo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.mycompany.dao.exceptions.DAOException;
import org.mycompany.dao.factories.DAOFactory;
import org.mycompany.dao.interfaces.IPollDAO;
import org.mycompany.dao.interfaces.IUserDAO;
import org.mycompany.dao.interfaces.IVoteDAO;
import org.mycompany.dao.models.Poll;
import org.mycompany.dao.models.User;
import org.mycompany.dao.models.Vote;
import org.mycompany.dao.models.Vote.Choice;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	/*	DAOFactory daoFactory = DAOFactory.getInstance("polls");
        IPollDAO pollDAO = daoFactory.getPollDAO();
        IUserDAO userDAO = daoFactory.getUserDAO();
		IVoteDAO voteDAO = daoFactory.getVoteDAO();
		
		HttpSession session = request.getSession(false);
		User user=new User();
		Poll poll=new Poll();
		try
		{
		Long user_id = (Long) session.getAttribute("username");
		user= DAOFactory.getInstance("polls").getUserDAO().find(user_id);
		
		Long poll_id= Long.parseLong( request.getParameter("poll_id"));
		poll = DAOFactory.getInstance("polls").getPollDAO().find(poll_id);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		Vote vote = new Vote();
		
		vote.setUser(user);
		vote.setPoll(poll);
		
		String valueOfChoice= request.getParameter("choice");
		if (valueOfChoice.equals(Choice.POUR))
			vote.setChoice(Choice.POUR);
		else if(valueOfChoice.equals(Choice.CONTRE))
			vote.setChoice(Choice.CONTRE);
		else
			return;
		
		try
		{
			voteDAO.create(vote);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}*/

		String vote = request.getParameter("choice");
		long user_id = new Integer(request.getParameter("user"));
		long poll_id = new Integer(request.getParameter("poll"));

		DAOFactory daoFactory = DAOFactory.getInstance("polls");
		IUserDAO userDAO = daoFactory.getUserDAO();
		IPollDAO pollDAO = daoFactory.getPollDAO();
		IVoteDAO voteDAO = daoFactory.getVoteDAO();

		if(vote.equals("POUR") || vote.equals("CONTRE"))
		{
		Choice choice = (vote.equals("POUR")) ? Choice.POUR : Choice.CONTRE;

		Poll pollToAdd = new Poll();
		org.mycompany.dao.models.User user = new org.mycompany.dao.models.User();

		try
		{
		pollToAdd = pollDAO.find(poll_id);
		user = userDAO.find(user_id);
		}
		catch (DAOException e) 
		{
		e.printStackTrace();
		}

		Vote voteToAdd = new Vote(null, pollToAdd, user, choice );
		try
		{
		voteDAO.create(voteToAdd);
		}
		catch (DAOException e) 
		{
		e.printStackTrace();
		}
		}

		response.sendRedirect("PollsServlet");
	}
}
