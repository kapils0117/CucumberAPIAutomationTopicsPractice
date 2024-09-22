import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AdvancePayloadCreationStrategies7 {
/*-Dynamically build JSON payload with external data inputs.
-Parameterize the API tests with multiple data sets.
-How to send static JSON files(payload) directly into POST method of RestAssurred 
-Feed JSON payload from using excel using HashMap
-POJO classes to build payload

Library API-
Addbook
120
Bookname, Authorname, isbn*/
	
	@Test
	public void Addbook() {
	RestAssured.baseURI = "http://216.10.245.166";
	String response = given().log().all().header("Comtent-Type", "application/json")
			.body(SupplyJsonInPostReq.Addbookpayload()).when().post("Library/Addbook.php")
			.then().log().all().assertThat()
			.statusCode(200).extract()
			.response().asString();
	//calling JSONPath method from other class
	JsonPath js4= ReusableMethods.rawToJson(response);
	//Fetch ID from the response body
	/* Output Json 
{
   "Msg": "successfully added",
   "ID": "bcd227"
} 
*/
	String id=js4.get("ID");
	System.out.println(id);
	
}
}