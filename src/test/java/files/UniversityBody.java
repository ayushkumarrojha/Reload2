package files;

public class UniversityBody {
	public static String addUniversity(String name) {
		String payload = "{\n"
				+ "    \"name\": \""+name+"\",\n"
				+ "    \"full_name\": \"university one\",\n"
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
}
