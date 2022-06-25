package Payload;

import Utilities.ReadJsonFile;
import io.restassured.path.json.JsonPath;

public class SensorReqBody {
	
	public static JsonPath js = ReadJsonFile.read("/src/test/java/Files/sensor.json");
//	static int num = (int)(Math.random()*100);
//	static String name = Integer.toString(num);
	
	public static String addSensor(int count) {
		return "{\n"
				+ "  \"name\": \""+js.getString("name")+(count)+"\",\n"
				+ "  \"mac\": \""+js.getString("mac")+(count+10)+"\"\n"
				+ "}";
	}
	
	public static String updateSensor() {
		return "{\n"
				+ "    \"deleted\": false\n"
				+ "}";
	}
	
	
	public static String BulkAllocate(int sensorId) {
		return "{\n"
				+ "	    \"id_org\": 1,\n"
				+ "	    \"sensor_ids\": ["+sensorId+"]\n"
				+ "	}";
	}
	
	public static String BulkUnallocate(int sensorId) {
		return "{\n"
				+ "	    \"sensor_ids\": ["+sensorId+"]\n"
				+ "	}";
	}

}
