package APIModules;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import Payload.SensorReqBody;
import Utilities.Constants;
import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;

public class Sensor {
	
	String access_token = null;
	public static int id_Sensor;
	String baseUrl = null;
	Login l = new Login(Constants.email, Constants.password, Constants.baseUri);
	
	public void addSensor(int count) {
		String sen = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.addSensor(count))
		.when().post("/sensor")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReusableMethod.rawToJson(sen);
		id_Sensor = js.get("data.sensors[0].id_sensor");
	}
	
	public String getSensor() {
		String sensorCount = given().log().all().queryParam("limit", 100).header("Authorization","Bearer "+l.getAccessToken())
						.when().get("/sensor")
						.then().log().all().assertThat().statusCode(200)
						.extract().response().asString();
				
				return sensorCount;
	}
	
//	public void deleteSensor() {
//		String deleteSensor = given().log().all().header("Authorization","Bearer "+l.getAccessToken())
//		.when().delete("/sensor/"+id_Sensor+"")
//		.then().log().all().assertThat().statusCode(200)
//		.extract().response().asString();
//
//		JsonPath js2 = ReusableMethod.rawToJson(deleteSensor);
//		boolean status = js2.getBoolean("data.sensors[0].deleted");
//		Assert.assertEquals(status, true);
//	}
	
	public void updateSensor() {
		given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.updateSensor())
		.when().put("/sensor/"+id_Sensor+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
	}
	
	public void bulkAllocateSensor() {
		String deleteSensor = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.BulkAllocate(id_Sensor))
		.when().post("/sensor/bulk-allocate")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(deleteSensor);
		int id = js2.getInt("data.sensors[0].id_sensor");
		Assert.assertEquals(id_Sensor, id);
	}
	
	public void bulkUnallocateSensor() {
		String deleteSensor = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.BulkUnallocate(id_Sensor))
		.when().post("/sensor/bulk-un-allocate")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js2 = ReusableMethod.rawToJson(deleteSensor);
		int id = js2.getInt("data.sensors[0].id_sensor");
		Assert.assertEquals(id_Sensor, id);
	}
	
	public void bulkActivateSensor() {
		given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.BulkUnallocate(id_Sensor))
		.when().post("/sensor/bulk-activate")
		.then().log().all().assertThat().statusCode(200);
	}
	
	public void bulkDeactivateSensor() {
		 given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
		.body(SensorReqBody.BulkUnallocate(id_Sensor))
		.when().post("/sensor/bulk-deactivate")
		.then().log().all().assertThat().statusCode(200);
	}

	public void bulkDelete() {
		 given().log().all().header("Content-Type","application/json").header("Authorization","Bearer "+l.getAccessToken())
			.body(SensorReqBody.BulkUnallocate(id_Sensor))
			.when().delete("/sensor/bulk-delete")
			.then().log().all().assertThat().statusCode(200);
	}
}
