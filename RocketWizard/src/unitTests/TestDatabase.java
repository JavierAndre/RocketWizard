package unitTests;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import rocketWizard.Controller;
import rocketWizard.SQLDBModel;
import rocketWizard.NoseConeModel;

public class TestDatabase {

	private Controller controller;
	private SQLDBModel database;
	
	public TestDatabase(Controller controller) {
		this.controller = controller;
		database = new SQLDBModel();
	}
	
	public void menu() {
	
		int option 		 = 0;
		Scanner keyboard = new Scanner(System.in);
		
		while (option != 9) {
			
			System.out.println("1. Test connection to " + database.PARTS_DATABASE + " Database");
			System.out.println("2. Test Select all Nose Cones");
			System.out.println("9. Exit");
	
			try {
				System.out.print("\nEnter your Menu choice: ");
				option = keyboard.nextInt();
				System.out.println();
			}
			catch (InputMismatchException e) {
				keyboard.nextLine();
			}
			
			switch (option) {
			
				case 1: 
				{
					connect();
					break;
				}
				case 2:
				{
					getNoseCones();
					break;
				}
				case 9:
				{
					break;
				}
				default:
				{
					System.out.println("Invalid Menu Option.\n");
					break;
				}
			}
		}
	}
	
	public boolean connect() {
		
		boolean connected = false;
		
		if (database.connect()) {
		
			System.out.println("Connected to the RocketParts database." + "\n");
		}
		else {
			System.out.println("Unable to connect to the RocketParts database." + "\n");
		}
		
		return connected;
	}
	
	/*
	 * CRUD (Create, Read, Update, Delete) Operations 
	 */

	// Read
	public void getNoseCones() {
		
		if (database == null) {
			connect();
		}

		LinkedList<NoseConeModel> list = controller.getNoseCones();
		
		System.out.println("\nSelect All Nose Cones test...");

		if (list.size() > 0) {
		
			for (NoseConeModel noseCone : list) {
				System.out.println("\nManufacturer ID: \t\t" + noseCone.getManufacturerID());
				System.out.println("Model Number: \t\t\t" + noseCone.getPartNumber());
				System.out.println("Model Name: \t\t\t" + noseCone.getModelName());
				
				System.out.println("Inner Diameter (English): \t" + noseCone.getInnerDiameterEnglish());
				System.out.println("Outer Diameter (English): \t" + noseCone.getOuterDiameterEnglish());
				System.out.println("Nose Length (English): \t\t" + noseCone.getNoseLengthEnglish());
				System.out.println("Shoulder Length (English): \t" + noseCone.getShoulderLengthEnglish());
				System.out.println("Weight (English): \t\t" + noseCone.getWeightEnglish());
	
				System.out.println("Inner Diameter (Metric): \t" + noseCone.getInnerDiameterMetric());
				System.out.println("Outer Diameter (Metric): \t" + noseCone.getOuterDiameterMetric());
				System.out.println("Nose Length (Metric): \t\t" + noseCone.getNoseLengthMetric());
				System.out.println("Shoulder Length (Metric): \t" + noseCone.getShoulderLengthMetric());
				System.out.println("Weight (Netric): \t\t" + noseCone.getWeightMetric());
				
				System.out.println("Material: \t\t\t" + noseCone.getMaterialID());
				
				System.out.println("Date Added: \t\t\t" + noseCone.getDateAdded());
				System.out.println("Date Last Updated: \t\t" + noseCone.getDateLastUpdated());
				System.out.println("Active: \t\t\t" + (noseCone.isActive() ? "Yes" : "No"));
			}
		}
		else {
			System.out.println("\nTestDatabase::getNoseCones(): the Nose Cones table is empty or MySQL Server is not connected.");
		}
		
		System.out.println("\nSelect a Nose Cone by Manufacturer Id and Part Number test...\n");
		
		list = controller.getNoseCones(1, SQLDBModel.NOSE_CONE_PART_NUMBER, "19800");
		
		if (list.size() > 0) {
			
			for (NoseConeModel noseCone : list) {
				System.out.println("\nManufacturer ID: \t\t" + noseCone.getManufacturerID());
				System.out.println("Model Number: \t\t\t" + noseCone.getPartNumber());
				System.out.println("Model Name: \t\t\t" + noseCone.getModelName());
				
				System.out.println("Inner Diameter (English): \t" + noseCone.getInnerDiameterEnglish());
				System.out.println("Outer Diameter (English): \t" + noseCone.getOuterDiameterEnglish());
				System.out.println("Nose Length (English): \t\t" + noseCone.getNoseLengthEnglish());
				System.out.println("Shoulder Length (English): \t" + noseCone.getShoulderLengthEnglish());
				System.out.println("Weight (English): \t\t" + noseCone.getWeightEnglish());
	
				System.out.println("Inner Diameter (Metric): \t" + noseCone.getInnerDiameterMetric());
				System.out.println("Outer Diameter (Metric): \t" + noseCone.getOuterDiameterMetric());
				System.out.println("Nose Length (Metric): \t\t" + noseCone.getNoseLengthMetric());
				System.out.println("Shoulder Length (Metric): \t" + noseCone.getShoulderLengthMetric());
				System.out.println("Weight (Netric): \t\t" + noseCone.getWeightMetric());
				
				System.out.println("Material: \t\t\t" + noseCone.getMaterialID());
				
				System.out.println("Date Added: \t\t\t" + noseCone.getDateAdded());
				System.out.println("Date Last Updated: \t\t" + noseCone.getDateLastUpdated());
				System.out.println("Active: \t\t\t" + (noseCone.isActive() ? "Yes" : "No"));
			}
		}
		else {
			System.out.println("\nTestDatabase::getNoseCones(): Nose Cone with Part Number 19800 not found or MySQL Server is not connected.");
		}
		
		System.out.println("\nSelect a Nose Cone by Manufacturer Id and Model Name test...\n");
		
		list = controller.getNoseCones(1, SQLDBModel.NOSE_CONE_MODEL_NAME, "PNC-13A");
		
		if (list.size() > 0) {
			
			for (NoseConeModel noseCone : list) {
				System.out.println("\nManufacturer ID: \t\t" + noseCone.getManufacturerID());
				System.out.println("Model Number: \t\t\t" + noseCone.getPartNumber());
				System.out.println("Model Name: \t\t\t" + noseCone.getModelName());
				
				System.out.println("Inner Diameter (English): \t" + noseCone.getInnerDiameterEnglish());
				System.out.println("Outer Diameter (English): \t" + noseCone.getOuterDiameterEnglish());
				System.out.println("Nose Length (English): \t\t" + noseCone.getNoseLengthEnglish());
				System.out.println("Shoulder Length (English): \t" + noseCone.getShoulderLengthEnglish());
				System.out.println("Weight (English): \t\t" + noseCone.getWeightEnglish());
	
				System.out.println("Inner Diameter (Metric): \t" + noseCone.getInnerDiameterMetric());
				System.out.println("Outer Diameter (Metric): \t" + noseCone.getOuterDiameterMetric());
				System.out.println("Nose Length (Metric): \t\t" + noseCone.getNoseLengthMetric());
				System.out.println("Shoulder Length (Metric): \t" + noseCone.getShoulderLengthMetric());
				System.out.println("Weight (Netric): \t\t" + noseCone.getWeightMetric());
				
				System.out.println("Material: \t\t\t" + noseCone.getMaterialID());
				
				System.out.println("Date Added: \t\t\t" + noseCone.getDateAdded());
				System.out.println("Date Last Updated: \t\t" + noseCone.getDateLastUpdated());
				System.out.println("Active: \t\t\t" + (noseCone.isActive() ? "Yes" : "No"));
			}
		}
		else {
			System.out.println("\nTestDatabase::getNoseCones(): Nose Cone with Model Name PNC-13A not found or MySQL Server is not connected.");
		}
		
		System.out.println();
	}	
}
