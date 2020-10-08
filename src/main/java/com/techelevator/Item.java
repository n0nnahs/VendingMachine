package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item extends VendingMachine {
	
	private int quantity = 5;
	private String type;
	private String name;
	
	public Item(String type, String name) {
		this.type = type;
		this.name = name;
	}	
	
	public int getQuantityLeft() {
		return quantity;
	}
	
	public String dispense(int quantityToRemove) {
		if(quantity - quantityToRemove >=0) {
			quantity -= quantityToRemove;
			System.out.println("Success" + message());
		}else {
			System.out.println("Out of Stock");
		}
	}
	
	
	//Getters & Setters
	public int getQuantity() {
		return quantity;
	}
	public String getType() {
		return type;
	}
}
