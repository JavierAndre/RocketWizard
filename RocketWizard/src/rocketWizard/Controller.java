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
	
	public boolean addNoseCone(NoseConeModel model) {
		
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
		
		return sqlDBModel.add(SQLDBModel.NOSE_CONES_TABLE, list);
	}
	
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
