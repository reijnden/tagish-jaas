// $Id: DBLogin.java 2 2008-09-03 19:06:36Z celdredge $
package com.tagish.auth;

import java.util.Map;
import java.io.*;
import java.util.*;
import java.security.Principal;
import java.sql.*;
import java.math.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

/**
 * Simple database based authentication module.
 *
 * @author Andy Armstrong, <A HREF="mailto:andy@tagish.com">andy@tagish.com</A>
 * @version 1.0.3
 */
public class DBLogin extends SimpleLogin
{
	protected String                dbDriver;
	protected String                dbURL;
	protected String                dbUser;
	protected String                dbPassword;
	protected String                userTable;
	protected String                userColumn;
	protected String                passwordColumn;
	protected String                where;

	protected synchronized Vector validateUser(String username, char password[]) throws LoginException
	{
		ResultSet rsu = null;
		Connection con = null;
		PreparedStatement psu = null;

		try
		{
			Class.forName(dbDriver);
			if (dbUser != null)
			   con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			else
			   con = DriverManager.getConnection(dbURL);

			psu = con.prepareStatement("SELECT " + passwordColumn + " FROM " + userTable +
									   " WHERE " + userColumn + " = ? " + where);

			psu.setString(1, username);
			rsu = psu.executeQuery();
			if (!rsu.next()) throw new FailedLoginException("Unknown user");
			String upwd = rsu.getString(1);
			if (!upwd.equals(new String(password))) throw new FailedLoginException("Bad password");
			Vector p = new Vector();
			p.add(new TypedPrincipal(username, TypedPrincipal.USER));
			p.add(new TypedPrincipal("VALID-USER", TypedPrincipal.GROUP));
			return p;
		}
		catch (ClassNotFoundException e)
		{
			throw new LoginException("Error reading user database (" + e.getMessage() + ")");
		}
		catch (SQLException e)
		{
			throw new LoginException("Error reading user database (" + e.getMessage() + ")");
		}
		finally
		{
			try {
				if (rsu != null) rsu.close();
				if (psu != null) psu.close();
				if (con != null) con.close();
			} catch (Exception e) { }
		}
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		super.initialize(subject, callbackHandler, sharedState, options);

		dbDriver = getOption("dbDriver", null);
		if (dbDriver == null) throw new Error("No database driver named (dbDriver=?)");
		dbURL = getOption("dbURL", null);
		if (dbURL == null) throw new Error("No database URL specified (dbURL=?)");
		dbUser = getOption("dbUser", null);
		dbPassword = getOption("dbPassword", null);
		if ((dbUser == null && dbPassword != null) || (dbUser != null && dbPassword == null))
		   throw new Error("Either provide dbUser and dbPassword or encode both in dbURL");

		userTable    = getOption("userTable",    "User");
		userColumn = getOption("userColumn", "login");
		passwordColumn    = getOption("passwordColumn",    "password");
		where        = getOption("where",        "");
		if (null != where && where.length() > 0)
			where = " AND " + where;
		else
			where = "";
	}
}
