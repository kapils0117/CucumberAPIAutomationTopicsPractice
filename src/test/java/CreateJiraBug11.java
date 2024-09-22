import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import java.io.File;

import org.testng.annotations.Test;

import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateJiraBug11 {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://kapils0117.atlassian.net/";
// To atach any file in rest assured we use method i.e "multipart"
		String response3=	 given()
		 .header("Content-Type","application/json")
		 .header("Authorization","Basic a2FwaWxzMDExN0BnbWFpbC5jb206QVRBVFQzeEZmR0YwVmVrZC1VeUNxNTJKNTNxRzVOTnRfYVRIckVGQ0hJeGh3VmJLU3pQZnFaLU00WkplZ2Y1Q25HcE5FWEp5MDJDNkE4cXNkOW82T0h2cHZFcktBRDN4dFdscDQxVnByWE00UTNnRGlydXhyNzQwMlI1X1V1ckg4dDMwU0t2M1NHcUphdUVDelRucGd5c01nS3p6b3piOGdxRXdyWE1hNzItUWZ0WTVxN1dqdFQ0PTgyNkM1MjdE")
		.body("{\r\n"
				+ "\"fields\": {\r\n"
				+ "\"project\": {\r\n"
				+ "\"key\": \"SCRUM\"\r\n"
				+ "},\r\n"
				+ "\"issuetype\": {\r\n"
				+ "\"name\": \"Bug\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"test 1234 UI down and is not working\",\r\n"
				+ "\"description\": \r\n"
				+ "    [\r\n"
				+ "        {\r\n"
				+ "            \"value\":\"This is test desc\"\r\n"
				+ "        }\r\n"
				+ "        ]\r\n"
				+ "}\r\n"
				+ "}").log().all()
		
		.when().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		
		JsonPath jss = new JsonPath(response3);
           String  issueId=jss.getString("id");
           System.out.println(issueId);
       String keys=    jss.getString("key");
       System.out.println(keys);
   	//Add Attachment
 //Here we're giving path parameter bcoz after slash in api url its consider as path param and ? is considered as query param
//https://kapils0117.atlassian.net/rest/api/3/issue/{issueId}/attachments
    	   File imageFile= new File("C:\\Users\\KSHARM23\\Downloads\\2024-06-10_23-42-36.png");
    	   String s7=  given().pathParam("key", issueId)
           .header("X-Atlassian-Token","no-check")
           .header("Authorization","Basic a2FwaWxzMDExN0BnbWFpbC5jb206QVRBVFQzeEZmR0YwVmVrZC1VeUNxNTJKNTNxRzVOTnRfYVRIckVGQ0hJeGh3VmJLU3pQZnFaLU00WkplZ2Y1Q25HcE5FWEp5MDJDNkE4cXNkOW82T0h2cHZFcktBRDN4dFdscDQxVnByWE00UTNnRGlydXhyNzQwMlI1X1V1ckg4dDMwU0t2M1NHcUphdUVDelRucGd5c01nS3p6b3piOGdxRXdyWE1hNzItUWZ0WTVxN1dqdFQ0PTgyNkM1MjdE")
//.multiPart("file",new File("C:\\Users\\KSHARM23\\Downloads\\2024-06-10_23-42-36.png")).log().all()
           .multiPart("file",imageFile).log().all().when()

           .post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200).extract().response().asString();
System.out.println(s7);
JsonPath jss1 = new JsonPath(s7);
 String str=jss1.getString("filename");
System.out.println("The file name is  : " +str);
	}


	
	
	}
