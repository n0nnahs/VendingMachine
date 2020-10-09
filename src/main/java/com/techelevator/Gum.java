package com.techelevator;

public class Gum extends Item {

	public Gum(String location, String name, double price, String type) {
		super(location, name, price, type);
	}
	
	@Override
	public void message() {
		System.out.println("Chew Chew, Yum!");
		System.out.println();
	}


}
