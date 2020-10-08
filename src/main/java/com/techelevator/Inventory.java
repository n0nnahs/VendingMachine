package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inventory extends VendingMachine {
	
	private String name;
	private double price;
	private int quantity = 5;
	private String slot;
	
	public Inventory(String name, double price, String slot) {
		this.name = name;
		this.price = price;
		this.slot = slot;
	}
	
public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(new File("vendingmachine.csv"));
		sc.useDelimiter(",");
		while(sc.hasNext()) {
			System.out.println(sc.next());
		}
		sc.close();
	}
	
	
	//Getters & Setters
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getSlot() {
		return slot;
	}
}
