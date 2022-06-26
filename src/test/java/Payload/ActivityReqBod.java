package Payload;

public class ActivityReqBod {

    public static String addActivity(int visitId){
        return "{\n" +
                "    \"id_visit\": "+visitId+",\n" +
                "    \"id_player\":1,\n" +
                "    \"id_activity\": 31,\n" +
                "    \"id_team\": 1,\n" +
                "    \"id_org\": 1\n" +
                "}";
    }

    public static String updateActivity(int visitId, int playerActivityId){
        return "{\n" +
                "    \"id_visit\": "+visitId+",\n" +
                "    \"id_player\": 1,\n" +
                "    \"id_activity\": 31,\n" +
                "    \"id_team\": 1,\n" +
                "    \"id_org\": 1,\n" +
                "    \"id_visit_player_activity\": "+playerActivityId+",\n" +
                "    \"id_status\": 1\n" +
                "}";
    }

    public static String addTrial(int playerActivityId){
        return "{\n" +
                "    \"id_activity\": 31,\n" +
                "    \"id_player\": 1,\n" +
                "    \"id_visit_player_activity\": "+playerActivityId+",\n" +
                "    \"assistive_device\": 1\n" +
                "}";
    }
}
