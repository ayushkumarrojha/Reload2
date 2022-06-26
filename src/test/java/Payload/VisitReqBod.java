package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class VisitReqBod {

    public static JsonPath js = ReadJsonFile.read("/src/test/java/Files/visit.json");

    public static String addVisitPL1(){
        return "{\n" +
                "  \"id_visit_type\": 1,\n" +
                "  \"id_team\": 1,\n" +
                "  \"visit_date\": \"2022-10-10T16:30\",\n" +
                "  \"id_status\": 1,\n" +
                "  \"id_players\": [1,2,3],\n" +
                "  \"id_activities\": [29,31,48,58,72,221,222,223,227,604,609]\n" +
                "}";
    }

    public static String addVisitPL2(){
        return "{\n" +
                "  \"id_visit_type\": 1,\n" +
                "  \"id_team\": 1,\n" +
                "  \"visit_date\": \"2022-06-04T16:30\",\n" +
                "  \"id_status\": 1,\n" +
                "  \"id_position\": 17,\n" +
                "  \"id_activities\": [29,31,48,58,72,221,222,223,227,604,609]\n" +
                "}";
    }

    public static String updateVisit(){
        return "{\n" +
                "  \"id_visit_type\":"+js.getInt("id_visit_type")+",\n" +
                "  \"id_team\": "+js.getInt("id_team")+",\n" +
                "  \"visit_date\": \""+js.getString("visit_date")+"\",\n" +
                "  \"id_status\": "+js.getInt("id_status")+"\n" +
                "}";
    }
}
