package govt.mbie.calculator.util.testhelper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DelteFileHelper {

	private static String BASEFOLDERPATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\govt\\mbie\\calculator\\testdata\\";

	private DelteFileHelper() {

	}

	/**
	 * Method to delete pdf file from folder.
	 * @param folderPath path of folder to be deleted.
	 * @throws IOException
	 */
	public static void deletePdf(String folderPath) throws IOException {

		FileUtils
		.cleanDirectory(new File(BASEFOLDERPATH + folderPath));

	}
}
