import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.GetCoursesParent;
import pojo.WebAutomation;

public class DeSerializationAndAssertionsCompareResponse16 {
	//Now in real time our goal will be to compare course title if they are correct or not by assertions so for this we will create one array String at the top and kept all Expected Courses there n then we will compare these with Actual courses titles (what we got from api response)

	public static void main(String[] args) {
		//Declaring Expected CourseTitle to compare with Actual course titles of WebAutomation
		String expectedCourseTitle[] = {"Selenium Webdriver Java", "Cypress","Protractor"};
		//OR we can also declare array list
		 // ArrayList<String> list = new ArrayList<>();
	     //   list.add("Selenium Webdriver Java");
	     //   list.add("Cypress");
	     //   list.add("Protractor");
		
		String s5=given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com" )
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(s5);
		JsonPath jjs=new JsonPath(s5);
		String accesstoken=jjs.getString("access_token");
		

		GetCoursesParent sd=given()
				.queryParam("access_token", accesstoken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCoursesParent.class);
		//Also we need to store this JSON respons ein class object bcoc data type is class
		
//Now if we want to fetch any param from resposne then we can use Getter method of that variable declared in GetCourse parent class.
		System.out.println("The linkedin url is : " +sd.getLinkedIn());
		//System.out.println("The Instructor  is : " +sd.getInstructor());
		//System.out.println("The complete output is : " +sd);
	//	System.out.println(sd.getCourses().getApi().get(1).getCourseTitle());

// Print price of SOAP UI course with dynamic/generic code. Now suppose the position of arrays moving then we have to write some generic code, suppose we want price of "SoapUI course"
	//List<Api> apiCourses =	sd.getCourses().getApi();
	//for (int i=0; i<apiCourses.size();i++) {
	//	if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
		//	System.out.println(apiCourses.get(i).getPrice());
		//	System.out.println(apiCourses.get(i).getCourseTitle());
		//}
		
		//Print all courses names exist under WebAutomation 
		ArrayList<String> expcourseTitles = new ArrayList<String>();

		List<WebAutomation> coursesTitle= sd.getCourses().getWebAutomation();
		for(int j=0;j<coursesTitle.size();j++) {
			
//so this loop iterates three timees and it will add all courses titles in below expcourseTitles array list object
			expcourseTitles.add(coursesTitle.get(j).getCourseTitle());
	
	}
		//No we can compare array declared at above and arraylist but we need to convert array into arraylist first
//		String expectedCourseTitle[] = {"Selenium Webdriver Java", "Cypress","Protractor"};
	
	List<String> expCoursesList=Arrays.asList(expectedCourseTitle);
	if(expcourseTitles.equals(expCoursesList)) {
		System.out.println("assertion passed");
		}
		else {
			System.out.println("assertion failed");
	Assert.assertTrue(expcourseTitles.equals(expCoursesList));
	
	

}
}}