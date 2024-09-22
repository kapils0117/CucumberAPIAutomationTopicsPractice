import org.testng.Assert;
import org.testng.annotations.Test;

import files.SupplyJsonInPostReq;
import io.restassured.path.json.JsonPath;

public class SumValidaionLogicSectionSeven6 {


	
	@Test
	public void sumofprices() {
	JsonPath js3=new JsonPath(SupplyJsonInPostReq.coursePrice());
	int size=js3.getInt("courses.size()");
	int sum=0;
	for (int i=0;i<size;i++) {
		int prices=js3.getInt("courses["+i+"].price");
		int copy=js3.getInt("courses["+i+"].copies");
         int amount=prices * copy;
         System.out.println(amount);
         sum=sum+amount;
		
	}
System.out.println(sum);


     int purchaseAmount=js3.getInt("dashboard.purchaseAmount");
     if (purchaseAmount == sum) {
         System.out.println("Validation passed: purchaseAmount is equal to the calculated total price.");
     } else {
         System.out.println("Validation failed: purchaseAmount is not equal to the calculated total price.");
     }
     Assert.assertEquals(purchaseAmount, sum);
}
}

/*{
"dashboard": {
"purchaseAmount": 910,
"website": "rahulshettyacademy.com"},
"courses": [
{
"title": "Selenium Python",
"price": 50,
"copies": 6 },
{
"title": "Cypress",
"price": 40,
"copies": 4},
{
"title": "RPA",
"price": 45,
"copies": 10}
]
}
1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount*/