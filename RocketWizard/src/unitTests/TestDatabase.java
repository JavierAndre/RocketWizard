package unitTests;

import java.sql.Date;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import rocketWizard.Controller;
import rocketWizard.SQLDBModel;
import rocketWizard.SQLDBColModel;
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
			System.out.println("3. Test Insert Nose Cone");
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
				case 3:
				{
					addNoseCone();
					break;
				}
				case 9:
				{
					System.out.println("Test(s) performed.\n");
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

	// Create
	public void addNoseCone() {
		
		if (database == null) {
			connect();
		}
		
		System.out.println("Insert Nose Cone test..." + "\n");
		
		NoseConeModel model = new NoseConeModel();
		
		model.setManufacturerID(1);
		model.setPartNumber("20000");
		model.setModelName("PNC-24A");
		model.setInnerDiameterEnglish(0.95);
		model.setOuterDiameterEnglish(0.978);
		model.setNoseLengthEnglish(3.0);
		model.setShoulderLengthEnglish(0.75);
		model.setWeightEnglish(0.16);
		model.setInnerDiameterMetric(24.1);
		model.setOuterDiameterMetric(24.8);
		model.setNoseLengthMetric(76.0);
		model.setShoulderLengthMetric(19.0);
		model.setWeightMetric(4.6);
		model.setMaterialID(1);
		model.setDateAdded(new Date(Calendar.getInstance().getTimeInMillis()));
		model.setDateLastUpdated(new Date(Calendar.getInstance().getTimeInMillis()));
		model.setActive(true);

		List<SQLDBColModel> list = new LinkedList<SQLDBColModel>();
		
		SQLDBColModel colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_MANUFACTURER_ID, model.getManufacturerID());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_PART_NUMBER, model.getPartNumber());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_MODEL_NAME, model.getModelName());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_INNER_DIAMETER_ENGLISH, model.getInnerDiameterEnglish());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_OUTER_DIAMETER_ENGLISH, model.getOuterDiameterEnglish());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_NOSE_LENGTH_ENGLISH, model.getNoseLengthEnglish());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_SHOULDER_LENGTH_ENGLISH, model.getShoulderLengthEnglish());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_WEIGHT_ENGLISH, model.getWeightEnglish());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_INNER_DIAMETER_METRIC, model.getInnerDiameterMetric());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_OUTER_DIAMETER_METRIC, model.getOuterDiameterMetric());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_NOSE_LENGTH_METRIC, model.getNoseLengthMetric());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_SHOULDER_LENGTH_METRIC, model.getShoulderLengthMetric());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_WEIGHT_METRIC, model.getWeightMetric());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_MATERIAL_ID, model.getMaterialID());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_DATE_ADDED, model.getDateAdded());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_DATE_LAST_UPDATED, model.getDateLastUpdated());
		list.add(colModel);
		colModel = new SQLDBColModel(SQLDBModel.NOSE_CONE_ACTIVE, (model.isActive()? 1 : 0));
		list.add(colModel);
		
		if (controller.addNoseCone(list)) {
			System.out.println("\nNose Cone " + model.getModelName() + " added." + "\n");
		}
		else {
			System.out.println("\nNose Cone " + model.getModelName() + " could not be added." + "\n");
		}
	}
	
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
