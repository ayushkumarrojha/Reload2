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
	
	public static String addTeam(String shortName, String teamName) {
		String payload = "{\n"
				+ "  \"short_name\": \"RM"+name+"\",\n"
				+ "  \"name\": \"RM"+name+"\",\n"
				+ "  \"label\": \"Real Madrid\",\n"
				+ "  \"id_sport\": 1,\n"
				+ "  \"id_gender\": 1,\n"
				+ "  \"description\": \"Real Madrid is one of the top clubs of Europe with 13 champions league titles to its name\",\n"
				+ "  \"logo\": \"https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/800px-Real_Madrid_CF.svg.png\"\n"
				+ "}";
		return payload;
	}
	
}
