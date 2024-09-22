import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LocationPojo17;
import pojo.PojoForClass17;

public class RequestSpecBuilders18 {
// Request spec builders is a class has method named as Req Specification which we use to write/ assign some common API request details like API base url, header, Key params, Content type, etc
	/* RequestSpecBuilder() class return type is RequestSpecification so we have to store in it
	 * ADD PLACE : RestAssured.baseURI="XXXX";
	 * Response res=given().queryParam("key","qaclick123").header("Content-Type","application/json")
	 * .body(add_place_json) .when().post("/maps/api/place/add/json").
	 * then().assertThat().statusCode(200). contentType("application/json").extract().response();
	 * 
	 * GET_PLACE RestAssured.baseURI="XXXX";
	 * 
	 * Response res=given().queryParam("key","qaclick123").header("Content-Type","application/json") when ().
	 * get("/maps/api/place/get/json").then().assertThat().statusCode(200).contentType("application/json").extract().response();
	 * 
	 * DELETE_PLACE RestAssured.baseURI="XXXX";
	 * 
	 * Response res=given().queryParam("key","qaclick123").header("Content-Type","application/json").body(“delete_Place_json”) .when().post("/maps/api/place/delete/json").then().assertThat().statusCode(200).
	 * contentType("application/json").extract().response();
	 * 
	 * Build -Request Spec Builder use- 
	 * req= new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("XXXX") .addQueryParam("key","qaclick123") .build(); 
	 //Here we are passing req object in spec method 
	 * String resp=given().spec(req ).body(add_place_json).post(“/maps/api/place/add/json).
	 
	 Build Response Spec Builder: It is used for API response to minimize common response code writing again n again
res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON). build();

then().spec(re).extract().response();

Rewriting Test with Request and Response Spec Builder :
given().spec(req).body(add_place_json).post(“/maps/api/place/add/json).
then().spec(res).extract().response();

	 */

	
	public static void main(String[] args) {
	
		PojoForClass17 obj = new PojoForClass17();//here we've to create object of parent pojo class to call 8 setter methods here
		obj.setAccuracy(55);
		obj.setAddress("K-Street Polly Hill wonder445");
		obj.setLanguage("Hindi");
		obj.setName("Rahul");
		obj.setPhone_number("1234567890");
		obj.setWebsite("www.rahulshettyacademy.com");
		
//Now to access types parameter from payload we need to create a list as 'types' is an array type in payload request
		
		List<String> types2=new ArrayList<String>();
		types2.add("shoe park");
		types2.add("shop");
		obj.setTypes(types2);
		
		//This is nested json param i.e location so we need to create object of this class as well to call setter methods
		LocationPojo17 obj2= new LocationPojo17();// here we've to create object of Location pojo class to call setter methods here
		obj2.setLat(-12);
		obj2.setLng(45);
		obj.setLocation(obj2);// here we're passing obj of LocationPojo17 class and the object "obj2" has values we provided 
		
//Now in Rest Assured body method, we need to provide the parent/ main Pojo class(PojoForClass17) object i.e (obj)
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
//Here we created object of RequestSpecBuilder class and then used Add() and Set() methods to add url, query param and content type
//Return type of RequestSpecBuilder and ResponseSpecBuilder is RequestSpecification
		
	RequestSpecification requ=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
//Spec method will be used to provide object of RequestBuilder created above
	//Also we break it till body and change return type from resposne to RequestSpecification 
	RequestSpecification response1 = given().log().all().spec(requ)
		.body(obj);
	// OR 	Response respo = given().log().all().queryParam("key", "qaclick123")

		//Here we used above reqspecification object(resonse1) and we can store it also in one response variable
	//Response response2= response1.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response();
	//Converted JSON req into string by asString() method	
	//String s9= response2.asString();
	//	System.out.println(s9);
		//OR 
	//Similarly we will do for response and use ResponseBuilder class
		ResponseSpecification respspec=	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		Response responsespec2= response1.when().post("maps/api/place/add/json").then().log().all().spec(respspec).extract().response();
		
		int ss= responsespec2.getStatusCode();
		System.out.println("status code is " + " "+ss);
		String s10= responsespec2.asString();
            JsonPath jj= ReusableMethods.rawToJson(s10);
String sd=jj.get("status");
System.out.println("status is:" + ""+sd);
	}
	
	
	
}
