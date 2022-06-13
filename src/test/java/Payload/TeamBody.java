package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class TeamBody {
	
	public static JsonPath js = ReadJsonFile.read("/Users/ayushkumarojha/Documents/Reload2/src/test/java/Payload/team.json");
	
	public static String addTeam(int count) {
		String payload = "{\n"
				+ "  \"short_name\": \""+js.getString("short_name")+count+"\",\n"
				+ "  \"name\": \""+js.getString("name")+count+"\",\n"
				+ "  \"label\": \""+js.getString("label")+"\",\n"
				+ "  \"id_sport\": "+js.getInt("id_sport")+",\n"
				+ "  \"id_gender\": "+js.getInt("id_gender")+",\n"
				+ "  \"description\": \""+js.getString("description")+"\",\n"
				+ "  \"logo\": \""+js.getString("logo")+"\"\n"
				+ "}";
		return payload;
	}
	
	public static String updateTeam(int count) {
		String payload = "{\n"
				+ "  \"name\": \""+js.getString("new_name")+count+"\"\n"
				+ "}";
		return payload;
	}

}
