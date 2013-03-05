<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Connexion</title>
	</head>

	<body>
       <form action="connection" method=POST>
	        <label for = "username">Username :</label>
	        	<input type = "text" size = 20 name = "username" placeholder = "Votre pseudo" required ="required">
	        <label for = "password">Password :</label>
	        	<input type = "password" size = 20 name = "password" placeholder = "Votre mot de passe" required = "required">
	        <input type = "submit" value="Se connecter">
        </form>
</body>
</html>