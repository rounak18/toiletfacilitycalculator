package govt.mbie.calculator.util.testhelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotAutomationHelper {
	
	private static String FILEPATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\govt\\mbie\\calculator\\testdata\\pdfgenrated\\";
	
	private RobotAutomationHelper() {

	}

	/**
	 * Method to handle window save file pop up and save pdf file .
	 * @param fileName location of file to be stored.
	 */
	public static void saveFileWindowPopupHandler(String fileName) throws AWTException {

		Robot robot = new Robot();
		robot.setAutoDelay(2000);

		StringSelection stringSelection = new StringSelection(FILEPATH + fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		robot.setAutoDelay(5000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.setAutoDelay(5000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		robot.setAutoDelay(5000);


	}

}
