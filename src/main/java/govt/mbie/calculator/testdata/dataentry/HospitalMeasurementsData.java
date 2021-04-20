package govt.mbie.calculator.testdata.dataentry;

import govt.mbie.calculator.util.datautil.TestDataUtil;

public class HospitalMeasurementsData {

	private String diningArea = TestDataUtil.readJsonFile("HospitalMeasurements", "diningArea");;
	private String interviewArea = TestDataUtil.readJsonFile("HospitalMeasurements", "interviewArea");;
	private String kitchenArea = TestDataUtil.readJsonFile("HospitalMeasurements", "kitchenArea");;
	private String houseKeepingArea= TestDataUtil.readJsonFile("HospitalMeasurements", "houseKeepingArea");;
	private String lobbieArea= TestDataUtil.readJsonFile("HospitalMeasurements", "lobbieArea");;
	private String officeArea= TestDataUtil.readJsonFile("HospitalMeasurements", "officeArea");;
	private String serviceFacilityArea = TestDataUtil.readJsonFile("HospitalMeasurements", "serviceFacilityArea");;
	private String receptionArea= TestDataUtil.readJsonFile("HospitalMeasurements", "receptionArea");;
	private String subordinatespaceArea= TestDataUtil.readJsonFile("HospitalMeasurements", "subordinatespaceArea");;
	private String numberOfBeds = TestDataUtil.readJsonFile("HospitalMeasurements", "numberOfBeds");;


	public String getDiningArea() {
		return diningArea;
	}

	public String getInterviewArea() {
		return interviewArea;
	}

	public String getKitchenArea() {
		return kitchenArea;
	}

	public String getHouseKeepingArea() {
		return houseKeepingArea;
	}

	public String getLobbieArea() {
		return lobbieArea;
	}

	public String getOfficeArea() {
		return officeArea;
	}

	public String getServiceFacilityArea() {
		return serviceFacilityArea;
	}

	public String getReceptionArea() {
		return receptionArea;
	}

	public String getSubordinatespaceArea() {
		return subordinatespaceArea;
	}
	
	public String getNumberOfBeds() {
		return numberOfBeds;
	}

}
