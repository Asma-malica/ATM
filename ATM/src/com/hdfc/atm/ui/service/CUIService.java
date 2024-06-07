package com.hdfc.atm.ui.service;

import java.beans.Expression;
import java.util.Scanner;
import com.hdfc.atm.iservice.IAccountService;
import com.hdfc.atm.iservice.IAuthenticationService;
import com.hdfc.atm.service.AccountService;
import com.hdfc.atm.service.AuthenticationService;
import com.hdfc.atm.ui.iservice.ICUIService;

public class CUIService implements ICUIService {
	IAuthenticationService authenticationService = new AuthenticationService();
	Scanner sc = new Scanner(System.in); // creating scanner obj across cls as we can use across the particular cls and
											// methods

	@Override
	public void showCUI() {
		while (true) {
			showMenu();
		}
	}

	private void showMenu() {
		System.out.println("\n1.Deposit\n2.Withdraw\n3.Balance\n4.PIN Change\n5.Exit");
		promptUserChoice();
	}

	private void promptUserChoice() {
		System.out.println("Enter Choice : ");
		Integer choice = sc.nextInt();
		performOperations(choice);
	}

	private void performOperations(Integer choice) {
		Integer amount = null;
		IAccountService accountService = new AccountService(); //
		switch (choice) {
		case 1:
			amount = readAmount();
			if (validateAmount(amount)) {
				if (accountService.deposit(amount))
					System.out.println("Amount Deposited !!1 ");
				else
					System.out.println("Failed to deposit ");
			} else {
				System.out.println("Invalid amount Entered ... ");
			}
			break;

		case 2:
			if (authoriseUser()) {
				amount = readAmount();
				if (validateAmount(amount)) {
					if (accountService.getBalance() >= amount) {

						if (accountService.withdraw(amount)) {

							System.out.println("... Amount deducted ...");
						} 
						else {
							System.out.println("... Fail to deduct the amount ...");
						}
					} else {
						System.out.println("... Insufficient Funds ...");
					}
				} else {
					System.out.println("... Invalid Amount ...");
				}
			}
			break;
		case 3:
			if (authoriseUser()) {
				System.out.println("Balance : " + accountService.getBalance());
			}
			break;

		case 4:
			if (authoriseUser()) {
				Integer newPin = readPin();
				if (validatePin(newPin)) {
					if (authenticationService.resetPin(newPin)) {
						System.out.println("PIN changed Successfully ...");
					} else {
						System.out.println("... PIN change Failed ...");
					}
				} else {
					System.out.println("... Invalid PIN entered ..");
				}
			}
			break;

		case 5:
			System.exit(0); // smooth termination of the program
//			--> if System.exit(1) means termination with error

		default:
			System.out.println("Invalid Choice ");
			break;
		}
	}

	private Integer readAmount() {
		System.out.print("Enter Amount : ");
		return sc.nextInt();
	}

	private Integer readPin() {
		System.out.print("Enter PIN : ");
		return sc.nextInt();
	}

	private boolean validateAmount(Integer amount) {
		return ((amount > 0) && (amount % 100 == 0)) ? true : false;
	}

	private boolean validatePin(Integer pin) {
//		sanitizing the data or validating the data 
//		this validation doesn't take '0' as 1st input for taking '0' as input 
//		in java use regular Expression 
		return ((pin > 999) && (pin <= 9999)) ? true : false;
	}

	private boolean authoriseUser() {

		boolean isAuthorised = false;
		Integer count = 1;
		Integer pin = null;

		while (count <= 3) {
			pin = readPin();
			if (validatePin(pin)) {
				if (authenticationService.authenticate(pin)) {
					isAuthorised = true;
					break;
				} else {
					count++;
					if (count > 3) {
						System.out.println("Unauthorised user ... Terminate the Program ");
						System.exit(0);
					}
				}

			} else {
				System.out.println(" Invalid pin entered  ");
			}
		}

		return isAuthorised;
	}

}
