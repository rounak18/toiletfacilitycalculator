package govt.mbie.calculator.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import govt.mbie.calculator.util.propertiesmanager.WebDriverConfig;

public class BasePageObject extends TestBase {

	private static long EXPLICITWAITTIMEOUT = WebDriverConfig.getInstance().getExplicitWaitTimeOut();

	/**	 
	 * Method to set explicitwait time for Webdriver.
	 * @param explicitwaittimeout is wait time out in seconds.
	 */	
	private WebDriverWait setExplicitwait(long explicitwaittimeout) {

		WebDriverWait explicitwait = new WebDriverWait(driver, explicitwaittimeout);
		return explicitwait;

	}

	/**	 
	 * Method to set fluentwait time for Webdriver.
	 * @param timeout is wait time out in seconds.
	 * @param pollingtime is polling time in seconds.
	 */	
	private FluentWait<WebDriver> setFluentWait(long timeout, long pollingtime) {

		FluentWait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime)).ignoring(Exception.class);

		return fluentwait;
	}

	/**	 
	 * Method to wait for web page element to be visible.
	 * @param element WebElement to wait for.
	 */	
	public void waitForElementVisible(WebElement element) throws Error {

		setExplicitwait(EXPLICITWAITTIMEOUT).until(ExpectedConditions.visibilityOf(element));

	}

	/**	 
	 * Method to wait for web page element to be clickable.
	 * @param element WebElement to wait for.
	 */	
	public void waitForElementClickable(WebElement element) throws Error {

		setExplicitwait(EXPLICITWAITTIMEOUT).until(ExpectedConditions.elementToBeClickable(element));

	}

	/**	 
	 * Method to wait for web page text to be displayed.
	 * @param element WebElement to wait for.
	 * @param text to be check for.
	 * @param timeout wait timeout in seconds for element.
 	 * @param pollingtime poll time in seconds for element.
	 * @return WebElement.
	 */	
	public WebElement waitForTextDisplay(final WebElement element, final String text, long timeout, long pollingtime) {

		WebElement ele = setFluentWait(timeout, pollingtime)
				.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {

				String getTextOnPage = element.getText();
				if (getTextOnPage.equals(text)) {
					return element;
				} else {
					return null;
				}
			}
		});

		return ele;
	}

	/**	 
	 * Method to check element is displayed or not for web page text to be displayed.
	 * @param element WebElement.
	 * @return boolean value.
	 */	
	public boolean isElementDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	/**	 
	 * Method to get text in one line removing new line.
	 * @param element WebElement.
	 * @return String.
	 */	
	public String getTextInOneline(WebElement element) {
		return element.getText().replaceAll("(\\r|\\n)", " ");

	}
	
	/**	 
	 * Method to switch to frame in web page using WebElement.
	 * @param element WebElement.
	 */	
	public void switchtoFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**	 
	 * Method to move back to the parent frame.
	 */	
	public void switchtoparentFrame( ) {
	    driver.switchTo().parentFrame();
	}
	
	/**	 
	 * Method to move back to the main frame.
	 */	
	public void switchtodefaultContent() {
	    driver.switchTo().defaultContent();
	}

	/**	 
	 * Method to Scroll into Page View of element.
	 * @param element WebElement.
	 */	
	public void scrollIntoView(WebElement element) {
		 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//This will scroll the page till the element is found		
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
   
	/**	 
	 * Method to iterate through list and select matching element based on text.
	 * @param label text to check for matching.
	 * @param list WebElement list.
	 * @return WebElement.
	 */	
	public WebElement getElementFromListWithLable(String label, List<WebElement> list) {

		for (WebElement e : list) {
			if (e.getText().contains(label)) {
				return e;
			}
		}
		throw new RuntimeException("Could not find nav link element for " + label);
	}
	
}