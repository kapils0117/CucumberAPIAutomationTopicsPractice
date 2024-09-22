import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.SupplyJsonInPostReq;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Oauth2Implementation12 {
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
	
	//GET
	String sd=given()
			.queryParam("access_token", accesstoken)
			.when().log().all()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
	System.out.println(sd);
	System.out.println();
}
}
	

