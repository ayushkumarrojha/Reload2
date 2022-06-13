package APIModules;

//import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

import Utilities.Constants;
import Utilities.ReusableMethod;
import Payload.UniversityBody;
//import io.qameta.allure.Description;
//import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class University {
	
	String access_token = null;
	public static int universityId;
	final String baseUrl = "http://localhost:5002/v0";
	Login l = new Login(Constants.email, Constants.password);
	
	public void addUniversity(int count) {		
		//add university
		String addOrgBody = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(UniversityBody.addUniversity(count))
		.when().post("/org")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(addOrgBody);
		universityId = js1.getInt("data.organization.id");
	}
	
	public void updateUniversity(int count) {		
		//update university
		String updateOrg = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(UniversityBody.updateOrg(count))
		.when().put("/org/"+universityId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJson(updateOrg);
		int UnivId = js1.getInt("data.organization.id");
		Assert.assertEquals(UnivId, universityId);
		//String fullName = js1.getString("data.organization.full_name");
		//Assert.assertEquals(, fullName);
		
	}
	
	public String getUniversity() {		
		//get university
		String getUnv = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
		.when().get("/org")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		return getUnv;
	}
	

	public void deleteUniversity() {		
		//get university
		String deleteOrg = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
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
