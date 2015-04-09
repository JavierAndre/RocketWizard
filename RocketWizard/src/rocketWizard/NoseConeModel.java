package rocketWizard;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NoseConeModel {

	/*
	 * Class Instance Variables
	 * 
	 */

	private int			manufacturerID;
	private String 		partNumber;
	private String		modelName;
	
	private double		innerDiameterEnglish;
	private double		outerDiameterEnglish;
	private double		noseLengthEnglish;
	private	double		shoulderLengthEnglish;
	private double		weightEnglish;
	
	private double		innerDiameterMetric;
	private double		outerDiameterMetric;
	private double		noseLengthMetric;
	private	double		shoulderLengthMetric;
	private double		weightMetric;
	
	private int			materialID;
	
	private Date		dateAdded;
	private Date		dateLastUpdated;
	private boolean		active;
	
	/*
	 * Class Constants
	 * 
	 */
	
	public final String	NOSE_CONES_TABLE	= "NOSE_CONES";
	
	/*
	 * Getters and Setters
	 * 
	 */
	
	public int getManufacturerID() {
		return manufacturerID;
	}

	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public double getInnerDiameterEnglish() {
		return innerDiameterEnglish;
	}

	public void setInnerDiameterEnglish(double innerDiameterEnglish) {
		this.innerDiameterEnglish = innerDiameterEnglish;
	}

	public double getOuterDiameterEnglish() {
		return outerDiameterEnglish;
	}

	public void setOuterDiameterEnglish(double outerDiameterEnglish) {
		this.outerDiameterEnglish = outerDiameterEnglish;
	}

	public double getNoseLengthEnglish() {
		return noseLengthEnglish;
	}

	public void setNoseLengthEnglish(double noseLengthEnglish) {
		this.noseLengthEnglish = noseLengthEnglish;
	}

	public double getShoulderLengthEnglish() {
		return shoulderLengthEnglish;
	}

	public void setShoulderLengthEnglish(double shoulderLengthEnglish) {
		this.shoulderLengthEnglish = shoulderLengthEnglish;
	}

	public double getWeightEnglish() {
		return weightEnglish;
	}

	public void setWeightEnglish(double weightEnglish) {
		this.weightEnglish = weightEnglish;
	}

	public double getInnerDiameterMetric() {
		return innerDiameterMetric;
	}

	public void setInnerDiameterMetric(double innerDiameterMetric) {
		this.innerDiameterMetric = innerDiameterMetric;
	}

	public double getOuterDiameterMetric() {
		return outerDiameterMetric;
	}

	public void setOuterDiameterMetric(double outerDiameterMetric) {
		this.outerDiameterMetric = outerDiameterMetric;
	}

	public double getNoseLengthMetric() {
		return noseLengthMetric;
	}

	public void setNoseLengthMetric(double noseLengthMetric) {
		this.noseLengthMetric = noseLengthMetric;
	}

	public double getShoulderLengthMetric() {
		return shoulderLengthMetric;
	}

	public void setShoulderLengthMetric(double shoulderLengthMetric) {
		this.shoulderLengthMetric = shoulderLengthMetric;
	}

	public double getWeightMetric() {
		return weightMetric;
	}

	public void setWeightMetric(double weightMetric) {
		this.weightMetric = weightMetric;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/*
	 * Constructors
	 * 
	 */
	
	public NoseConeModel() {

		manufacturerID			= 0;
		partNumber				= "";
		modelName				= "";
		innerDiameterEnglish	= 0.0;
		outerDiameterEnglish	= 0.0;
		noseLengthEnglish		= 0.0;
		shoulderLengthEnglish	= 0.0;
		weightEnglish			= 0.0;
		innerDiameterMetric		= 0.0;
		outerDiameterMetric		= 0.0;
		noseLengthMetric		= 0.0;
		shoulderLengthMetric	= 0.0;
		weightMetric			= 0.0;
		materialID				= 0;
		dateAdded				= new Date(0);
		dateLastUpdated			= new Date(0);
		active					= false;
	}

	public NoseConeModel(List<SQLDBColModel> list) {

		for (int index = 0; index < list.size(); index++) {
			
			switch (list.get(index).getSqlDBColName()) {
			
				case SQLDBModel.NOSE_CONE_MANUFACTURER_ID: {
					manufacturerID = (int)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_PART_NUMBER: {
					partNumber = (String)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_MODEL_NAME: {
					modelName = (String)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_INNER_DIAMETER_ENGLISH: {
					innerDiameterEnglish = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_OUTER_DIAMETER_ENGLISH: {
					outerDiameterEnglish = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_NOSE_LENGTH_ENGLISH: {
					noseLengthEnglish = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_SHOULDER_LENGTH_ENGLISH: {
					shoulderLengthEnglish = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_WEIGHT_ENGLISH: {
					weightEnglish = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_INNER_DIAMETER_METRIC: {
					innerDiameterMetric = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_OUTER_DIAMETER_METRIC: {
					outerDiameterMetric = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_NOSE_LENGTH_METRIC: {
					noseLengthMetric = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_SHOULDER_LENGTH_METRIC: {
					shoulderLengthMetric = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_WEIGHT_METRIC: {
					weightMetric = (double)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_MATERIAL_ID: {
					materialID = (int)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_DATE_ADDED: {
					dateAdded = (Date)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_DATE_LAST_UPDATED: {
					dateLastUpdated = (Date)list.get(index).getSqlDBColValue();
					break;
				}
				case SQLDBModel.NOSE_CONE_ACTIVE: {
					active = (boolean)list.get(index).getSqlDBColValue();
					break;
				}
			}
		}
	}
}
