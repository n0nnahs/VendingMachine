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
	protected List<Item> inventoryList = new ArrayList<>();
	
	
	
	public VendingMachine() {
		stockFromFile();
	}
	
	public void mainMenu() {
		boolean goodInput = false;
		
		while(!goodInput) {
			System.out.println("(1) Display Vending Machine Items" + '\n' + "(2) Purchase" + '\n' + "(3) Exit");
			System.out.println();
			Scanner userInput = new Scanner(System.in);
			String selection = userInput.nextLine();
			
				if(selection.equals("1")) {
					displayInventory();
					goodInput = false;
				}
				else if(selection.equals("2")) {
					purchaseMenu();
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
		
	public void purchaseMenu() {
		boolean goodInput = false;
		while(!goodInput) {
		System.out.println("(1) Feed Money" + '\n' + "(2) Select Product" + '\n' + "(3) Finish Transaction");
		//NEW
		System.out.println();
		Scanner userInput = new Scanner(System.in);
		String selection = userInput.nextLine();
		
			if(selection.equals("1")) {
				System.out.print("Enter money: ");
				 Scanner addMoney = new Scanner(System.in);
				 int money = addMoney.nextInt();
				 //FIND SUM OF MONEY ADDED HERE
				 System.out.println("Current Money: " );
				goodInput = true;
				
			}
			else if(selection.equals("2")) {
				//select product
				goodInput = true;
			}
			else if(selection.equals("3")) {
				//finish transaction
				goodInput = true;
			}
		//System.out.println(currentMoney());
		
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
