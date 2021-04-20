package govt.mbie.calculator.util.datautil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import govt.mbie.calculator.util.propertiesmanager.AppConfig;


public class TestDataUtil {

	private static final String TESTDATAJSONPATH = System.getProperty("user.dir") + AppConfig.getInstance().getTestData();
	
	private TestDataUtil() {
	}
	
	/**
	 * Retrieve a logger instance named according to class name.
	 * 
	 * @param id is the unique name with which we need to search the JSON Array.
	 * @param details retrieve the value stored against JSONObject in JSON Array.
	 * @return JSON Value as String.
	 */
	public static String readJsonFile(String id, String details) {
		JSONParser parser = new JSONParser();
		String valueToReturn;

		try {

			InputStream inputStream = new FileInputStream(TESTDATAJSONPATH);
			Reader fileReader = new InputStreamReader(inputStream, "UTF-8");

			// Object obj = parser.parse(new FileReader(TESTDATAJSONPATH));

			Object obj = parser.parse(fileReader);

			JSONObject jsonobject = (JSONObject) obj;

			JSONArray jarray = (JSONArray) jsonobject.get(id);
			JSONObject jsondata = (JSONObject) jarray.get(0);

			valueToReturn = (String) jsondata.get(details);

			return valueToReturn;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (ParseException e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}
}
