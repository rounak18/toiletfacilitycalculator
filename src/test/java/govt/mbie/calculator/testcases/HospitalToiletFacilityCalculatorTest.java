package govt.mbie.calculator.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import govt.mbie.calculator.base.TestBase;
import govt.mbie.calculator.workflow.HospitalToiletFacilityWorkFlow;

public class HospitalToiletFacilityCalculatorTest extends TestBase {

	
	private String RESULT_GENRATED_MESSAGE_SUCCESS = "Calculated number of toilet facilities";
	
	/**
	 * Test to verify Toilet Calculator generate ToiletFacility result for Hospital with people count unknown.
	 * Verify submit, report generation, save and print pdf and verify pdf report functionality.
	 */	
	@Test(priority = 1)
	private void printSaveAndCompareResult_ToiletFacilityCalculator_Hospital() throws Exception {

        /* Verify report generation and save and print report in pdf .*/
		Assert.assertEquals(HospitalToiletFacilityWorkFlow.getResultGenratedMessage(), RESULT_GENRATED_MESSAGE_SUCCESS);
        /* Verify saved pdf report against master.*/
		Assert.assertEquals(HospitalToiletFacilityWorkFlow.compareResultWithSource(), true);

	}


}