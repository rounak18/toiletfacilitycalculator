package govt.mbie.calculator.workflow;

import java.awt.AWTException;
import java.io.IOException;

import govt.mbie.calculator.pages.calculator.ToiletCalculatorHomePage;
import govt.mbie.calculator.pages.calculator.hospital.HospitalMeasurementPage;
import govt.mbie.calculator.pages.calculator.hospital.HospitalToiletFacilityResult;
import govt.mbie.calculator.testdata.dataentry.HospitalMeasurementsData;
import govt.mbie.calculator.util.testhelper.DelteFileHelper;
import govt.mbie.calculator.util.testhelper.PdfComparisonHelper;

public class HospitalToiletFacilityWorkFlow {

	private static ToiletCalculatorHomePage toiletCalculatorHomePage;
	private static HospitalMeasurementsData hospitalMeasurementsData;
	private static HospitalMeasurementPage hospitalMeasurementPage;
	private static HospitalToiletFacilityResult hospitalToiletFacilityResult;

	private HospitalToiletFacilityWorkFlow() {

	}

	static {
		toiletCalculatorHomePage = new ToiletCalculatorHomePage();
		hospitalMeasurementsData = new HospitalMeasurementsData();
	}

	/**
	 * Method to update and submit measurement for unknown count of people in Hospital facility.
	 * @return object of HospitalToiletFacilityResult Page.
	 */
	private static HospitalToiletFacilityResult updateAndSubmitMeasurementForUnknownCount() throws IOException {
		
		DelteFileHelper
		.deletePdf("pdfgenrated\\hospital");

		hospitalMeasurementPage = toiletCalculatorHomePage
				.selectRadiobuttonUnKnownCount()
				.selectBuildingUse("Hospital");

		return hospitalToiletFacilityResult = hospitalMeasurementPage
				.calculateOccupantDensity(hospitalMeasurementsData);
	}

	/**
	 * Method to print and save generated report in pdf for unknown count of people in Hospital facility.
	 * @return object of HospitalToiletFacilityResult Page.
	 */
	private static HospitalToiletFacilityResult printAndSaveResult() throws AWTException {
		return hospitalToiletFacilityResult
				.printandSaveResultAsPdf("hospital\\HospitalToiletCalculatorResult_Genrated");
	}

	/**
	 * Method to get result generated message confirmation heading for unknown count of people in Hospital facility.
	 * @return String of result generated message.
	 */
	public static String getResultGenratedMessage() throws AWTException, IOException {

		updateAndSubmitMeasurementForUnknownCount();
		printAndSaveResult();
		return hospitalToiletFacilityResult.getResultHeading();
	}

	/**
	 * Method to compare and match pdf result generated against master for unknown count of people in Hospital facility.
	 * @return boolean of compare result.
	 */
	public static boolean compareResultWithSource() throws Exception {

		return PdfComparisonHelper.comparePdf("hospital\\HospitalToiletCalculatorResult_Source.pdf",
				"hospital\\HospitalToiletCalculatorResult_Genrated.pdf",
				"hospital\\HospitalToiletCalculatorResult_Comparison");
	}
}
