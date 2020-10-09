package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	

	public void mainMenu() throws NumberFormatException, IOException {
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
		
	public void purchaseMenu() throws NumberFormatException, IOException {
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

	public void feedMoney() throws NumberFormatException, IOException {
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
				log("FEED MONEY:", currentBalance, Double.parseDouble(money));
				currentBalance += Double.parseDouble(money);
				System.out.println();
				System.out.println("Current balance: " + currentBalance);
			}
		}
	}
	
	public void selectItem() throws NumberFormatException, IOException {
		displayInventory();
		System.out.println(currentBalance);

		System.out.println();
		System.out.println("Please enter an item code: ");
		String userSelection = userInput.nextLine().toUpperCase();
		int dispenseQuantity = 0;
		
		for(Item inventory : inventoryList) {
		
			if(inventory.getLocation().equals(userSelection)) {
				System.out.println("How many would you like to dispense? ");
				dispenseQuantity = Integer.parseInt(userInput.nextLine());
				
				//if funds are not enough
				if(currentBalance <= (inventory.getPrice() * dispenseQuantity)) {
					System.out.println("Insufficient Funds!");
					purchaseMenu();
				}	
				
				else if(currentBalance >= (inventory.getPrice() * dispenseQuantity)) {
				
					if(inventory.getQuantity() < dispenseQuantity) {
						System.out.println("Not enough product available!");
						selectItem();
					}
				
				
					if(inventory.getQuantity() >= dispenseQuantity ) {
						
						currentBalance -= (inventory.getPrice() * dispenseQuantity);
						inventory.dispense(dispenseQuantity);
						purchaseMenu();
					}
					if(inventory.getQuantity() < dispenseQuantity) {
						System.out.println("Not enough product available!");
						selectItem();
				}
			
			}
			else if(!inventory.getLocation().equals(userSelection)) {
					System.out.println("Invalid selection");
					selectItem();
			}
				
			}	

		}
	}
	
	private void log(String action, double startingMoney, double endingMoney) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		File log = new File("log.txt");
		if(!log.exists()) {
			try {
				log.createNewFile();
			}
			catch(IOException e){
				System.out.println("IO Exception");
			}
		}
		try(FileWriter myFileWriter = new FileWriter(log.getAbsolutePath(), true); //open for appending instead of rewriting
				PrintWriter myPrintWriter = new PrintWriter(myFileWriter)) {
				myPrintWriter.println(dateString + " " + action + " " + startingMoney + " " + endingMoney);
			}
		}	
		
		
		
	
	
	
	
	private void stockFromFile() {
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
