package Demo;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import files.LoginReqBod;
import files.ReusableMethod;
import files.TeamBody;
import files.UniversityBody;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Team {
	
	public static String accToken;
	public static int teamId;
	final String baseUrl = "http://localhost:5002/v0";
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = baseUrl;
		accToken = ReusableMethod.LoginAsSuperAdmin();
	}
	
	@Test(priority=1, enabled = true)
	@Description("Validate if user is able to add Team")
	public void addTeam() {
		String team = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.body(TeamBody.addTeam())
		.when().post("/org/1/team")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(team);
		teamId = js2.getInt("data.team.id");
	}
	
	@Test(priority=2, enabled = true)
	@Description("Validate if user is able to update Team")
	public void updateTeam() {
		String updates = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.body(TeamBody.updateTeam())
		.when().put("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(updates);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
	
	@Test(priority=3, enabled = true)
	@Description("Validate if user is able to get all Teams of an org")
	public void getTeam() {
		given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.when().get("/org/1/team/")
		.then().log().all().assertThat().statusCode(200);
	}
	
	@Test(priority=4, enabled = true)
	@Description("Validate if user is able to get Team by team id")
	public void getSingleTeam() {
		String singleTeam = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.when().get("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(singleTeam);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
	
	@Test(priority=5, enabled = true)
	@Description("Validate if user is able to delete Team")
	public void deleteTeam() {
		String deleteTeam = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+accToken)
		.when().delete("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(deleteTeam);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
}
