package com.techelevator;

import java.io.IOException;

public class Application {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		VendingMachine vm = new VendingMachine();
		
		vm.applicationBanner();
		vm.mainMenu();
		
	}
}
