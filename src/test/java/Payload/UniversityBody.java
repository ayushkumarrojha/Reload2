package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class UniversityBody {
	public static JsonPath js = ReadJsonFile.read("/Users/ayushkumarojha/Documents/Reload2/src/test/java/Payload/university.json");
	
	public static String addUniversity(int count) {
		
		String payload = "{\n"
				+ "    \"name\": \""+js.getString("name")+count+"\",\n"
				+ "    \"full_name\": \""+js.getString("full_name")+"\",\n"
				+ "    \"logo\": \""+js.getString("logo")+"\",\n"
				+ "    \"address1\": \""+js.getString("address1")+"\",\n"
				+ "    \"address2\": \""+js.getString("address2")+"\",\n"
				+ "    \"city\": \""+js.getString("city")+"\",\n"
				+ "    \"zip\": \""+js.getString("zip")+"\",\n"
				+ "    \"id_state\": "+js.getInt("id_status")+",\n"
				+ "    \"id_country\": "+js.getInt("id_country")+"\n"
				+ "}";
		return payload;
	}
	
	public static String updateOrg(int count) {
		String  payload = "{\n"
				+ "  \"full_name\": \""+js.getString("new_full_name")+count+"\"\n"
				+ "}";
		return payload;
	}
	
}
