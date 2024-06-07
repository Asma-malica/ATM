package com.hdfc.atm.service;

import com.hdfc.atm.iservice.IAccountService;

public class AccountService implements IAccountService
{
	private static Integer balance = 1000;
	public boolean deposit(Integer amount) 
	{
		boolean IsDeposited = false ;
		try {
			
			this.balance += amount ;
			IsDeposited = true ; 
				
		}
		catch(Exception ex)
		{
			System.out.println("Exception raised while depositing the amount : "+ex.getMessage());
		}
		
		return IsDeposited;
	}


	@Override
	public boolean withdraw(Integer amount) 
	{
		boolean IsDeducted = false ; 
		try {
			
			this.balance += amount ;
			IsDeducted = true ; 
				
		}
		catch(Exception ex)
		{
			System.out.println("Exception raised while deducting the amount : "+ex.getMessage());
		}
		
		return IsDeducted;
	}


	@Override
	public Integer getBalance() 
	{
		return this.balance;
		
	}
	

}
