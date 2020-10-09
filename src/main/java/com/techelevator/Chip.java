package com.techelevator;

public class Chip extends Item {


	public Chip(String location, String name, double price, String type) {
		super(location, name, price, type);
		
	}

	@Override
	public void message() {
		System.out.println("Crunch Crunch, Yum!");
		System.out.println();
	}

}
