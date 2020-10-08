package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine implements Purchasable {
	
	public VendingMachine() {
		
	}
	
	public void mainMenu() {
		System.out.println("(1) Display Vending Machine Items" + '\n' + "(2) Purchase" + '\n' + "(3) Exit");
		
		boolean goodInput = false;
		
		while(!goodInput) {
			Scanner userInput = new Scanner(System.in);
			String selection = userInput.nextLine();
			
				if(selection.equals("1")) {
					goodInput = true;
				}
				else if(selection.equals("2")) {
					goodInput = true;
				}
				else if(selection.equals("3")) {
					goodInput = true;
				}
				else if(selection.equals("4")) {
					goodInput=true;
				}
		}			
	}
		
	
	private static void inventoryList() {
		File file = new File("vendingmachine.csv");
		Scanner sc = null;
		
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				parseLine(str);
			}
		}
		catch(IOException e) {
			
		}
		sc.close();
		
	}
		
		private static void parseLine(String str) {
			String location, name, price, type;
			List<String> item = new ArrayList<String>();

			Scanner sc = new Scanner(str);
			sc.useDelimiter("[|]");
			
			while(sc.hasNextLine()) {
				location = sc.next();
				name = sc.next();
				price = sc.next();
				type = sc.next();
				
				item.add(location + " - " + name + " - " + price + " - " + type);
				System.out.println(item);
			}		
			sc.close();
		}
		
	
	
	
}
