import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RunWithMultipleTestDataDataProvid9 {
//Here we are running POST request with multiple test data using TestNG Data Provider annotation
	@Test(dataProvider = "Bookdata")//here we are linking databook data provider annotation method to test annotation
	public void Addbooking(String isbn, String aisle) {
		//above we need to provide three variables as in data provider method we declare three arrays
	RestAssured.baseURI = "http://216.10.245.166";
	String response = given().log().all().header("Comtent-Type", "application/json")
			//Below we're directly providing params values in body i.e isbn and aisle from class supplyjsonpostreq
			//We can remove directly providing test data in Addbookpay method as we're now using data provider annotaion
			.body(SupplyJsonInPostReq.Addbookpayloads(isbn, aisle)).when().post("Library/Addbook.php")
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
	
	@DataProvider(name="Bookdata")
	public Object[][] getdata() {
//Here we provided 3 no of test data and the above method Add booking POST req will run three times as three arrays are there and each array contain two elements.		
//Below test data like firsttest, second test will fall above for vaiable "isbn" and similarly for value 12345, 67890 and 00000 will fall above for variable "aisle"	
		Object[][] data= new Object[][] {{"firstTest", "12345"}, {"secondTest", "67890"},{"thirdTest", "00000"} };
	return data;
		
	}
	
}
