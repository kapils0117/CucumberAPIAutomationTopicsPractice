import files.SupplyJsonInPostReq;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsingThruJSONPath5 {
	// Here we dont' have any API url, here we're just validating and fetching params value from body 
//Below are the complex JSON Arrays examples, arrays contain indexes starts from 0, UnderDASHBOARD there are 2 child nodespurchaseamount & website (dashboard.pruchaseamount, dashboard.website)
//Under courses there are 3 arrays starts from 0 index to 2, it contain square bracket so its json array [ ]
	
	/*{
		"dashboard": {
		"purchaseAmount": 910,
		"website": "rahulshettyacademy.com"},
		
		"mobile":[12345,67890],
		
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
	public static void main(String[] args) {
		
	JsonPath js3=new JsonPath(SupplyJsonInPostReq.coursePrice());
	//print no of courses returned by API
	//below getInt method is used to fetch INT type json param value from POST req body
	int size=js3.getInt("courses.size()");
	System.out.println(size);
	//Print purchase amount
	int amount=js3.getInt("dashboard.purchaseAmount");
	System.out.println(amount);
	
	//print title of first course
	String title=js3.get("courses[0].title");
	System.out.println(title);
	
	//Print all Courses Title and their respective prices, to iterate use loop
	//This is the dynamic loop which can fetch any number param arrays value from nested json body
	for (int i=0; i<size;i++) {
		//provide i as variable +i+, kept title value in String variable
	   String titless=js3.get("courses["+i+"].title");
	   System.out.println(titless);
	//Print prices and converting return value into String by toString() method as syso except String value only
	   System.out.println(js3.get("courses["+i+"].price").toString());
	}
	
	
	//Print no of copies sold by RPA Course and this is generic login, doesn't matter where is the position of RPA in JSON
	System.out.println("Print no of copies sold by RPA Course");
	for (int i=0; i<size;i++) {
		String courseTitle=(js3.getString("courses["+i+"].title"));
		if(courseTitle.equalsIgnoreCase("RPA")) {
		int price=	js3.get("courses["+i+"].copies");
			System.out.println(price);
			break;
		}
	}
	//Verify if Sum of all Course prices matches with Purchase Amount
	
}

}