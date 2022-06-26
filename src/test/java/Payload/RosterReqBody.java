package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class RosterReqBody {

    public static JsonPath js = ReadJsonFile.read("/src/test/java/Files/roster.json");

    public static String AddRosterBody() {
        return "{\n" +
                "     \"rosters\":{\n" +
                "        \"fosterHank@gmail.com\": {\n" +
                "            \"row_id\": 1,\n" +
                "            \"first_name\": \"Joey\",\n" +
                "            \"last_name\": \"Aurelio\",\n" +
                "            \"email\": \"fosterHank@gmail.com\",\n" +
                "            \"jersey_no\": 3,\n" +
                "            \"birthdate\": \"1997-05-03\",\n" +
                "            \"sacrum_height\": 104,\n" +
                "            \"weight\": 68,\n" +
                "            \"height\": 135,\n" +
                "            \"id_status\": 1,\n" +
                "            \"id_position\": 83,\n" +
                "            \"id_year\": 2,\n" +
                "            \"secondary_position\": 1\n" +
                "        },\n" +
                "         \"kimHank@gmail.com\": {\n" +
                "            \"row_id\": 2,\n" +
                "            \"first_name\": \"Evan\",\n" +
                "            \"last_name\": \"donny\",\n" +
                "            \"email\": \"kimHank@gmail.com\",\n" +
                "            \"jersey_no\": 4,\n" +
                "            \"birthdate\": \"1998-10-02\",\n" +
                "            \"sacrum_height\": 121,\n" +
                "            \"weight\": 134,\n" +
                "            \"height\": 121,\n" +
                "            \"id_status\": 3,\n" +
                "            \"id_position\": 84,\n" +
                "            \"id_year\": 1,\n" +
                "            \"secondary_position\": 2\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    public static String updateRoster(){
        return "{\n" +
                "  \"first_name\": \""+js.getString("first_name")+"\"\n" +
                "}";
    }

}
