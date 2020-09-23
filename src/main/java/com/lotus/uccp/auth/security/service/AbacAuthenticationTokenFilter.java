package  com.lotus.uccp.auth.security.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.google.gson.Gson;
import com.lotus.uccp.authentication.model.User360;
import com.lotus.uccp.authentication.model.Users;


public class AbacAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	private boolean postOnly = true;
	
  
	
	public AbacAuthenticationTokenFilter() {
	        super("/rest/**");
	    }
	 
	
	 @Override
	 public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException, IOException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException(
						"Authentication method not supported: " + request.getMethod());
			}
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();

			Users user = gson.fromJson(reader, Users.class); 

			String username = user.getUsername();
			String password = user.getPassword();

			if (username == null) {
				username = "";
			}

			if (password == null) {
				password = "";
			}

			username = username.trim();

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			
			/*UserDetailsServiceImpl userdetailsserviceimpl = new UserDetailsServiceImpl();
			//UserDetails userdetails = userdetailsserviceimpl.loadUserByUsername(username); */
			// Allow subclasses to set the "details" property
			AbacTokenGenerator abtoken = new AbacTokenGenerator();
			User360 user360 = new User360();
			user360.setUsername(username);
			user360.setPassword(password);
			String key = abtoken.generate(user360);
			
			 //String s = "rohit" ;
		
			
			 System.out.println(":: Generated Key ::" + key );
			 Cookie ck= new Cookie("JwtToknen",key); 
		     HttpSession session = request.getSession() ;
		     session.setMaxInactiveInterval(70*60);
		     
		     session.setAttribute("Username", username);
		     session.setAttribute("Password", password);
		        
		     System.out.println("@@## After Cookies -" + username + "--" + password);
		     response.addCookie(ck);

			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}
	 
	 protected void setDetails(HttpServletRequest request,
				UsernamePasswordAuthenticationToken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}
	
	 
/*	 @Override
	 protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
	        super.successfulAuthentication(request, response, chain, authResult);
	        System.out.println("authResult.getName():" + authResult.getName());
	        chain.doFilter(request, response);
	    }*/
	
		
}

	

