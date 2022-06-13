package Tests;

import java.util.List;

import org.testng.annotations.Test;

import Demo.University;
import Utilities.ReusableMethod;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;

public class Test_Universities {
	
	University uni = new University();
	int count = 0;
	
	@Test(priority = 1, enabled = true)
	@Description("Validate if user is able to get all the university")
	public void getUniversity_test() {
		String getUni = uni.getUniversity();
		
		JsonPath js = ReusableMethod.rawToJson(getUni);
		List<String> org = js.getList("data.organizations");
		count = org.size()+1;
	}
	
	@Test(priority = 2)
	@Description("Validate if user is able to add university")
	public void addUniversity_test() {
		uni.addUniversity(this.count);
	}
	
	@Test(priority = 3)
	@Description("Validate if user is able to update university")
	public void updateUniversity_test() {
		uni.updateUniversity(this.count);
	}
	
	@Test(priority = 4)
	@Description("Validate if user is able to delete university")
	public void deleteUniversity_test() {
		uni.deleteUniversity();
	}

}
