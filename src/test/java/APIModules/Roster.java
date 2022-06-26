package APIModules;

import Payload.RosterReqBody;
import Utilities.Constants;
import Utilities.ReadJsonFile;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Roster {

    String access_token = null;

    public static int playerId;
    String baseUrl = null;
    Login l = new Login(Constants.email, Constants.password, Constants.baseUri);

    public void addRoster(){
        String rosterBulkAdd = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
        .body(RosterReqBody.AddRosterBody())
        .when().post("/org/1/team/"+Team.teamId+"/roster")
        .then().log().all().assertThat().statusCode(200)
        .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(rosterBulkAdd);
        playerId = js1.getInt("data.rosters[0].id");
    }

    public void getRoster(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
        .when().get("/org/1/team/"+Team.teamId+"/roster")
        .then().log().all().assertThat().statusCode(200);
    }

    public void updateRoster(){
        String updateResponse = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
        .body(RosterReqBody.updateRoster())
        .when().put("/org/1/team/"+Team.teamId+"/roster/"+playerId)
        .then().log().all().assertThat().statusCode(200)
        .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(updateResponse);
//        Assert.assertEquals(playerId,js1.getInt("date.roster.id_player"));
    }
}
