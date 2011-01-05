// $Id: NTGroupSIDPrincipal.java 2 2008-09-03 19:06:36Z celdredge $
/*
 * NTGroupPrincipal.java
 *
 * Created on January 29, 2003, 3:46 PM
 */

package com.tagish.auth.win32.typed;

import com.tagish.auth.win32.*;

/**
 * A NTGroupPrincipal is a <code>Class</code> level Principal that represents a
 * NT Group's SID.
 *
 * @author Jerry L Smith II, <A HREF="mailto:jsmith@sedonaesolutions.com">jsmith@sedonaesolutions.com</A>
 * @version 1.0.3
 */
public class NTGroupSIDPrincipal extends NTPrincipal {

	/** Creates a new instance of NTGroupPrincipal */
	public NTGroupSIDPrincipal(String name) {
		super(name, NTPrincipal.GROUP);
	}

}
