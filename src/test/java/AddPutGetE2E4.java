import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import files.ReusableMethods;
import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPutGetE2E4 {
//Add place by POST then Update address by PUT api and then GET new updated address  by GET API.
//To update address we will be requiring some ID which was created by POST request
//Now first we need to extract response in string format by extract method and keep it under one variable to use ahead.
//We've to extract the json POST req response, so we need to pass post re json,so will use json path parser class which will take input as string n convert into JSON
//We are simply printing response as an output on console , also removing logs updating code from then for response.
	// JSON PATH class is used to parse the json and exposed multiple methods
	// Extract() method give us complete response of api request.
	// We've used testNG assertion methods to assert actual n expected result (we're
	// updating address so we're comparing actual address and updated address in GET
	// API)
	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
// Here in body we're supplying Body params by other class method so we're calling other class static method here instead of writing hard coded body
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(SupplyJsonInPostReq.Addplace()).when().post("maps/api/place/add/json").then().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();

		System.out.println(response);
// Now we got some params in api response out of which we want to fetch or use place id so here we will use one method to fetch the string response i. get(Stringpath)
		JsonPath js = new JsonPath(response);
//We have to provide path of json response body parameter, if param has parent then "ParentParamName.ParamName" and if there is no parent then directly provide param name i.e "ParamName" as an argument
		String placed_id = js.get("place_id");
		System.out.println("Place id is :" + placed_id);

//Now we're updating address by PUT API- refer postman put api, and here we're passing placed_id variable bcoz it contain actual place_id fetched from POST req
//Now the PUT api is not giving response but status code is 200
		// PUT
		// Also we can store address in one variable and same var we can pass in body
		// String addresses ="TEST-Address 70 Summer walk, USA123"
		String updatedaddress = "TEST-Address 70 Summer walk, USA123";
		given().log().all().queryParam("key", "qaclick123").header("Comtent-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placed_id + "\",\r\n"
				// + "\"address\":\"TEST-Address 70 Summer walk, USA123\",\r\n"
						+ "\"address\":\"" + updatedaddress + "\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				// Below body is response body where we're validating any param or message
				.body("msg", equalTo("Address successfully updated")).extract().response().asString();

//GET - Now we will check if address is actually updated or not by GET api after updating address 
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.header("Comtent-Type", "application/json").queryParam("place_id", placed_id).when()
				.get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();
		// Here we're calling JSON PATH method from other class by class name.method
		// name as its static, converting string into JSON.
		// and from JSON 'js2' object we're extracting string by get() method in below
		// line.
		JsonPath js2 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualaddress = js2.get("address");
		System.out.println(actualaddress);
		// assertEquals(actualaddress, "TEST-Address 70 Summer walk, USA123");
		assertEquals(actualaddress, updatedaddress);
	}

}
