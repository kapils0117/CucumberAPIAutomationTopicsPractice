import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;

public class RemoveJsonInPostBody3 {
// Here we are removing hard coded json payload from Post req body and supplying json params from other class by calling one static method


	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
// Here in body we're supplying Body params by other class method so we're calling other class static method here instead of writing hard coded body
		given().log().all().queryParam("key", "qaclick123").header("Comtent-Type","application/json")
		.body(SupplyJsonInPostReq.Addplace()).when()
		.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		.body("scope",equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
		
		
		
	}

}
