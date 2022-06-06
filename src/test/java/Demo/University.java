package Demo;

import static org.hamcrest.Matchers.equalTo;

//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.LoginReqBod;
import files.ReusableMethod;
import files.UniversityBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class University {
	
	public static String accToken;
	public static int universityId;
	final String baseUrl = "http://localhost:5002/v0";
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = baseUrl;
	}
	
	@Test(priority=1, enabled = true, description = "Validate if user is able to login")
	public void login() {
		//RestAssured.baseURI= "http://localhost:5002/v0";
		//login a super admin
		String response = given().log().all().header("Content-Type","application/json")
		.body(LoginReqBod.loginBod()).when().post("/auth/login")
		.then().log().all().assertThat().statusCode(200).body("success", equalTo(true))
		.header("X-Powered-By", "Express").extract().response().asString();
				
		System.out.println(response);
		JsonPath js = ReusableMethod.rawToJson(response);//for parsing json
		accToken = js.getString("data.authToken.accessToken");
	}
	
	@Test(priority=2, enabled = true, description = "Validate if user is able to add university")
	public void addUniversity() {		
		//add university
		String addOrgBody = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.body(UniversityBody.addUniversity())
		.when().post("/org")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(addOrgBody);
		universityId = js1.getInt("data.organization.id");
		String universityName = js1.getString("data.organization.id");
		System.out.println(universityId);
		System.out.println(universityName);
	}
	
	@Test(priority=3, enabled = true, description = "Validate if user is able to delete university")
	public void deleteUniversity() {
		//delete university
		
		given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.when().delete("/org/"+universityId)
		.then().log().all().assertThat().statusCode(200);
		
	}
	/*
	@DataProvider(name="universities")
	public Object[] getData() {
		return new Object[] {"uni 1", "uni 2"};
	}*/

}
