package rocketWizard;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.util.*;

public class SQLDBModel {

	/*
	 * Class Instance Variables
	 * 
	 */
	
	private Connection 			connection;
	private Statement 			statement;
	private PreparedStatement 	preparedStatement;
	private ResultSet 			resultSet;
	
	/*
	 * Class Constants
	 * 
	 */
	
	public final String			SQL_SERVER 							= "localhost";
	public final String			PARTS_DATABASE 						= "rocketparts";
	public final String			DEFAULT_USER_NAME					= "root";
	public final String			DEFAULT_PASSWORD					= "root";
	
	public static final String	NOSE_CONES_TABLE 					= "NOSE_CONES";
	public static final String	NOSE_CONE_MANUFACTURER_ID			= "MANUFACTURER_ID";
	public static final String	NOSE_CONE_PART_NUMBER				= "PART_NUMBER";
	public static final String	NOSE_CONE_MODEL_NAME				= "MODEL_NAME";
	public static final String	NOSE_CONE_INNER_DIAMETER_ENGLISH 	= "INNER_DIAMETER_ENGLISH";
	public static final String	NOSE_CONE_OUTER_DIAMETER_ENGLISH 	= "OUTER_DIAMETER_ENGLISH";
	public static final String	NOSE_CONE_NOSE_LENGTH_ENGLISH 		= "NOSE_LENGTH_ENGLISH";
	public static final String	NOSE_CONE_SHOULDER_LENGTH_ENGLISH 	= "SHOULDER_LENGTH_ENGLISH";
	public static final String	NOSE_CONE_WEIGHT_ENGLISH 			= "WEIGHT_ENGLISH";
	public static final String	NOSE_CONE_INNER_DIAMETER_METRIC 	= "INNER_DIAMETER_METRIC";
	public static final String	NOSE_CONE_OUTER_DIAMETER_METRIC 	= "OUTER_DIAMETER_METRIC";
	public static final String	NOSE_CONE_NOSE_LENGTH_METRIC 		= "NOSE_LENGTH_METRIC";
	public static final String	NOSE_CONE_SHOULDER_LENGTH_METRIC 	= "SHOULDER_LENGTH_METRIC";
	public static final String	NOSE_CONE_WEIGHT_METRIC 			= "WEIGHT_METRIC";
	public static final String	NOSE_CONE_MATERIAL_ID 				= "MATERIAL_ID";
	public static final String	NOSE_CONE_DATE_ADDED 				= "DATE_ADDED";
	public static final String	NOSE_CONE_DATE_LAST_UPDATED 		= "DATE_LAST_UPDATED";
	public static final String	NOSE_CONE_ACTIVE 					= "ACTIVE";
	
	/*
	 * Constructors
	 * 
	 */
	
	public SQLDBModel() {
		
	}
	
	/*
	 * Connect
	 * 
	 */
	
	public boolean connect() {
	
		boolean connected = false;
		
		// Locate the JDBC
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("SQLDatabase::connect(): MySQL JDBC Driver found.");
			
			// Connect to the MySQL Server
			try {
				
				connection = DriverManager.getConnection("jdbc:mysql://" + SQL_SERVER + "/" + PARTS_DATABASE + "?", DEFAULT_USER_NAME, DEFAULT_PASSWORD);
				System.out.println("SQLDatabase::connect(): MySQL Server connected.");
				connected = true;
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::connect(): MySQL Server not connected.");
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("SQLDatabase::connect(): MySQL JDBC Driver not found.");
		}
		
		return connected;
	}
	
	/*
	 * Disconnect
	 * 
	 */
	
	public void disconnect() {
	
		if (connection != null) {
			
			// No need for a try/catch. No SQL exception thrown if MySQL Server is down.
			try{
				connection.close();
				connection = null;
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::disconnect(): Unable to disconnect from MySQL Server.");
			}
		}
		else {
			System.out.println("SQLDatabase::disconnect(): System error. Null connection to MySQL Server.");
		}
	}
	
	/*
	 * Generic CRUD Operations
	 * 
	 */
	
	public boolean add(String tableName, List<SQLDBColModel> model) {
		
		boolean added = false;
		
		if (connect()) {

			try {
				statement = connection.createStatement();
				
				String insertQuery = "INSERT INTO " + PARTS_DATABASE + "." + NOSE_CONES_TABLE;
				
				/*
				// Must use weird symbol on tilde key for columnNames.
				String columnNames = " (";
				
				for (int index = 0; index < model.size() - 1; index++) {
					columnNames = columnNames + "`" + model.get(index).getSqlDBColName().toString() + "`" + ", ";
				}
				
				columnNames = columnNames + "`" + model.get(model.size() - 1).getSqlDBColName() + "`" + ")";
				*/

				// Must use apostrophe for values.
				String queryValues = " VALUES(";
				
				for (int index = 0; index < model.size() - 1; index++) {
					queryValues = queryValues + "'" + model.get(index).getSqlDBColValue().toString() + "'" + ", ";
				}
				
				queryValues = queryValues + "'" + model.get(model.size() - 1).getSqlDBColValue().toString() + "'" + ")";
				// insertQuery = insertQuery + columnNames + queryValues;
				
				insertQuery = insertQuery + queryValues;
				
				System.out.println(insertQuery);
				
				// Must call executeUpdate for insert
				statement.executeUpdate(insertQuery);
				added = true;
				disconnect();
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::addNoseCone(): MySQL INSERT INTO " + tableName + " table failed.");
				disconnect();
			}
		}
		
		return added;
	}
	
	public boolean addNoseCone(String tableName, NoseConeModel model) {
		
		boolean added = false;
		
		if (connect()) {

			try {
				statement = connection.createStatement();
				
				String selectQuery = "INSERT INTO " + PARTS_DATABASE + "." + NOSE_CONES_TABLE + " VALUES(" + 
																											model.getManufacturerID() + 
									 																		model.getPartNumber() +
									 																		model.getModelName() + 
									 																		model.getInnerDiameterEnglish() + 
									 																		model.getOuterDiameterEnglish() + 
									 																		model.getNoseLengthEnglish() + 
									 																		model.getShoulderLengthEnglish() + 
									 																		model.getWeightEnglish() + 
									 																		model.getInnerDiameterMetric() + 
									 																		model.getOuterDiameterMetric() + 
									 																		model.getNoseLengthMetric() + 
									 																		model.getShoulderLengthMetric() + 
									 																		model.getWeightEnglish() + 
									 																		model.getMaterialID() + 
									 																		model.getDateAdded() + 
									 																		model.getDateLastUpdated() + 
									 																		model.isActive() + ")";
				statement.executeQuery(selectQuery);
				added = true;
				disconnect();
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::addNoseCone(): MySQL INSERT INTO " + tableName + " failed. ManufacturerID: " + model.getManufacturerID() + " Part Number: " + model.getPartNumber() + " Model Name: " + model.getModelName());
				disconnect();
			}
		}
		
		return added;
	}
	
	public List<Object> get(String tableName) {
		
		// Build a list of SQL table rows from the SQL ResultSet. Each "row" will contain a list of SQL table column objects.
		// It is up to each Model to unwrap its own "row" and cast the column values to their original types.
		List<Object> list = new LinkedList<Object>();
		
		if (connect()) {

			try {
				statement = connection.createStatement();
				
				String selectQuery = "SELECT * FROM " + PARTS_DATABASE + "." + tableName + " WHERE ACTIVE = 1";
				resultSet = statement.executeQuery(selectQuery);

				// Should not be necessary. resultSet.getRow() returns 0.
				//resultSet.beforeFirst();
				
				/*
				 * How to get the number of rows in the ResultSet
				 * resultSet.last();
				 * int numberOfRows = resultSet.getRow();
				 * resultSet.beforeFirst();
				*/

				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			    int numberOfCols 					= resultSetMetaData.getColumnCount();				
			    			    
			    // For each ResultSet SQL table row
				while (resultSet.next()) {

					SQLDBRowModel sqlTableRow = new SQLDBRowModel();
					
					// Retrieve each of its columns and build a list of SQL table column objects
					for (int col = 1; col <= numberOfCols; col++) {

						// Do not use getArray(), exception occurs, not implemented in MySQL
						String sqlColumnName	= resultSetMetaData.getColumnName(col);
						Object sqlColumnValue 	= resultSet.getObject(col);

						// Add the current column to the list of SQL table columns for the current SQL table row
						sqlTableRow.add(sqlColumnName, sqlColumnValue);
					}

					// Add the current SQL table row to the list of SQL table rows
					list.add(sqlTableRow);
				}
				
				disconnect();
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::get(): MySQL SELECT all rows from " + tableName + " table failed.");
				disconnect();
			}
		}
		
		return list;
	}

	public List<Object> get(String tableName, int manufacturerID, String columnName, String searchValue) {
		
		// Build a list of SQL table rows from the SQL ResultSet. Each "row" will contain a list of SQL table column objects.
		// It is up to each Model to unwrap its own "row" and cast the column values to their original types.
		List<Object> list = new LinkedList<Object>();
		
		if (connect()) {

			try {
				statement = connection.createStatement();
				
				String selectQuery = "SELECT * FROM " + PARTS_DATABASE + "." + NOSE_CONES_TABLE + " WHERE MANUFACTURER_ID = " + manufacturerID + " AND " + columnName + " = '" + searchValue + "' AND ACTIVE = 1";
				resultSet = statement.executeQuery(selectQuery);

				// Should not be necessary. resultSet.getRow() returns 0.
				//resultSet.beforeFirst();
				
				/*
				 * How to get the number of rows in the ResultSet
				 * resultSet.last();
				 * int numberOfRows = resultSet.getRow();
				 * resultSet.beforeFirst();
				*/

				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			    int numberOfCols 					= resultSetMetaData.getColumnCount();				
			    			    
			    // For each ResultSet SQL table row
				while (resultSet.next()) {

					SQLDBRowModel sqlTableRow = new SQLDBRowModel();
					
					// Retrieve each of its columns and build a list of SQL table column objects
					for (int col = 1; col <= numberOfCols; col++) {

						// Do not use getArray(), exception occurs, not implemented in MySQL
						String sqlColumnName	= resultSetMetaData.getColumnName(col);
						Object sqlColumnValue 	= resultSet.getObject(col);

						// Add the current column to the list of SQL table columns for the current SQL table row
						sqlTableRow.add(sqlColumnName, sqlColumnValue);
					}

					// Add the current SQL table row to the list of SQL table rows
					list.add(sqlTableRow);
				}
				
				disconnect();
			}
			catch (SQLException e) {
				System.out.println("SQLDatabase::get(): MySQL SELECT from " + tableName + " WHERE MANUFACTURER_ID = " + manufacturerID + " AND " + columnName + " = '" + searchValue + "'" + " failed.");
				disconnect();
			}
		}
		
		return list;
	}
}
