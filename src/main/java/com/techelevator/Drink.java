package com.techelevator;

public class Drink extends Item {

	public Drink(String location, String name, double price, String type) {
		super(location, name, price, type);
	}

	@Override
	public void message() {
		System.out.println("Glug Glug, Yum!");
		System.out.println();
	}

}
