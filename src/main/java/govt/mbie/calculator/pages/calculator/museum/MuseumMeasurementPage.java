package govt.mbie.calculator.pages.calculator.museum;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import govt.mbie.calculator.base.BasePageObject;

public class MuseumMeasurementPage extends BasePageObject {

	@FindBy(xpath = "//span[text()='Museum']")
	private WebElement pageTitle;

	@FindBy(xpath = "//mat-label[text()='Enter applicable measurements to calculate occupant density:']")
	private WebElement intro;

	@FindBy(xpath = "//mat-card-title[contains(text(),'Calculate the number of toilet facilities here')]//ancestor::mat-card[1]")
	private WebElement calculator;

	@FindBy(xpath = "//mat-label[contains(text(),'Art galleries, museums')]//ancestor::div[1]//input")
	private WebElement museumsAreaInputBox;

	@FindBy(css = "#submit")
	private WebElement submitButton;

	public MuseumMeasurementPage() {

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		isLoaded();
		logExtentReport("MuseumMeasurement Page Object Created");
	}

	protected void isLoaded() throws Error {
		waitForElementVisible(pageTitle);
	}

}
