package Utilities;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

import io.restassured.path.json.JsonPath;

public class ReadJsonFile {
	
	public static JsonPath read(String string) {
		String path = System.getProperty("user.dir");
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(path+string));
			JSONObject jsonObject = (JSONObject)obj;
			String str = (String) jsonObject.toString();
			JsonPath js = ReusableMethod.rawToJson(str);
			return js;
		}catch(Exception e){
			e.printStackTrace();
			return null;
			//String error = "Error While reading json file";
		}
	}

}
