import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.EcommE2ELogin19;
import pojo.Orderdetails19Child;
import pojo.OrdersPraent19;
import pojo.ResponsePojo19;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import files.ReusableMethods;

public class EcommerceE2E19 {

	public static void main(String[] args) {
//RequestSpecBuilder req=new RequestSpecBuilder();
		//OR
RequestSpecification reqspec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

//calling pojo class to call post req params by creating object of pojo class here to call setter methods n set value at run time.
EcommE2ELogin19 pojo19 = new EcommE2ELogin19();
pojo19.setUserEmail("kapils0117@gmail.com");
pojo19.setUserPassword("admin@123");

RequestSpecification newreq=given().log().all().spec(reqspec).body(pojo19);
ResponsePojo19 responsetoken=newreq.when().post("/api/ecom/auth/login").then().extract().response().as(ResponsePojo19.class);

//Now we need to read response so there are many ways like create JSONPATH class like we did prev, create pojo class & deserialize, create JSONOBJECT and deserialize
	
System.out.println(responsetoken.getToken());
System.out.println(responsetoken.getUserId());
System.out.println(responsetoken.getMessage());

String token=responsetoken.getToken();
String userIds=responsetoken.getUserId();

//Add Product
//Here we provided token as object in which we stored the token value above
RequestSpecification addProd=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.addHeader("Authorization", token).build();// here we wont be using content type bcoz we're not using JSON here, we're using form params here

RequestSpecification reqprod=given().log().all().spec(addProd).param("productName", "kapproduct").param("productAddedBy", userIds)
.param("productCategory", "fashion").param("productSubCategory", "shirts").param("productPrice", "1500")
.param("productDescription", "Arrow cotton shorts").param("productFor", "men")
//For uploading image we will use multipart() method
.multiPart("productImage", new File("\\C:\\Users\\KSHARM23\\Desktop\\labour.jpg"));

//now we will use when() 
String response= reqprod.when().post("/api/ecom/product/add-product")
.then().log().all().extract().response().asString();
System.out.println(response);
JsonPath s2= ReusableMethods.rawToJson(response);
 String prodId= s2.get("productId");

	//Create Order
 RequestSpecification createOrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		 .addHeader("Authorization", token).setContentType(ContentType.JSON).build();
 
 
 Orderdetails19Child orderchild= new Orderdetails19Child();
 orderchild.setCountry("Australia");
 orderchild.setProductOrderedId(prodId);// Here we provided prodID which we got from above Add Product request API so passing that object here.
 
 //Now we will create one ArrayList as parent pojo class contain List and then we will add all objects of child Pojo class in the list object
 List<Orderdetails19Child> parorderList = new ArrayList<Orderdetails19Child>();// Here we provided ChildPojo class object
 parorderList.add(orderchild);
 
 OrdersPraent19 obbj=new OrdersPraent19();// Parent pojo class
 obbj.setOrders(parorderList);
 
  RequestSpecification createOrd= given().log().all().spec(createOrder).body(obbj);
 String latestOrder= createOrd.post("/api/ecom/order/create-order")
  .then().log().all().extract().response().asString();
 
 JsonPath s5= ReusableMethods.rawToJson(latestOrder);
String Js5=s5.getString("orders[0]");
System.out.println("OrderId is" + "" +Js5);
 
 //Now this order is created, we can also login into webapp and then confirm with assertion 
 System.out.println(latestOrder);
 
 // Delete Product and send Path Parameters
 RequestSpecification deleteProd1 =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
 .addHeader("Authorization", token).setContentType(ContentType.JSON).build();
 
 //If u see delete request on postman then we want to delete a product so we need productId which we got from POST req same variable we will pass here. Also productId on postman delete api url is a path parameter
       RequestSpecification deletereqapi=      given().log().all().spec(deleteProd1).pathParam("productId", prodId);
 String devicedeleteMesge= deletereqapi.when().delete("/api/ecom/product/delete-product/{productId}")
 .then().log().all().extract().response().asString();
 JsonPath s4= ReusableMethods.rawToJson(devicedeleteMesge);
String Js4=s4.get("message");
System.out.println(Js4);

// Delete Order and send Path Parameters
RequestSpecification deleteOrder =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

RequestSpecification deleteOrder1=      given().log().all().spec(deleteOrder).pathParam("orders", Js5);
String orderdeleteMsge=deleteOrder1.when().delete("/api/ecom/order/delete-order/{orders}")
.then().log().all().extract().response().asString();

JsonPath s7= ReusableMethods.rawToJson(orderdeleteMsge);
String Js7=s7.get("message");
System.out.println(Js7);
	}
	

	
	
}
