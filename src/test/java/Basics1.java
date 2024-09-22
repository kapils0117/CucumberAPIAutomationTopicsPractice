import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Basics1 {
public static void main(String[] args) {
	//validate if ADD PLACE api is working as expected
	//Always remember when u create POST req then delete that also to run it again bcoz it will say its already exists
	
	//Path param we provide like this {productId}
	//Variable we provide like this {""+variableName+}
	//Query param we provide like this ("paramName", "value")
// DataBind  - Conver java object into json and json object into array
	
	
	//For all static package / methods eclipse is not asking to import
//given: take all input details of api, 
//when : when you hit or submit the api : need to provide HTTP methods and resources (after slash in api uri), no need to prvide query param from api uri after question mark ?
//Then: validate the response, all validation what we're doing comes under then
	//firstly set base uri and need to import this package explicitly : import static io.restassured.RestAssured.*;
	RestAssured.baseURI="https://rahulshettyacademy.com";

	//Now give query params in given like query params : param part near authorisation (at left), we dont have authorisation in this POST request
	//Now we will give header, like content type n its value
	//We will also give body, and the api doc which devs provides contain body in json format so if u paste that here then it will consider as string and put slash there wherever it found double quotes
//To convert json into string format go to Preferences => Editor=>typing and check check box for "escape text when pasting into a string literal"
//in When select post method (arg uri)
//In Then keyword validate any response param like status code , id, etc with assertion
	//Can also use log method to check logs log().all() in GIVEN(Request logs) and THEN(Validate RESPONSE logs) keyword
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\n  \"location\": {\n    \"lat\": -38.383494,\n    \"lng\": 33.427362\n  },\n  \"accuracy\": 50,\n  \"name\": \"Frontline house\",\n  \"phone_number\": \"(+91) 983 893 3937\",\n  \"address\": \"29, side layout, cohen 09\",\n  \"types\": [\n    \"shoe park\",\n    \"shop\"\n  ],\n  \"website\": \"http://google.com\",\n  \"language\": \"French-IN\"\n}" +"")
	.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);
	
	
	
	
}
}
