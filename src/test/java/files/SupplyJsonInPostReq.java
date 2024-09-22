package files;

public class SupplyJsonInPostReq {
// The below methods Addplace is being used in class 3 in body as we're just passing this static method to supply the post req body params
	public static String Addplace() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	//Below payload n method will be used in class 5
	
	public static String coursePrice() {
		return "{\r\n"
				+ "		\"dashboard\": {\r\n"
				+ "		\"purchaseAmount\": 910,\r\n"
				+ "		\"website\": \"rahulshettyacademy.com\"},\r\n"
				+ "		\"courses\": [\r\n"
				+ "		{\r\n"
				+ "		\"title\": \"Selenium Python\",\r\n"
				+ "		\"price\": 50,\r\n"
				+ "		\"copies\": 6 },\r\n"
				+ "		{\r\n"
				+ "		\"title\": \"Cypress\",\r\n"
				+ "		\"price\": 40,\r\n"
				+ "		\"copies\": 4},\r\n"
				+ "{\r\n"
				+ "        \"title\": \"RPA\",\r\n"
				+ "		\"price\": 45,\r\n"
				+ "		\"copies\": 10}\r\n"
				+ "]\r\n"
				+ "		}\r\n"
				+ "";
	}
	
	public static String Addbookpayload() {
String addbooks ="{\r\n"
		+ "\r\n"
		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		+ "\"isbn\":\"test\",\r\n"
		+ "\"aisle\":\"227\",\r\n"
		+ "\"author\":\"John foe\"\r\n"
		+ "}\r\n"
		+ "";
return addbooks;
	}
	
//below we're declaring variables so that class 8 can use these in test class itself
public static String Addbookpayloads(String isbn, String aisle) {
String addbooks ="{\r\n"
		+ "\r\n"
		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		+ "\"isbn\":\""+isbn+"\",\r\n"
		+ "\"aisle\":\""+aisle+"\",\r\n"
		+ "\"author\":\"John foe\"\r\n"
		+ "}\r\n"
		+ "";
return addbooks;

	
	
	
	
}


}
