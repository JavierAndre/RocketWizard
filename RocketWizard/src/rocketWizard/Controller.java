package rocketWizard;

import java.util.LinkedList;
import java.util.List;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

	/*
	 * Class Instance Variables
	 * 
	 */
	
	private SQLDBModel	sqlDBModel;
	
	/*
	 * Constructor
	 * 
	 */
	
	public Controller() {

		sqlDBModel = new SQLDBModel();		
	}
	
	/*
	 * Getters
	 * 
	 */
	
	public SQLDBModel getSQLDBModel() {
		return sqlDBModel;
	}
	
	/*
	 * Nose Cones
	 * 
	 */
	
	public LinkedList<NoseConeModel> getNoseCones() {
		
		LinkedList<NoseConeModel> noseConeList 	= new LinkedList<NoseConeModel>();
		List<Object> sqlTableRows				= sqlDBModel.get(SQLDBModel.NOSE_CONES_TABLE);
		
		for (int index = 0; index < sqlTableRows.size(); index++) {

			// Extract the current SQL table row
			Object sqlTableRow = sqlTableRows.get(index);

			// Fill in the current Nose Cone Model
			NoseConeModel noseCone 	= new NoseConeModel(((SQLDBRowModel) sqlTableRow).getRow());

			// Add the current Nose Cone Model to the list
			noseConeList.add(noseCone);
		}
		
		return noseConeList;
	}
	
	public LinkedList<NoseConeModel> getNoseCones(int manufacturerID, String columnName, String columnValue) {
		
		LinkedList<NoseConeModel> noseConeList 	= new LinkedList<NoseConeModel>();
		List<Object> sqlTableRows				= sqlDBModel.get(SQLDBModel.NOSE_CONES_TABLE, manufacturerID, columnName, columnValue);
		
		for (int index = 0; index < sqlTableRows.size(); index++) {

			// Extract the current SQL table row
			Object sqlTableRow = sqlTableRows.get(index);

			// Fill in the current Nose Cone Model
			NoseConeModel noseCone 	= new NoseConeModel(((SQLDBRowModel) sqlTableRow).getRow());

			// Add the current Nose Cone Model to the list
			noseConeList.add(noseCone);
		}
		
		return noseConeList;
	}
}
