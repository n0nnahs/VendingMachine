package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws NumberFormatException, IOException {
		VendingMachine vm = new VendingMachine();
		
		vm.mainMenu();
		
	}
}
