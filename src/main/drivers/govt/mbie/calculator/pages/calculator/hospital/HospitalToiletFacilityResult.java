package govt.mbie.calculator.pages.calculator.hospital;

import java.awt.AWTException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import govt.mbie.calculator.base.BasePageObject;
import govt.mbie.calculator.util.screenshot.ScreenshotUtility;
import govt.mbie.calculator.util.testhelper.RobotAutomationHelper;

public class HospitalToiletFacilityResult extends BasePageObject {

	@FindBy(xpath = "//div[text()='Calculated number of toilet facilities']")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[text()='Calculated occupant densities (based on G1/AS1 Table 4) ']")
	private WebElement subheading;

	@FindBy(xpath = "//span[text()='Print']//ancestor::button[1]")
	private WebElement printButton;

	public HospitalToiletFacilityResult() {

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		isLoaded();
		scrollIntoView(pageTitle);
		logExtentReport("HospitalToiletFacilityResult Page Object Created");
	}

	protected void isLoaded() throws Error {
		waitForElementVisible(pageTitle);
	}

	/**
	 * Method to select building Facility from Dropdown.
	 * @param buildingName name of building.
	 * @return page object for the building.
	 * Not fully implemented for other building type except Hospital and Museum.
	 */
	public String getResultHeading() {

		scrollIntoView(printButton);
		return pageTitle.getText();

	}

	/**
	 * Method to print and save pdf of hospital toilet facility result.
	 * @param fileName is location of the file to store result.
	 * @return object HospitalToiletFacilityResult page.
	 */
	public HospitalToiletFacilityResult printandSaveResultAsPdf(String fileName) throws AWTException {

		ScreenshotUtility.capture(pageTitle);
		scrollIntoView(printButton);
		ScreenshotUtility.captureandclick(printButton);

		RobotAutomationHelper
		.saveFileWindowPopupHandler(fileName);

		return this;

	}
}
