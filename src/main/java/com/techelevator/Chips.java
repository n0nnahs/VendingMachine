package com.techelevator;

public class Chips extends Item {

	public Chips(String type, String name) {
		super(type, name);
	}

	public String message() {
		return "Crunch Crunch, Yum!";
	}
}
