package com.github.btheu.settesting.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.btheu.settesting.User;

public class Simple1User implements User {

	public void create() {

	}

	public void validate() {
		// TODO Auto-generated method stub

	}

	public void authenticate() {
		springSecurityAuthentication("btheu","btheu");
	}

	public void invalidate() {
		springSecurityInvalidate();
	}
	

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	@Autowired
	protected AuthenticationManager authenticationManager;

	private void springSecurityAuthentication(String username, String password) {

		UsernamePasswordAuthenticationToken authRequest =
				new UsernamePasswordAuthenticationToken(username, password);

		Authentication authResult = authenticationManager.authenticate(authRequest);

		SecurityContextHolder.getContext().setAuthentication(authResult);

	}

	private void springSecurityInvalidate() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}


}
