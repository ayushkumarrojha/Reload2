package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class LoginReqBod {
	
	public static String loginBod(String email, String password) {
		return "{\n"
				+ "    \"username\": \""+email+"\",\n"
				+ "    \"password\": \""+password+"\"\n"
				+ "}";
	}
	
	public static String resetBod() {
		JsonPath js = ReadJsonFile.read("/src/test/java/Files/login.json");
		return "{\n"
				+ "    \"oldPassword\": \""+js.getString("oldPassword")+"\",\n"
				+ "    \"newPassword\": \""+js.getString("newPassword")+"\",\n"
				+ "    \"confirmPassword\": \""+js.getString("confirmPassword")+"\"\n"
				+ "}";
	}
	
//	public static String fpBod() {
//		return "{\n"
//				+ "    \"username\": \"ayushk@geekyants.com\"\n"
//				+ "}";
//	}

}
