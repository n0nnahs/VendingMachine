package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {
	private int quantity = 5;
	private String location;
	private String name;
	private double price;
	private String type;
	private static DecimalFormat df = new DecimalFormat("0.00");

	
	public Item(String location, String name, double price, String type) {
		this.location = location;
		this.name = name;
		this.price = price;
		this.type = type;
	}	

	public int dispense(int quantityToRemove) {
			quantity -= quantityToRemove;
			
			
			System.out.println();
			System.out.println(name + " $" + df.format(price * quantityToRemove));
			message();
			System.out.println();
			
			return quantity;
	}

	public void message() {
		
	}

	//Getters & Setters

	public int getQuantity() {
		return quantity;
	}
	public String getLocation() {
		return location;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getType() {
		return type;
	}








	

}
