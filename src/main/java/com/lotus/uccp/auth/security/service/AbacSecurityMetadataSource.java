/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
package  com.lotus.uccp.auth.security.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AbacSecurityMetadataSource implements SecurityMetadataSource {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		SecurityContext sContext =SecurityContextHolder.getContext();
		Authentication authentication = sContext.getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
		for(GrantedAuthority gAuth : grantedAuthorities){
			System.out.println("gAuth.getAuthority() :"  + gAuth.getAuthority() );
			AbacConfigAttribute abacAtt = new AbacConfigAttribute();
			abacAtt.setAttribute(gAuth.getAuthority());
			configAttributes.add((ConfigAttribute)abacAtt);
		}
		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
