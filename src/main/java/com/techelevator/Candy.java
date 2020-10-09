package com.techelevator;

public class Candy extends Item {

	public Candy(String location, String name, double price, String type) {
		super(location, name, price, type);
	}
	@Override
	public void message() {
		System.out.println("Munch Munch, Yum!");
		System.out.println();
	}

}
