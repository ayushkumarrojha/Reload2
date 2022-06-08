package Demo;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import files.LoginReqBod;
import files.ReusableMethod;

public class Login {
	
	public static String accToken;
	public static String refToken;
	final String baseUrl = "http://localhost:5002/v0";
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = baseUrl;
	}
	
	@Test(enabled = true)
	@Description("Validate if user is able to login")
	public void login() {
		// TODO Auto-generated method stub
		// login to cane sense api
		
		// given - All input details
		// when - Submit the api - resource and http method
		// then - validate the response
		
		//RestAssured.baseURI= "http://localhost:5002/v0";
		String response = given().log().all().header("Content-Type","application/json")
		.body(LoginReqBod.loginBod()).when().post("/auth/login")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true))
		.header("X-Powered-By", "Express").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = ReusableMethod.rawToJson(response);//for parsing json
		accToken = js.getString("data.authToken.accessToken");
		refToken = js.getString("data.authToken.refreshToken");
		System.out.println(accToken);
	}
	
	@Test(enabled = true)
	@Description("Validate if reset password is working")
	public void resetPassword() {
		
		given().log().all().header("Authorization","Bearer "+accToken).header("Content-Type","application/json")
		.body(LoginReqBod.resetBod()).when().post("/auth/reset-password")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true));
	}
		
		/*given().log().all().header("Authorization","Bearer "+accToken).header("Content-Type","application/json")
		.body(LoginReqBod.fpBod()).when().post("/auth/forgot-password")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true));*/
	
	@Test(enabled = true)
	@Description("Validate if refresh token is working")
	public void refreshToken() {
		
		String refresh  = given().log().all().header("Authorization","Bearer "+refToken).header("Content-Type", "none")
		.when().post("/auth/refresh-token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(refresh);
		accToken = js1.getString("data.authToken.accessToken");
	}
	
	@Test(enabled = true)
	@Description("Validate if who am i is working")
	public void whoAmI() {
		
		
		String whoami  = given().log().all().header("Authorization","Bearer "+accToken)
		.when().get("/auth/whoami")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(whoami);
		String user = js2.getString("data.user.username");
		Assert.assertEquals(user, "ayush2394@outlook.com");
	}
}
