package com.lotus.uccp.auth.security.service;
/*/* Licensed Materials - 
 Property of IBM 6949 - 67L 
 Copyright IBM Corp. 2017, 2018 All Rights Reserved */
//package  com.ibm.gbs.auth.security.service;
/*
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ibm.gbs.model.AbacAuthenticationToken;
import com.ibm.gbs.model.AbacUser;
import com.ibm.gbs.model.AbacUserDetails;

import org.springframework.security.core.GrantedAuthority;

@Component
public class AbacAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private AbacTokenValidator abacTokenValidator;
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		AbacAuthenticationToken abacToken = (AbacAuthenticationToken)authentication;
		System.out.println("sinu abacToken.getName() in additional check" + abacToken.getName());
		System.out.println("sinu userDetails in additional check : userDetails.getPassword() :"+ userDetails.getUsername() );
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
		AbacAuthenticationToken abacToken = (AbacAuthenticationToken)authenticationToken;
		String token = abacToken.getToken();
		
		System.out.println("Sinu User Name from Authentication Provider : " + username);
		
		AbacUser abacUser = abacTokenValidator.validate(token);
		
		if (abacUser == null) {
            throw new RuntimeException("JWT Token is incorrect");
        } 

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(abacUser.getRole());
        
        return new AbacUserDetails(abacUser.getUserName(), abacUser.getId(),
                token,
                grantedAuthorities);
		
		 	
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return AbacAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
*/