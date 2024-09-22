	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.equalTo;
	import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReusableMethods;
	import files.SupplyJsonInPostReq;
	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
public class HandleStaticPayload10 {
	//This code is similar to class 4 , only we're providing static json by external file
//1st way to send static JSON payload- For static paylaod either send POST req payload bby other class like we did by supplying payload thru 'SupplyJsonInPostReq' class OR 
	//2nd way to send static json payload - Or we can also send payload like we sent as a variable in class 8
//3rd way to send static json payload- via external files & send file path into your body method

	
		public static void main(String[] args) throws IOException {

			RestAssured.baseURI = "https://rahulshettyacademy.com";
	// Here in body we're supplying Body params by other class method so we're calling other class static method here instead of writing hard coded body
		//Content of the file to String -> content of file will convert into byte -> n then from byte data to String 
			
			//Imp - Here to convert byte data into String we need to create String object and pass in the body oe we can first declare String variable and pass it in the body
	
			String sss=new String(Files.readAllBytes(Paths.get("C:\\Users\\KSHARM23\\Desktop\\test.txt")));
			String response = given().log().all().queryParam("key", "qaclick123").header("Comtent-Type", "application/json")
					.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\KSHARM23\\Desktop\\test.txt")))).when().post("maps/api/place/add/json").then().assertThat()
                            //OR
					//.body(sss).when().post("maps/api/place/add/json").then().assertThat()

					.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
					.response().asString();

			System.out.println(response);
			JsonPath js = new JsonPath(response);
			String placed_id = js.get("place_id");
			System.out.println("Place id is :" + placed_id);

			// PUT
		
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

