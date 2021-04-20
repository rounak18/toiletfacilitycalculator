package govt.mbie.calculator.pages.calculator.hospital;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import govt.mbie.calculator.base.BasePageObject;
import govt.mbie.calculator.testdata.dataentry.HospitalMeasurementsData;
import govt.mbie.calculator.util.screenshot.ScreenshotUtility;

public class HospitalMeasurementPage extends BasePageObject {

	@FindBy(xpath = "//span[text()='Hospital']")
	private WebElement pageTitle;

	@FindBy(xpath = "//mat-label[text()='Enter applicable measurements to calculate occupant density:']")
	private WebElement intro;

	@FindBy(xpath = "//mat-card-title[contains(text(),'Calculate the number of toilet facilities here')]//ancestor::mat-card[1]")
	private WebElement calculator;

	@FindBy(xpath = "//mat-label[contains(text(),'Dining, beverage and cafeteria spaces')]//ancestor::div[1]//input")
	private WebElement diningAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Interview rooms')]//ancestor::div[1]//input")
	private WebElement interviewAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Kitchens')]//ancestor::div[1]//input")
	private WebElement kitchenAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Laundry and house keeping facilities')]//ancestor::div[1]//input")
	private WebElement houseKeepingAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Lobbies and foyers')]//ancestor::div[1]//input")
	private WebElement lobbieAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Offices and staffrooms')]//ancestor::div[1]//input")
	private WebElement officeAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Personal service facilities')]//ancestor::div[1]//input")
	private WebElement serviceFacilityAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Reception areas')]//ancestor::div[1]//input")
	private WebElement receptionAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Toilets and subordinate spaces (no occupants counted)')]//ancestor::div[1]//input")
	private WebElement subordinatespaceAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Beds')]//ancestor::div[1]//input")
	private WebElement numberOfBedsInputBox;

	@FindBy(css = "#submit")
	private WebElement submitButton;

	public HospitalMeasurementPage() {

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		isLoaded();
		logExtentReport("HospitalMeasurement Page Object Created");
	}

	protected void isLoaded() throws Error {
		waitForElementVisible(pageTitle);
	}

	/**
	 * Method to update and submit occupant density form.
	 * @param hospitalMeasurementsData is object of HospitalToiletFacilityResult data to be addded in the form.
	 * @return object HospitalToiletFacilityResult page.
	 */
	public HospitalToiletFacilityResult calculateOccupantDensity(HospitalMeasurementsData hospitalMeasurementsData) {

		diningAreaInputBox.sendKeys(hospitalMeasurementsData.getDiningArea());
		interviewAreaInputBox.sendKeys(hospitalMeasurementsData.getInterviewArea());
		kitchenAreaInputBox.sendKeys(hospitalMeasurementsData.getKitchenArea());
		houseKeepingAreaInputBox.sendKeys(hospitalMeasurementsData.getHouseKeepingArea());
		lobbieAreaInputBox.sendKeys(hospitalMeasurementsData.getLobbieArea());
		officeAreaInputBox.sendKeys(hospitalMeasurementsData.getOfficeArea());
		serviceFacilityAreaInputBox.sendKeys(hospitalMeasurementsData.getServiceFacilityArea());
		receptionAreaInputBox.sendKeys(hospitalMeasurementsData.getReceptionArea());
		subordinatespaceAreaInputBox.sendKeys(hospitalMeasurementsData.getSubordinatespaceArea());
		numberOfBedsInputBox.sendKeys(hospitalMeasurementsData.getNumberOfBeds());

		ScreenshotUtility.capture(calculator);

		scrollIntoView(serviceFacilityAreaInputBox);

		ScreenshotUtility.captureandclick(submitButton);

		return new HospitalToiletFacilityResult();
	}

}
