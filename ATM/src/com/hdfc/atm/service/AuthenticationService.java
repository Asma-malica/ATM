package com.hdfc.atm.service;

import com.hdfc.atm.iservice.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService
{
	private Integer passCode = 1234 ;
		
		@Override
		public boolean authenticate(Integer pin) 
		{
			return this.passCode.equals(pin)?true : false; 
		}

		@Override
		public boolean resetPin(Integer newPin) 
		{
			boolean isChanged = false ;
			try 
			{
				this.passCode = newPin ;
				isChanged = true ; 
			}
			catch (Exception ex)
			{
				System.out.println("Exception Raised while changin the pin : "+ex.getMessage());
			}
			return isChanged;
		}
		 
	

}
