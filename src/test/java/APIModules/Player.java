package APIModules;

import Payload.PlayerReqBody;
import Payload.RosterReqBody;
import Utilities.Constants;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Player {

    String access_token = null;

    public static int playerId;
    String baseUrl = null;
    Login l = new Login(Constants.email, Constants.password, Constants.baseUri);

    public String getPlayer(){
        String getPlayers = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().get("org/1/team/1/player")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        return getPlayers;
    }

    public void addPlayer(int count){
        String addedPlayer = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(PlayerReqBody.addPlayer(count))
                .when().post("/org/1/player")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(addedPlayer);
        playerId = js1.getInt("data.player[0].id");
    }

    public void updatePlayer(){
        String updatedPlayer = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(PlayerReqBody.updatePlayer())
                .when().put("/org/1/player/"+playerId)
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(updatedPlayer);
        int activatedPlayer = js1.getInt("data.player.id");
        Assert.assertEquals(activatedPlayer,playerId);
        boolean playerStatus = js1.getBoolean("data.player.deleted");
        Assert.assertEquals(playerStatus, false);
    }

    public void deletePlayer(){
        String deletedPlayer = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().delete("/org/1/player/"+playerId)
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(deletedPlayer);
        int delPlayer = js1.getInt("data.team.id");
        Assert.assertEquals(delPlayer,playerId);
        boolean playerStatus = js1.getBoolean("data.team.deleted");
        Assert.assertEquals(playerStatus, true);
    }
}
