// $Id: NTSystemLogin.java 6 2008-09-03 19:11:01Z celdredge $
package com.tagish.auth.win32;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import com.tagish.auth.BasicLogin;
import com.tagish.auth.Utils;
import com.tagish.auth.win32.typed.NTDomainPrincipal;
import com.tagish.auth.win32.typed.NTDomainSIDPrincipal;
import com.tagish.auth.win32.typed.NTGroupPrincipal;
import com.tagish.auth.win32.typed.NTGroupSIDPrincipal;
import com.tagish.auth.win32.typed.NTUserPrincipal;
import com.tagish.auth.win32.typed.NTUserSIDPrincipal;

/**
 * LoginModule for Windows NT. This module's behavior depends on the following
 * boolean options that may appear in the configuration file:
 * <table border="0">
 * 	<tr><td>returnNames</td><td>Principals will use NT names</td></tr>
 * 	<tr><td>returnSIDs</td><td>Principals will use NT SIDs</td></tr>
 * 	<tr><td>defaultDomain</td><td>Supply the name of the domain that
 *  will be used to authenticate logons. If this option is not present
 *  a domain name will be requested using a TextInputCallback.
 *  </td></tr>
 * </table>
 *
 * @author Andy Armstrong, <A HREF="mailto:andy@tagish.com">andy@tagish.com</A>
 * @version 1.0.3
 */
public class NTSystemLogin extends BasicLogin
{
	// Native object
	protected NTSystem			ntSystem;

	protected boolean			returnNames		= true;
	protected boolean			returnSIDs		= true;
	protected String			defaultDomain	= null;
	protected boolean			enableDomainTextCallback = true;

	// the authentication status
	protected boolean			succeeded		= false;
	protected boolean			commitSucceeded	= false;

	protected Vector			principals;

	/**
	 * Initialize this <code>LoginModule</code>.
	 *
	 * <p>
	 *
	 * @param subject the <code>Subject</code> to be authenticated. <p>
	 *
	 * @param callbackHandler a <code>CallbackHandler</code> for communicating
	 *			with the end user (prompting for usernames and
	 *			passwords, for example). <p>
	 *
	 * @param sharedState shared <code>LoginModule</code> state. <p>
	 *
	 * @param options options specified in the login
	 *			<code>Configuration</code> for this particular
	 *			<code>LoginModule</code>.
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		super.initialize(subject, callbackHandler, sharedState, options);

		// Construct the native proxy
		ntSystem = new NTSystem();
		ntSystem.checkVersion();

		// initialize any configured options
		returnNames		= getOption("returnNames",		returnNames);
		returnSIDs		= getOption("returnSIDs",		returnSIDs);
		defaultDomain	= getOption("defaultDomain",	defaultDomain);
		enableDomainTextCallback = getOption("enableDomainTextCallback", enableDomainTextCallback);
	}

	/**
	 * Authenticate the user by prompting for a username and password.
	 *
	 * <p>
	 *
	 * @return true in all cases since this <code>LoginModule</code> should not be ignored.
	 * @exception FailedLoginException if the authentication fails. <p>
	 * @exception LoginException if this <code>LoginModule</code>
	 * 		is unable to perform the authentication.
	 */
	public boolean login() throws LoginException
	{
		// username and password
		String	username;
		char	password[] = null;
		String	domain = defaultDomain;

		try {

			// prompt for a username and password
			if (callbackHandler == null)
				throw new LoginException("Error: no CallbackHandler available to garner authentication information from the user");

			List<Callback> callbacks = new ArrayList<Callback>();
			callbacks.add(new NameCallback("Username: "));
			callbacks.add(new PasswordCallback("Password: ", false));
			if (enableDomainTextCallback) {
				callbacks.add(new TextInputCallback("Domain: "));
			}

			try {
				callbackHandler.handle(callbacks.toArray(new Callback[callbacks.size()]));

				// Get username...
				username = ((NameCallback) callbacks.get(0)).getName();

				// ...password...
				password = ((PasswordCallback) callbacks.get(1)).getPassword();
				((PasswordCallback)callbacks.get(1)).clearPassword();

				boolean domainSetInUsername = false;
				
				if (username.indexOf('\\')>0) {
					String[] strings = username.split("\\\\");
					
					domain = strings[0];
					username = strings[1];
					domainSetInUsername = true;
				}
				
				// ...and domain.
				if (callbacks.size() == 3 && !domainSetInUsername) {
					domain = ((TextInputCallback) callbacks.get(2)).getText();
				}

				if (domain != null && domain.length() == 0) {
					domain = null;
				}
			} catch (java.io.IOException ioe) {
				throw (LoginException)new LoginException(ioe.toString()).initCause(ioe);
			} catch (UnsupportedCallbackException uce) {
				throw (LoginException)new LoginException("Error: " + uce.getCallback().toString() +
				" not available to garner authentication information from the user").initCause(uce);
			}

			// Attempt to logon using the supplied credentials
			succeeded = false;
			ntSystem.logon(username, password, domain);			// may throw
			succeeded = true;
		} finally {
			Utils.smudge(password);
		}

		return true;
	}

	/**
	 * Place the specified <CODE>Principle</CODE> in the subject and also record it in our
	 * principles <CODE>Vector</CODE> so we can remove them all later.
	 *
	 * @param s The <CODE>Set</CODE> to add the Principle to
	 * @param p Principle to add
	 */
	private void putPrincipal(Set s, Principal p)
	{
		s.add(p);
		principals.add(p);
	}

	/**
	 * <p> This method is called if the LoginContext's overall authentication
	 * succeeded (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL
	 * LoginModules succeeded).
	 *
	 * <p>If this LoginModule's own authentication attempt
	 * succeeded (checked by retrieving the private state saved by the
	 * <code>login</code> method), then this method associates a
	 * number of <code>NTPrincipal</code>s
	 * with the <code>Subject</code> located in the
	 * <code>LoginModule</code>.  If this LoginModule's own
	 * authentication attempted failed, then this method removes
	 * any state that was originally saved.
	 *
	 * <p>
	 *
	 * @return true if this LoginModule's own login and commit
	 * 		attempts succeeded, or false otherwise.
	 * @exception LoginException if the commit fails.
	 */
	public boolean commit() throws LoginException
	{
		if (!succeeded) {
			return false;
		}

		principals = new Vector();

		Set s = subject.getPrincipals();
		String groups[];

		// Do printable names
		if (returnNames) {
			putPrincipal(s, new NTUserPrincipal(ntSystem.getName()));
			putPrincipal(s, new NTDomainPrincipal(ntSystem.getDomain()));
			groups = ntSystem.getGroupNames(false);
			for (int g = 0; groups != null && g < groups.length; g++) {
				if (groups[g] != null) {
					putPrincipal(s, new NTGroupPrincipal(groups[g]));
				}
			}
		}

		// Do SIDs
		if (returnSIDs) {
			putPrincipal(s, new NTUserSIDPrincipal(ntSystem.getUserSID()));
			putPrincipal(s, new NTDomainSIDPrincipal(ntSystem.getDomainSID()));
			groups = ntSystem.getGroupIDs();
			for (int g = 0; groups != null && g < groups.length; g++) {
				putPrincipal(s, new NTGroupSIDPrincipal(groups[g]));
			}
		}

		commitSucceeded = true;
		return true;
	}

	/**
	 * <p> This method is called if the LoginContext's
	 * overall authentication failed.
	 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
	 * did not succeed).
	 *
	 * <p> If this LoginModule's own authentication attempt
	 * succeeded (checked by retrieving the private state saved by the
	 * <code>login</code> and <code>commit</code> methods),
	 * then this method cleans up any state that was originally saved.
	 *
	 * <p>
	 *
	 * @exception LoginException if the abort fails.
	 *
	 * @return false if this LoginModule's own login and/or commit attempts
	 *		failed, and true otherwise.
	 */
	public boolean abort() throws LoginException
	{
		if (!succeeded) {
			return false;
		} else if (succeeded && !commitSucceeded) {
			// login succeeded but overall authentication failed
			succeeded = false;
		} else {
			logout();
		}
		return true;
	}

	/**
	 * Logout the user.
	 *
	 * <p> This method removes the <code>Principal</code>s
	 * that were added by the <code>commit</code> method.
	 *
	 * <p>
	 *
	 * @return true in all cases since this <code>LoginModule</code>
	 *          should not be ignored.
	 * @exception LoginException if the logout fails.
	 */
	public boolean logout() throws LoginException
	{
		ntSystem.logoff();

		succeeded		= false;
		commitSucceeded	= false;
		// Remove all the principals we added
		Set s = subject.getPrincipals();
		int sz = principals.size();
		for (int p = 0; p < sz; p++) {
			s.remove(principals.get(p));
		}
		principals = null;

		return true;
	}
}
