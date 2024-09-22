import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SendingBodyParamsViaTestFile8 {
// Upto class 7th we were passing POST / PUT request body params from another class "SupplyJsonPostReq" but its not good practice.
	//Now we'll pass payload param from test class itself so we need to create variable inside payload(test data)class
//below is dynamic json- duplicate of class 7 code but here we're passing param in test class itself
	@Test
	public void Addbooking() {
	RestAssured.baseURI = "http://216.10.245.166";
	String response = given().log().all().header("Comtent-Type", "application/json")
			//Below we're directly providing params values in body i.e isbn and aisle from class supplyjsonpostreq
			.body(SupplyJsonInPostReq.Addbookpayloads("Testing", "445039")).when().post("Library/Addbook.php")
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
