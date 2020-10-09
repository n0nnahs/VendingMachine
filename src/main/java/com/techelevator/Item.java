package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {
	
	private int quantity = 5;
	private String location;
	private String name;
	private double price;
	private String type;
	
	public Item(String location, String name, double price, String type) {
		this.location = location;
		this.name = name;
		this.price = price;
		this.type = type;
	}	
	

		
	
	
	
	
	public int getQuantityLeft() {
		return quantity;
	}

	public void dispense(int quantityToRemove) {
		if(quantity - quantityToRemove >=0) {
			quantity -= quantityToRemove;
			System.out.println("Success");
		}else {
			System.out.println("Out of Stock");
		}
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
