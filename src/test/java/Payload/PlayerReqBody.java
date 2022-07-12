package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class PlayerReqBody {

    public static JsonPath js = ReadJsonFile.read("/src/test/java/Files/player.json");

    public static String addPlayer(int count){
        return "{\n" +
                "    \"players\": [\n" +
                "        {\n" +
                "          \"first_name\": \"Sam\",\n" +
                "          \"last_name\": \"Elliott\",\n" +
                "          \"nickname\":  \"Elly\",\n" +
                "          \"email\": \"Elliott0"+count+"@eoscast.com\",\n" +
                "          \"jersey_no\": 92,\n" +
                "          \"birthdate\": \"1995-05-01\",\n" +
                "          \"sacrum_height\":59,\n" +
                "          \"weight\": 50,\n" +
                "          \"height\": 60,\n" +
                "          \"id_status\": 1\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String updatePlayer(){
        return "{\n" +
                "    \"activate\": "+js.getBoolean("activate")+"\n" +
                "}";
    }
}
