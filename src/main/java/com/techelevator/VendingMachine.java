package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine implements Purchasable {
	Scanner userInput = new Scanner(System.in);
	protected List<Item> inventoryList = new ArrayList<>();
	protected double currentBalance = 0.00;
	
	//vending machine constructor
	public VendingMachine() {
		stockFromFile();
	}
	

	public void mainMenu() {
		boolean goodInput = false;
		
		while(!goodInput) {
			System.out.println("(1) Display Vending Machine Items" + '\n' + "(2) Purchase" + '\n' + "(3) Exit");
			System.out.println();
			//Scanner userInput = new Scanner(System.in);
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
				}
				else if(selection.equals("4")) {
					goodInput=true;
				}
		}			
	}
		
	public void purchaseMenu() {
		boolean goodInput = false;
		while(!goodInput) {
		System.out.println("(1) Feed Money" + '\n' + "(2) Select Product" + '\n' + "(3) Finish Transaction");
		System.out.println();
		String selection = userInput.nextLine();
		
			if(selection.equals("1")) {
				goodInput = true;
				feedMoney();
			}
			else if(selection.equals("2")) {
				//select product
				selectItem();
				goodInput = true;
			}
			else if(selection.equals("3")) {
				//finish transaction
				goodInput = true;
			}
		System.out.println("Current balance: " + currentBalance);
		
			}
		}


	public void feedMoney() {
		boolean finish = false;
		while(!finish) {
			System.out.println();
			System.out.println("Please select amount to feed: (1) $1, (2) $2, (5) $5, (10) $10");
			System.out.println("Press p to Select Product");
			 String money = userInput.nextLine();
			if(money.toLowerCase().equals("p")) {
				finish = true;
				selectItem();
			}
			else {
				currentBalance += Double.parseDouble(money);
			 System.out.println();
			 System.out.println("Current balance: " + currentBalance);
			}
		}
	}
	
	
	
	public void selectItem() {
		displayInventory();
		
		System.out.println();
		System.out.println("Please enter an item code: ");
		String userSelection = userInput.nextLine();
		String dispenseQuantity = "";
		
		for(Item inventory : inventoryList) {
			if(inventory.getLocation() != userSelection) {
				System.out.println("Invalid selection");
			}
			else if(inventory.getLocation() == userSelection && inventory.getQuantity() >= Integer.parseInt(dispenseQuantity)) {
				System.out.println("How many would you like to dispense? ");
				dispenseQuantity = userInput.nextLine();
				
				currentBalance -= inventory.getPrice();
				inventory.dispense(Integer.parseInt(dispenseQuantity));
			}
			else if(inventory.getLocation() == userSelection && inventory.getQuantity() == 0) {
				System.out.println("Sold Out!");
			}
		}
	}
	
	
	
	private void stockFromFile() {
		File inventory = new File("vendingmachine.csv");
		
		try(Scanner inventoryStream = new Scanner(inventory))	 {
			
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
			System.exit(1);
		}	
	}
		
	public void displayInventory() {
		for(Item product : inventoryList) {
			System.out.println(product.getLocation() + " | " + product.getName() + "| " + product.getPrice() + " | " + product.getType() + " | " + product.getQuantity());
		}
		System.out.println();
		System.out.println();
	}
	
		
	
	
	
}
