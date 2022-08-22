package APIModules;

import Payload.InjuryReportBod;
import Utilities.Constants;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import Payload.InjuryReportBod;

import static io.restassured.RestAssured.given;

public class InjuryReport {

    String access_token = null;

    public static int injuryReportID;
    String baseUrl = null;
    Login l = new Login(Constants.email, Constants.password, Constants.baseUri);

    public void createInjuryReport(){
        String injuryReport = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(InjuryReportBod.injuryReport(Roster.playerId))
                .when().post("/injury-report")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(injuryReport);
        injuryReportID = js1.getInt("data.injury_report.id_injury_report");
    }

    public void getInjuryReport(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().get("/injury-report/"+InjuryReport.injuryReportID)
                .then().log().all().assertThat().statusCode(200);
    }

    public void updateInjuryReport(){
        given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
                .body(InjuryReportBod.updateReportBod())
                .when().put("/injury-report/"+InjuryReport.injuryReportID)
                .then().log().all().assertThat().statusCode(200);
    }

    public void deleteReport(){
        given().log().all().header("Authorization","Bearer "+l.getAccessToken())
                .when().delete("/injury-report/"+InjuryReport.injuryReportID)
                .then().log().all().assertThat().statusCode(200);
    }

}
