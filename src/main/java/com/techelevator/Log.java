package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public String action;
	public String name;
	public double startingAmount;
	public double endingAmount;
	public String location;
	private static DecimalFormat df = new DecimalFormat("0.00");

	
	
	public Log(String action, double startingAmount, double endingAmount) throws IOException {
		this.action = action;
		this.startingAmount = startingAmount;
		this.endingAmount = endingAmount;
		
		File log = new File("log.txt");
		if(!log.exists()) {
			try {
				log.createNewFile();
			}
			catch(IOException e){
				System.out.println("IO Exception");
			}
		}
		writeToLogAction();
	}
	
	
	public Log(String name, String location, double startingAmount, double endingAmount) throws IOException {
		this.name = name;
		this.location = location;
		this.startingAmount = startingAmount;
		this.endingAmount = endingAmount;
		
		File log = new File("log.txt");
		if(!log.exists()) {
			try {
				log.createNewFile();
			}
			catch(IOException e){
				System.out.println("IO Exception");
			}
		}
		writeToLogDispense();
	}
	
	
	
	
	
	
	
	
	//getter & setters
	public String getAction() {
		return action;
	}
	public double getStartingAmount() {
		return startingAmount;
	}
	public double getEndingAmount() {
		return endingAmount;
	}
	public String getLocation() {
		return location;
	}
	public void setStartingAmount(double startingAmount) {
		this.startingAmount = startingAmount;
	}
	public void setEndingAmount(double endingAmount) {
		this.endingAmount = endingAmount;
	}
	
	
	 
	
	private void writeToLogAction() throws IOException  {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		try(FileWriter myFileWriter = new FileWriter("log.txt", true); //open for appending instead of rewriting
				PrintWriter myPrintWriter = new PrintWriter(myFileWriter)) {
				myPrintWriter.println(dateString + " " + action + " $" + df.format(startingAmount) + " $" + df.format(endingAmount));
			}
		}	
	
	private void writeToLogDispense() throws IOException  {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		try(FileWriter myFileWriter = new FileWriter("log.txt", true); //open for appending instead of rewriting
				PrintWriter myPrintWriter = new PrintWriter(myFileWriter)) {
				myPrintWriter.println(dateString + " " + name + " " + location + " $" + df.format(startingAmount) + " $" + df.format(endingAmount));
			}
		}	
}
