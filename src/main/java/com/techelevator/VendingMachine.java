package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	Scanner userInput = new Scanner(System.in);
	protected List<Item> inventoryList = new ArrayList<>();
	protected double currentBalance = 0.00;
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	//vending machine constructor
	public VendingMachine() {
		stockFromFile();
	}
	
		

	public void mainMenu() throws NumberFormatException, IOException {
		boolean goodInput = false;
		System.out.println();
		System.out.println("*********************");
		System.out.println("*  Vendo-Matic 800  *");
		System.out.println("*     Main Menu     *");
		System.out.println("*********************");
		System.out.println();
		
		while(!goodInput) {
			System.out.println("(1) Display Vending Machine Items      (2) Purchase      (3) Exit");
			System.out.println();
			String selection = userInput.nextLine();
			
				if(selection.equals("1")) {
					displayInventory();
					goodInput = false;
				}
				else if(selection.equals("2")) {
					goodInput = true;
					purchaseMenu();
				}
				else if(selection.equals("3")) {
					goodInput = true;
					exit();
				}
				else if(selection.equals("4")) {
					goodInput=true;
				}
		}			
	}
		
	public void purchaseMenu() throws NumberFormatException, IOException {
		boolean goodInput = false;
		System.out.println();
		System.out.println("*********************");
		System.out.println("*  Vendo-Matic 800  *");
		System.out.println("*   Purchase Menu   *");
		System.out.println("*********************");
		System.out.println();
		
		while(!goodInput) {
			System.out.println("(1) Feed Money      (2) Select Product      (3) Finish Transaction");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Current balance: $" + df.format(currentBalance));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			String selection = userInput.nextLine();
			
				if(selection.equals("1")) {
					//feed money
					goodInput = true;
					feedMoney();
				}
				else if(selection.equals("2")) {
					//select product
					selectItem();
					goodInput = true;
				}
				else if(selection.equals("3")) {
					returnChange();
					goodInput = true;
					mainMenu();
				}		
			}
		}

	public void feedMoney() throws NumberFormatException, IOException {
		boolean finish = false;
		while(!finish) {
			System.out.println();
			System.out.println("\t Press P to Select Product");
			System.out.println("Please select amount to feed: (1) $1, (2) $2, (5) $5, (10) $10");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Current balance: $" + df.format(currentBalance));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print(" >>> ");
			String money = userInput.nextLine();
			
			if(money.toLowerCase().equals("p")) {
				finish = true;
				selectItem();
			}
			else if(money.equals("1") || money.equals("2") || money.equals("5") || money.equals("10")) {
				double startingAmount = currentBalance;
				currentBalance += Double.parseDouble(money);
				Log l = new Log("FEED MONEY: ", startingAmount, currentBalance);
			}
			else {
				invalidEntryBanner();
			}
		}
	}
	
	public void selectItem() throws NumberFormatException, IOException {
		displayInventory();
		System.out.println("Current Balance: $" + df.format(currentBalance));

		System.out.println();
		System.out.println("Please enter an item code: ");
		String userSelection = userInput.nextLine().toUpperCase();
		
		boolean goodInput = false;
		
		while(!goodInput) {
			for(Item inventory : inventoryList) {
				//if user enters a valid location
				if(inventory.getLocation().equals(userSelection)) {
					goodInput = true;
					goodItemSelection(inventory);
				}
			}
		invalidEntryBanner();
		selectItem();
		}
	}
	
	public void goodItemSelection(Item inventory) throws IOException {
		int dispenseQuantity = 0;

		boolean goodInput = false;
		while(!goodInput) {
			System.out.println("How many would you like to dispense? ");
			String kb = userInput.nextLine();

			if(kb.equals("1") || kb.equals("2") || kb.equals("3") || kb.equals("4")|| kb.equals("5")) {
				dispenseQuantity = Integer.parseInt(kb);
				goodInput = true;
			}
			else {
				invalidEntryBanner();
			}
		}
		
		if(dispenseQuantity < 0) {
			invalidEntryBanner();
			selectItem();
		}else {
			//if funds are not enough
			//if there isn't enough product inventory
			if(inventory.getQuantity() < dispenseQuantity) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(" Not enough product available!");
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				selectItem();
			}
			if(currentBalance <= (inventory.getPrice() * dispenseQuantity)) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(" Insufficient Funds!");
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

				purchaseMenu();
			}	
			
			//if there are enough funds for the transaction
			else if(currentBalance >= (inventory.getPrice() * dispenseQuantity)) {
				
				//if there isn't enough product inventory
				if(inventory.getQuantity() < dispenseQuantity) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					System.out.println(" Not enough product available!");
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					selectItem();
				}
			
				//if there is enough product
				if(inventory.getQuantity() >= dispenseQuantity ) {
					double startingAmount = currentBalance;
					currentBalance -= (inventory.getPrice() * dispenseQuantity);
					inventory.dispense(dispenseQuantity);
				
					//loops the log constructor to create separate lines in the log for each item removed
					double m = startingAmount;
					for(int i = 0; i < dispenseQuantity; i++) {
						Log l = new Log(inventory.getName(), inventory.getLocation(), m, (m - inventory.getPrice()));
						m -= inventory.getPrice();
					}	
					purchaseMenu();
				}
			}
		}
	}
	
	
	
	public void returnChange() throws IOException {
		int change = (int)(Math.ceil(currentBalance*100));
		
		int quarters = Math.round((int)change/25);
	    change = change % 25;
	    
	    int dimes = Math.round((int)change/10);
	    change = change % 10;
	    
	    int nickels = Math.round((int)change/5);
	    change = change % 5;
	    
	    Log l = new Log("GIVE CHANGE: ", currentBalance, 0.00);
	    
	    currentBalance = 0;
	    
	    System.out.println("Quarters: " + quarters + ", Dimes: " + dimes + ", Nickels: " + nickels);
	    
	}
	public void applicationBanner() {
		System.out.println("********************************");
		System.out.println("*  Welcome to Vendo-Matic 800  *");
		System.out.println("*  A product of Umbrella Corp  *");
		System.out.println("********************************");
		System.out.println();
	}
		
	private void exit() {
		System.out.println("***********************************************************");
		System.out.println("* Umbrella Corp would like to thank you for your purchase *");
		System.out.println("*       Thank you for vending with Vendo-Matic 800        *");
		System.out.println("***********************************************************");

		System.exit(1);;
	}
	
	private void invalidEntryBanner() {
		System.out.println("!!!!!!!!!!!!!!!");
		System.out.println(" Invalid Entry ");
		System.out.println("!!!!!!!!!!!!!!!");
	}
	
	
	private boolean stockFromFile() {
		File inventory = new File("vendingmachine.csv");
		
		try(Scanner inventoryStream = new Scanner(inventory)){
			
			while(inventoryStream.hasNextLine()) {
				
				String line = inventoryStream.nextLine();
				String[] pieces = line.split("\\|");
				
				String location = pieces[0];
				String name = pieces[1];
				double price = Double.parseDouble(pieces[2]);
				String type = pieces[3];
				
				if(type.toLowerCase().equals("chip")) {
					this.inventoryList.add(new Chip(location, name, price, type));
				}
				else if(type.toLowerCase().equals("candy")){
					this.inventoryList.add(new Candy(location, name, price, type));
				}
				else if(type.toLowerCase().equals("drink")) {
					this.inventoryList.add(new Drink(location, name, price, type));
				}
				else if(type.toLowerCase().equals("gum")) {
					this.inventoryList.add(new Gum(location, name, price, type));
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
			return false;
		}
		return true;

	}
		
	public void displayInventory() {
		System.out.println();
		System.out.println("*********************************************************");

		for(Item product : inventoryList) {
			System.out.println(product.getLocation() + " | " + product.getName() + "| $" + product.getPrice() + " | " + product.getType() + " | " + product.getQuantity());
		}
		System.out.println("*********************************************************");
		System.out.println();
	}
	
		
	
	
	
}
