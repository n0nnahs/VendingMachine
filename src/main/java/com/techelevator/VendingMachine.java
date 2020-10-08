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
			Scanner userInput = new Scanner(System.in);
			String selection = userInput.nextLine();
			
				if(selection.equals("1")) {
					displayInventory();
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
				
				this.inventoryList.add(new Item(location, name, price, type));
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
			System.exit(1);
		}	
	}
		
	public String displayInventory() {
		for(Item product : inventoryList) {
			System.out.println(product.getLocation() + " | " + product.getName() + "| " + product.getPrice() + " | " + product.getType() + " | " + product.getQuantity());
		}
		return null;
		
	}
	
		
	
	
	
}
