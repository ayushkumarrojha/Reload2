package Tests;

import Utilities.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import APIModules.Sensor;
import io.qameta.allure.Description;

import java.util.List;


public class Test_Sensor {
	
	Sensor sensor = new Sensor();

	int count = 0;
	
	@Test(priority = 1, enabled = true)
	@Description("Validate if user is able to get all the Sensors")
	public void getSensor_test() {
		String getSensor = sensor.getSensor();

		JsonPath js = ReusableMethod.rawToJson(getSensor);
		List<String> teams = js.getList("data.sensors");
		count = teams.size()+1;
		
	}
	
	@Test(priority = 2, enabled = true)
	@Description("Validate if user is able to add Sensors")
	public void addSensor_test() {
		sensor.addSensor(this.count);
	}
	
//	@Test(priority = 3, enabled = true)
//	@Description("Validate if user is able to delete Sensors")
//	public void deleteSensor_test() {
//		sensor.deleteSensor();
//	}
	
	@Test(priority = 3, enabled = true)
	@Description("Validate if user is able to activateed delete Sensors")
	public void updateSensor_test() {
		sensor.updateSensor();
	}
	
	@Test(priority = 4, enabled = true)
	@Description("Validate if user is able to allocate Sensors")
	public void bulkAllocate_test() {
		sensor.bulkAllocateSensor();
	}
	
	@Test(priority = 5, enabled = true)
	@Description("Validate if user is able to unallocate Sensors")
	public void bulkUnallocate_test() {
		sensor.bulkUnallocateSensor();
	}
	
	@Test(priority = 7, enabled = true)
	@Description("Validate if user is able to activate Sensors")
	public void activate_test() {
		sensor.bulkActivateSensor();
	}
	
	@Test(priority = 6, enabled = true)
	@Description("Validate if user is able to deactivate Sensors")
	public void deactivartest_test() {
		sensor.bulkDeactivateSensor();
	}
	
//	@Test(priority = 8, enabled = true)
//	@Description("Validate if user is able to deactivate Sensors")
//	public void bulkDeleteSensor() {
//		sensor.bulkDelete();
//	}

}
