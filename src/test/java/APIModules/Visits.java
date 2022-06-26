package APIModules;

import Payload.RosterReqBody;
import Payload.VisitReqBod;
import Utilities.Constants;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Visits {

    String access_token = null;

    public static int visitId1;
    public static int visitId2;
    String baseUrl = null;
    Login l = new Login(Constants.email, Constants.password, Constants.baseUri);

    public int addVisitByPlayers(){
        String addsVisit = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(VisitReqBod.addVisitPL1())
                .when().post("/org/1/visit")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(addsVisit);
        visitId1 = js1.getInt("data.visit.id_visit");
        return visitId1;
    }

    public void addVisitByPosition(){
        String addsVisit = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(VisitReqBod.addVisitPL2())
                .when().post("/org/1/visit")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(addsVisit);
        visitId2 = js1.getInt("data.visit.id_visit");
    }

    public void getVisit(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().get("/org/1/visit")
                .then().log().all().assertThat().statusCode(200);
    }

    public void getVisitPlayer(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().get("/org/1/visit/"+visitId1+"/players")
                .then().log().all().assertThat().statusCode(200);
    }

    public void getVisitActivities(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().get("/org/1/visit/"+visitId1+"/activities")
                .then().log().all().assertThat().statusCode(200);
    }

    public void updateVisit(){
        String updVisit = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(VisitReqBod.updateVisit())
                .when().put("/org/1/visit/"+visitId1)
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(updVisit);
        int updateVisitId = js1.getInt("data.visit.id_visit");
        Assert.assertEquals(updateVisitId, visitId1);
    }

    public void deleteVisit(){
        String delVisit = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().delete("/org/1/visit/"+visitId1)
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(delVisit);
        int delVisitId = js1.getInt("data.visit.id_visit");
        Assert.assertEquals(delVisitId, visitId1);
    }
}
