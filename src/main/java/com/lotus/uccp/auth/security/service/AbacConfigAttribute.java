/*Licensed Materials - 
 Property of IBM
6949 - 67L
  Copyright IBM Corp. 2017, 2018 All Rights Reserved
*/

/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.auth.security.service;

import org.springframework.security.access.ConfigAttribute;

public class AbacConfigAttribute implements ConfigAttribute{

	private String attribute;
	
	public void setAttribute(String attribute){
		this.attribute = attribute;
	}
	
	
	@Override
	public String getAttribute() {
		return attribute;
	}

}
