
package com.lotus.uccp;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.lotus.uccp.auth.security.service.AbacAuthenticationEntryPoint;
import com.lotus.uccp.auth.security.service.AbacDaoAuthenticationProvider;
import com.lotus.uccp.auth.security.service.AbacAuthenticationTokenFilter;
import com.lotus.uccp.auth.security.service.AbacSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AbacAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AbacDaoAuthenticationProvider authenticatioProvider;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticatioManager() {
		return new ProviderManager(Collections.singletonList(authenticatioProvider));
	}

	@Bean
	public AbacAuthenticationTokenFilter abacAuthenticationTokenFilter() {

		AbacAuthenticationTokenFilter filter = new AbacAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticatioManager());
		filter.setAuthenticationSuccessHandler(new AbacSuccessHandler());
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.woff",
						"/**/*.ico", "/**/*.ttf", "/**/*.jpg")
				.permitAll()
				.antMatchers("/token/**", "/control/**", "/auth/**", "/authRes/**", "/consent/**", "/images/**",
						"/ConfigController/**", "/TransactionController/**", "/PersonSearch/**", "/person/**", "/ODSController/**","/pwdcontrol/**","/UserMgmt/**")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterAt(abacAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		// http.addFilterAt(authoriationFilter(),FilterSecurityInterceptor.class );
	}

//	@Bean
	public SimpleUrlHandlerMapping faviconHandlerMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(Integer.MIN_VALUE);
		mapping.setUrlMap(Collections.singletonMap("mylocation/favicon.ico", null));
		return mapping;
	}
}