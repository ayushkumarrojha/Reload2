package files;

public class TeamBody {
	
	public static int num = (int)(Math.random()*1000);
	public static String name = Integer.toString(num);
	
	public static String addTeam() {
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
	
	public static String updateTeam() {
		String payload = "{\n"
				+ "  \"name\": \"RM"+(name+1)+"\"\n"
				+ "}";
		return payload;
	}

}
