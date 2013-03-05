package fr.intechinfo.model;

public class User 
{
	private String username;
	private String password;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String setUsername(String name)
	{
		username = name;
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String setPassword(String pass)
	{
		password = pass;
		return password;
	}
}