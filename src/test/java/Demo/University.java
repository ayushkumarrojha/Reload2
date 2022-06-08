package Demo;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.LoginReqBod;
import files.ReusableMethod;
import files.UniversityBody;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class University {
	
	public static String accToken;
	public static int universityId;
	public static int teamId;
	final String baseUrl = "http://localhost:5002/v0";
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = baseUrl;
		accToken = ReusableMethod.LoginAsSuperAdmin();
	}
	
	
	@Test(priority=1, enabled = true)
	@Description("Validate if user is able to add University")
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
	
	@Test(priority=2, enabled = true)
	@Description("Validate if user is able to update University")
	public void updateUniversity() {		
		//update university
		String fn = "Default org name";
		String updateOrg = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.body(UniversityBody.updateOrg(fn))
		.when().put("/org/"+universityId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(updateOrg);
		int UnivId = js1.getInt("data.organization.id");
		Assert.assertEquals(UnivId, universityId);
		String fullName = js1.getString("data.organization.full_name");
		Assert.assertEquals(fn, fullName);
		
	}
	
	@Test(priority=3, enabled = true)
	@Description("Validate if user is able to view all the organization")
	public void getUniversity() {		
		//get university
		given().log().all().header("Authorization","Bearer "+accToken)
		.when().get("/org")
		.then().log().all().assertThat().statusCode(200);
	}
	
	@Test(priority=4, enabled = true)
	@Description("Validate if user is able to delete")
	public void deleteUniversity() {		
		//get university
		String deleteOrg = given().log().all().header("Authorization","Bearer "+accToken)
		.when().delete("/org/"+universityId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(deleteOrg);
		boolean delete = js1.getBoolean("data.organization.deleted");
		Assert.assertEquals(delete, true);
		int UnivId = js1.getInt("data.organization.id");
		Assert.assertEquals(UnivId, universityId);
	}

}
