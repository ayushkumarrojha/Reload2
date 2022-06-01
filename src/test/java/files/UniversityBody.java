package files;

public class UniversityBody {
	public static String addUniversity() {
		String payload = "{\n"
				+ "    \"name\": \"Uni 3\",\n"
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
}
