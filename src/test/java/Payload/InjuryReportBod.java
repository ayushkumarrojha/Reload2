package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class InjuryReportBod {

    public static JsonPath js = ReadJsonFile.read("/src/test/java/Files/roster.json");

    public static String injuryReport(int rosterId){

        return "{\n" +
                "    \"id_roster\":"+ rosterId+",\n" +
                "    \"location\": \"Some injury location here\",\n" +
                "    \"activity\": \"Some activity here\",\n" +
                "    \"physical_contact\": true,\n" +
                "    \"id_body_part\": 3,\n" +
                "    \"injured_structures\": [4, 3],\n" +
                "    \"injury_types\": [5],\n" +
                "    \"treatments\": [3],\n" +
                "    \"other_treatment\": null,\n" +
                "    \"diagnosis\": \"Some diagnosis here\"\n" +
                "}";

    }

    public static String updateReportBod(){
        return "{\n" +
                "    \"location\": \"Location\",\n" +
                "    \"activity\": \"Activity\",\n" +
                "    \"physical_contact\": true,\n" +
                "    \"id_body_part\": 4,\n" +
                "    \"injured_structures\": [3,4],\n" +
                "    \"injury_types\": [3,4],\n" +
                "    \"treatments\": [4,5],\n" +
                "    \"other_treatment\": null,\n" +
                "    \"diagnosis\": \"Diagnosed\"\n" +
                "}";
    }
}
