package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


public class ItemTest {
	
	@Test
	public void dispense_test() {
		Item objectToTest = new Item("a1", "chip", 1.50, "Chip");
		int actualOutput = objectToTest.dispense(3);
		int expectedOutput = 2; 
		Assert.assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void dispense_test_negative() {
		Item objectToTest = new Item("a1", "chip", 1.50, "Chip");
		int actualOutput = objectToTest.dispense(-1);
		int expectedOutput = 6; 
		Assert.assertEquals(expectedOutput, actualOutput);
	}
	
}
