package APIModules;

//import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import Utilities.Constants;
import Utilities.ReusableMethod;
import Payload.TeamBody;
//import io.qameta.allure.Description;
//import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Team {
	
	String access_token = null;
	public static int teamId;
	final String baseUrl = "http://localhost:5002/v0";
	Login l = new Login(Constants.email, Constants.password);
	
	public void addTeam(int count) {
		String team = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(TeamBody.addTeam(count))
		.when().post("/org/1/team")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(team);
		teamId = js2.getInt("data.team.id");
	}
	
	public void updateTeam(int count) {
		String updates = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(TeamBody.updateTeam(count))
		.when().put("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(updates);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
	
	public String getTeam() {
		String team = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.when().get("/org/1/team/")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		return team;
	}
	
	public void getSingleTeam() {
		String singleTeam = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.when().get("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(singleTeam);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
	
	public void deleteTeam() {
		String deleteTeam = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.when().delete("/org/1/team/"+teamId+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(deleteTeam);
		int teamNewId = js2.getInt("data.team.id");
		Assert.assertEquals(teamNewId, teamId);
	}
}
