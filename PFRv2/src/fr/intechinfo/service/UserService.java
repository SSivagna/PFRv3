package fr.intechinfo.service;

import java.util.HashMap;
import java.util.Iterator;

import org.mycompany.dao.exceptions.DAOException;
import org.mycompany.dao.factories.DAOFactory;
import org.mycompany.dao.interfaces.IPollDAO;
import org.mycompany.dao.interfaces.IUserDAO;
import org.mycompany.dao.interfaces.IVoteDAO;

import fr.intechinfo.model.User;

public class UserService
{
	public HashMap<String, String> hashMap = new HashMap<String, String>(); 
	
/*	public UserService()
	{
		hashMap.put("Louis", "chat");
		hashMap.put("Antoine", "ours");
	    hashMap.put("Geraldine", "tigre");
	}*/
	
	public User findUser(String username, String password) throws DAOException
	{
	/*	if(hashMap.containsKey(username) && hashMap.get(username).equals(password) )
		{
			return new User(username,password);
		}
		else
		{
			return null;
		}
		
		Iterator<String> i = hashMap.keySet().iterator();
		
		while( i.hasNext())
		{
			String key = i.next();
			String value = hashMap.get(key);
			
			if(key.equals(username) && value.equals(password))
			{
				return new User(username,password);
			}
		}
		return null;*/
		
		DAOFactory daoFactory = DAOFactory.getInstance("polls");
        IUserDAO userDAO = daoFactory.getUserDAO();
        
        if(userDAO.find(username, password) != null)
        {
        	return new User(username, password);
        }
        return null;
		
	}
}