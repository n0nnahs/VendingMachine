package com.techelevator;

public class Chip extends Item {



	public Chip(String location, String name, double price, String type) {
		super(location, name, price, type);
	}

	public String message() {
		return "Crunch Crunch, Yum!";
	}
}
