import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.Courses;
import pojo.GetCoursesParent;
import pojo.WebAutomation;
//In this class we will only work with De-serialization means we will get / fetch the API request response by getter methods( .get())
public class DeSerialization15 {
	/*Serialization is used for JSON REQUEST , in rest assured is a process of converting Java object into request body(payload), and we send the json at run by creating java object of pojo class
	 * Serialization : We convert JSON into java object, 2.  POJO(Plain object java class) is java object to json.  we create getter n setter methods of json each json param /object and set it on run time 
	 * In serialization, we use Setter method to set the values of JSON request
	 * 	De-serialization is used for JSON RESPONSE : Json to POJO (Java ob) and it comes into the picture if u want to fetch any param value from api response
	 * 	De-serialization is a process to convert Response payload into Java object. we were using JSONPATH class to convert json response into string but here we will use POJO class and GETTER method for this without using JSONPATH class
	 
		Advantages- Easy to parse & extract response(Json/xml) values
		User friendly methods can be created which makes code more readable.
		
		Design Approach
		Java object is constructed with the help of POJO classes.
		POJO classes are created based on request/response payload.
		Need to add below libraries Jackson, Jackson2, Gson, Johnzon in the classpath and for xml we need JAXB. 
		
			POST API JSON REQUEST :
			
		{
    "instructor": "RahulShetty",
    "url": "rahulshettycademy.com",
    "services": "projectSupport",
    "expertise": "Automation",
    "courses": {
        "webAutomation": [
            {
                "courseTitle": "Selenium Webdriver Java",
                "price": "50"
            },
            {
                "courseTitle": "Cypress",
                "price": "40"
            },
            {
                "courseTitle": "Protractor",
                "price": "40"
            }
        ],
        "api": [
            {
                "courseTitle": "Rest Assured Automation using Java",
                "price": "50"
            },
            {
                "courseTitle": "SoapUI Webservices testing",
                "price": "40"
            }
        ],
        "mobile": [
            {
                "courseTitle": "Appium-Mobile Automation using Java",
                "price": "50"
            }
        ]
    },
    "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
}
			 

		JSON FORMATTED objects : so here we have 6 objects(instructor, url, services, expertise, courses and linkedin url in JSON so 6 pojo classes we need to create first
			Also courses has 3 sub json and have arrays so we will claso create 3 separate POJO classes for this also
		object		{6}
			instrcutor	:	Rahulshetty
			url	:	rahul
			services	:	chawla
			expertise	:	automation
			courses		{3} // Here courses contain nested JSON, 3 nested JSON and each 
			  webAutomation		[3]
			  api		[2]
			  mobile		[1]	
	linkedin url	:	https://www.linkedin.com/in/rahul-shetty-trainer	
	}
	*/
		
		//Authentication methods Basic Auth (like using bearer token), Oath2.0, Oauth 1.0, etc
			//On postman we selected formdata under body and provided grant type, scope, client secret, client id as a key n value param n send post API to get token
			//For authorization there are two methods mainly used as a Grant type in Oauth2.0, 1. Client creds(client ID & Client secret), 2nd - Password(user name & psswrd)
			/*Authorization Server EndPoint:
				https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token
				HTTP Method : POST
				grant_type:   client_credentials
		        Form parameters :
				client_id:692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
				client_secret:  erZOWM9g3UtwNRj340YYaK_W
				scope:  trust
				******************************************************************
				GetCourseDetails EndPoint (Secured by OAuth) :
				https://rahulshettyacademy.com/oauthapi/getCourseDetails
				HTTP Method : GET
				Query Parameter : access_token*/
	
	public static void main(String[] args) {
		
				
		
		String s5=given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com" )
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(s5);
		JsonPath jjs=new JsonPath(s5);
		String accesstoken=jjs.getString("access_token");
		
//Here we will use deserialization to fetch the response. Also in class 12 we convert JSON reposne into String then stored in variable "sd" and then printed response
// But here we will convert JSON response into Java Object(de-serialization) by using one class ( classname.class), here we will class Parents class of JSON which has nested class n arrays		
		//GET
		//Also we need to store this JSON response in class object bcoz data type is class so we changed datat type from String to Class(class name)
		GetCoursesParent sd=given()
				.queryParam("access_token", accesstoken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCoursesParent.class);
		//Also we need to store this JSON respons ein class object bcoc data type is class
		
//Now if we want to fetch any param from resposne then we can use Getter method of that variable declared in GetCourse parent class.
		System.out.println("The linkedin url is : " +sd.getLinkedIn());
		//System.out.println("The Instructor  is : " +sd.getInstructor());

		//System.out.println("The complete output is : " +sd);
		//System.out.println(sd.getCourses().getWebAutomation().get(0).getCourseTitle());
	//	System.out.println(sd.getCourses().getApi().get(0).getPrice());
	//	System.out.println(sd.getCourses().getApi().get(1).getCourseTitle());

// Print price of SOAP UI course with dynamic/generic code. Now suppose the position of arrays moving then we have to write some generic code, suppose we want price of "SoapUI course"
	//List<Api> apiCourses =	sd.getCourses().getApi();
	//for (int i=0; i<apiCourses.size();i++) {
	//	if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
		//	System.out.println(apiCourses.get(i).getPrice());
		//	System.out.println(apiCourses.get(i).getCourseTitle());
		//}
		
		//Print all courses names exist under WebAutomation 
		List<WebAutomation> coursesTitle= sd.getCourses().getWebAutomation();
		for(int j=0;j<coursesTitle.size();j++) {
			System.out.println(coursesTitle.get(j).getCourseTitle());

//Now our goal is to compare course title if they are correct or not by assertions so for this we will create one array String at the top and kept all Expected Courses there n then we will compare these with Actual courses titles (what we got from api response)

			
	}
		

	}
	
}