package files;

public class UniversityBody {
	public static int num = (int)(Math.random()*1000);
	public static String name = Integer.toString(num);
	public static String addUniversity() {
		
		String payload = "{\n"
				+ "    \"name\": \"Uni"+name+"\",\n"
				+ "    \"full_name\": \"OIST\",\n"
				+ "    \"logo\": \"https://www.w3schools.com/howto/img_avatar1.png\",\n"
				+ "    \"address1\": \"test address 1\",\n"
				+ "    \"address2\": \"test address 2\",\n"
				+ "    \"city\": \"test city\",\n"
				+ "    \"zip\": \"135777\",\n"
				+ "    \"id_state\": 1,\n"
				+ "    \"id_country\": 1\n"
				+ "}";
		return payload;
	}
	
	public static String updateOrg(String fn) {
		String  payload = "{\n"
				+ "  \"full_name\": \""+fn+"\"\n"
				+ "}";
		return payload;
	}
	
}
