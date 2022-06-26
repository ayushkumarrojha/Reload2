package APIModules;

import Payload.ActivityReqBod;
import Payload.VisitReqBod;
import Utilities.Constants;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PlayerActivity {

    String access_token = null;

    public static int playerActivityId;
    String baseUrl = null;
    Login l = new Login(Constants.email, Constants.password, Constants.baseUri);

    public void performPlayerActivity(int visitId){
        String addActivity = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(ActivityReqBod.addActivity(visitId))
                .when().post("/activity/perform")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(addActivity);
        playerActivityId = js1.getInt("data.activity.id_visit_activity_player");
    }

    public void updatePlayerActivity(int visitId){
        String updateActivity = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(ActivityReqBod.updateActivity(visitId, playerActivityId))
                .when().put("/activity/update-status")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(updateActivity);
        int updatedPlayerActivityId = js1.getInt("data.activity.id_visit_activity_player");
        Assert.assertEquals(updatedPlayerActivityId, playerActivityId);
    }

    public void addPlayerTrial(int visitId){
        given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(ActivityReqBod.addTrial(playerActivityId))
                .when().post("/visit/"+visitId+"/trial")
                .then().log().all().assertThat().statusCode(200);
    }
}
