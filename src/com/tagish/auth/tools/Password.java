// $Id: Password.java 2 2008-09-03 19:06:36Z celdredge $
package com.tagish.auth.tools;

import com.tagish.auth.Utils;

/**
 * Simple command line tool to encode passwords in a form suitable
 * for use by DBLogin and FileLogin based login schemes.
 */
public class Password
{
	public static void main(String[] args)
	{
		try
		{
			for (int a = 0; a < args.length; a++)
				System.out.println(args[a] + ": " + Utils.cryptPassword(args[a].toCharArray()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}