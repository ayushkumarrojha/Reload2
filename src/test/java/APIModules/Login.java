package APIModules;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Payload.LoginReqBod;
import Utilities.ReusableMethod;

public class Login {
	

	final String baseUrl = "http://localhost:5002/v0";
	private String access_token = null;
	private String refresh_token = null;
	
	public void setBaseURI() {
		RestAssured.baseURI = baseUrl;
	}

	public List<String> login(String email, String password) {
		// TODO Auto-generated method stub
		// login to cane sense api
		
		// given - All input details
		// when - Submit the api - resource and http method
		// then - validate the response
		setBaseURI();
		String response = given().log().all().header("Content-Type","application/json")
		.body(LoginReqBod.loginBod(email, password)).when().post("/auth/login")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true))
		.header("X-Powered-By", "Express").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = ReusableMethod.rawToJson(response);//for parsing json
		access_token = js.getString("data.authToken.accessToken");
		refresh_token = js.getString("data.authToken.refreshToken");
		 
		return Arrays.asList(access_token, refresh_token);
	}
	
	//@Step()
	public void resetPassword() {
		given().log().all().header("Authorization","Bearer "+this.getAccessToken()).header("Content-Type","application/json")
		.body(LoginReqBod.resetBod()).when().post("/auth/reset-password")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true));
	}
//		Forget password
//		/*given().log().all().header("Authorization","Bearer "+accToken).header("Content-Type","application/json")
//		.body(LoginReqBod.fpBod()).when().post("/auth/forgot-password")
//		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true));*/
//	
	public void refreshToken() {
		
		String refresh  = given().log().all().header("Authorization","Bearer "+refresh_token).header("Content-Type", "none")
		.when().post("/auth/refresh-token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(refresh);
		access_token = js1.getString("data.authToken.accessToken");
	}
	
	public void whoAmI() {
		
		
		String whoami  = given().log().all().header("Authorization","Bearer "+access_token)
		.when().get("/auth/whoami")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(whoami);
		String user = js2.getString("data.user.username");
		Assert.assertEquals(user, "ayushk@geekyants.com");
	}
	
	
	public Login(String email, String password){
		List<String> tokens = this.login(email, password);
		this.access_token = tokens.get(0);
		this.refresh_token = tokens.get(1);
	}
	
	public String getAccessToken() {
		return this.access_token;
	}
	
	
}
