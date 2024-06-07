package com.hdfc.atm;

import com.hdfc.atm.ui.iservice.ICUIService;
import com.hdfc.atm.ui.service.CUIService;

public class ATM {

	public static void main(String[] args) {
		ICUIService icuiservice = new CUIService(); // creating interface reference ( obj is created )
		icuiservice.showCUI();

	}

}
