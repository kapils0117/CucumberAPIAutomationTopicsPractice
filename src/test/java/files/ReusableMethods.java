package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
//Here we're creating one static JSONPath method as generic so that other classes can use this
	// This method is getting called in class 4 , 58th line

	public static JsonPath rawToJson(String response) {
		JsonPath js2 = new JsonPath(response);
		return js2;
	}

}
