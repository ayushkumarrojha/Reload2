package files;

public class LoginReqBod {
	
	public static String loginBod() {
		return "{\n"
				+ "    \"username\": \"ayush2394@outlook.com\",\n"
				+ "    \"password\": \"reload\"\n"
				+ "}";
	}
	
	public static String resetBod() {
		return "{\n"
				+ "    \"oldPassword\": \"reload\",\n"
				+ "    \"newPassword\": \"reload\",\n"
				+ "    \"confirmPassword\": \"reload\"\n"
				+ "}";
	}
	
	public static String fpBod() {
		return "{\n"
				+ "    \"username\": \"ayush2394@outlook.com\"\n"
				+ "}";
	}

}
