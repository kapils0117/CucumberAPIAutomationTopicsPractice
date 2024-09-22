import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.LocationPojo17;
import pojo.PojoForClass17;

public class Serialization17 {
	//Serialization : We convert JSON into java object,2. Create java object and set all the values with the help of setter method & then give ur code to rest assured n then rest assured converts java objects into json body to submit to the api
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
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response respo = given().log().all().queryParam("key", "qaclick123")
		.body(obj)
		.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		String s9= respo.asString();
		System.out.println(s9);
	}

	
}
