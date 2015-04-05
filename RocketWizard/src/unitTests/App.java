package unitTests;

import rocketWizard.Controller;

public class App {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		TestDatabase testDataBase = new TestDatabase(controller);
		testDataBase.menu();
	}
}
