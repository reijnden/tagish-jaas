// $Id: NTDomainPrincipal.java 2 2008-09-03 19:06:36Z celdredge $
/*
 * NTDomainPrincipal.java
 *
 * Created on January 29, 2003, 3:45 PM
 */

package com.tagish.auth.win32.typed;

import com.tagish.auth.win32.*;

/**
 * A NTDomainPrincipal is a <code>Class</code> level Principal that represents a
 * named NT Domain.
 *
 * @author Jerry L Smith II, <A HREF="mailto:jsmith@sedonaesolutions.com">jsmith@sedonaesolutions.com</A>
 * @version 1.0.3
 */
public class NTDomainPrincipal extends NTPrincipal {

	/** Creates a new instance of NTDomainPrincipal */
	public NTDomainPrincipal(String name) {
		super(name, NTPrincipal.DOMAIN);
	}

}
