import org.json.JSONArray;
import org.json.JSONObject;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.groovy.control.HasCleanup;
import org.testng.reporters.jq.Main;

import files.SupplyJsonInPostReq;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class SendJsonThruJsonObjectAtRunTime14 {
//The advantage of using JSONOBject is that we don't need to provide data type as we provide in MAP while sending JSON
				
				/*"email":"kapils12@gmail.com",
				"firstname":"rahul",
				"lastname":"chawla",
				Mobile no is an json array 
				"mobile":[12345,67890],
				"address":{
					"flat-no":"A-30",
					"city":"agra",
					"pin":"245556",
					"state":"delhi",
					"country":"india"
				}
				
			}*/
			/*{
				  "location": {
				    "lat": -38.383494,
				    "lng": 33.427362
				  },
				  "accuracy": 50,
				  "name": "Frontline house",
				  "phone_number": "(+91) 983 893 3937",
				  "address": "29, side layout, cohen 09",
				  "types": [
				    "shoe park",
				    "shop"
				  ],
				  "website": "http://google.com",
				  "language": "French-IN"
				} */

			
		public static void main(String[] args) {
			RestAssured.baseURI = "https://rahulshettyacademy.com";
           JSONObject locations = new JSONObject();
			locations.put("lat", "-38.383494");
			locations.put("lng", "33.427362");
			
//Also in hashmap we used arraylist or String array[] but here in JSONOBJ we can use JSONARRAY and by using PUt mthod can add data in it		
		 //For types we can create arraylist or String array[]
			
		//ArrayList<String> typess = new ArrayList<String>();
		//typess.add("shoe park");
		//typess.add("shop");

		//OR
		//String[] arr = {"shoe park","shoe"};
			JSONArray typess = new JSONArray();
			typess.put("shoe park");
			typess.put("shop");
			
			
		 JSONObject payloadMap = new JSONObject();
		payloadMap.put("accuracy", "50");
		payloadMap.put("name", "Frontline house");
		payloadMap.put("phone_number", "(+91) 983 893 3937");
		payloadMap.put("address", "29, side layout, cohen 09");
		payloadMap.put("location", locations);
		payloadMap.put("types",typess);
		payloadMap.put("website", "http://google.com");
		payloadMap.put("language", "French-IN");

		// Convert the HashMap to JSON string
		//ObjectMapper objectMapper = new ObjectMapper();
		//String jsonPayload = objectMapper.writeValueAsString(payloadMap);

			String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body(payloadMap.toString()).when().post("maps/api/place/add/json").then().assertThat()
					.statusCode(200).body("scope", equalTo("APP")).body("status",equalTo("OK"))
					.header("server", "Apache/2.4.52 (Ubuntu)").extract()
					.response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
				String placed_id = js.get("place_id");
				System.out.println("Place id is :" + placed_id);
					
					
					

					
			}	
		}

		
	
	
	

