package govt.mbie.calculator.pages.calculator;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import govt.mbie.calculator.base.BasePageObject;
import govt.mbie.calculator.pages.calculator.hospital.HospitalMeasurementPage;
import govt.mbie.calculator.pages.calculator.museum.MuseumMeasurementPage;
import govt.mbie.calculator.util.screenshot.ScreenshotUtility;

public class ToiletCalculatorHomePage extends BasePageObject {

	@FindBy(xpath = "//h1[text()='Toilet calculator']")
	private WebElement pageTitle;

	@FindBy(css = ".intro-text")
	private WebElement introduction;

	@FindBy(xpath = "//iframe[@src='https://msg-tc-spa-as-prd.azurewebsites.net/']")
	private WebElement calculatoriframe;
	
	@FindBy(xpath = "//mat-card-title[text()='Calculate the number of toilet facilities here']")
	private WebElement calculatorHeading;

	@FindBy(xpath = "//label[@for='countKnownNo-input']")
	private WebElement countKnownNo;

	@FindBy(css = "#countKnownYes")
	private WebElement countKnownYes;

	@FindBy(css = "#buildingUse")
	private WebElement selectDropDownButton;

	@FindBy(css = "#occupantCount")
	private WebElement numberOfPeopleInputBox;

	@FindBy(xpath = "//mat-option[contains(@id,'mat-option')]")
	private List<WebElement> listOfBuildingType;

	public ToiletCalculatorHomePage() {

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		isLoaded();
		logExtentReport("ToiletCalculatorHome age Object Created");
	}

	protected void isLoaded() throws Error {
		waitForElementVisible(pageTitle);
	}

	/**
	 * Method to Select Unknown Count of People Radio Box.
	 */
	public ToiletCalculatorHomePage selectRadiobuttonUnKnownCount() {
		ScreenshotUtility.capture(pageTitle);
		switchtoFrame(calculatoriframe);
		scrollIntoView(calculatorHeading);
		ScreenshotUtility.captureandclick(countKnownNo);
		return this;
	}

	/**
	 * Method to Select Known Count of People Radio Box.
	 */
	public ToiletCalculatorHomePage selectRadiobuttonKnownCount() {

		scrollIntoView(calculatorHeading);
		ScreenshotUtility.captureandclick(countKnownYes);
		return this;
	}
	
	/**
	 * Method to select building Facility from Dropdown.
	 * @param buildingName name of building.
	 * @return page object for the building.
	 * Not fully implemented for other building type except Hospital and Museum.
	 */
	@SuppressWarnings("unchecked")
	public <T> T selectBuildingUse(String buildingName) {

		ScreenshotUtility.captureandclick(selectDropDownButton);
		ScreenshotUtility.captureandclick(getElementFromListWithLable(buildingName, listOfBuildingType));
		ScreenshotUtility.capture(selectDropDownButton);
		if (buildingName.equalsIgnoreCase("Hospital")) {
			return (T) new HospitalMeasurementPage();
		} else if (buildingName.equalsIgnoreCase("Museum")) {
			return (T) new MuseumMeasurementPage();
		}
		throw new RuntimeException("Could not find page object class for " + buildingName);

	}

}