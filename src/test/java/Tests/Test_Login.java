package Tests;

import org.testng.annotations.Test;

import APIModules.Login;
import Utilities.Constants;
import io.qameta.allure.Description;

public class Test_Login {

	String email = System.getenv("EMAIL");
	String password = System.getenv("PASSWORD");
	String baseUri = System.getenv("BASEURI");
	Login login = new Login(email, password, baseUri);
	
	@Test(priority=1, enabled = true)
	@Description("Validate if user is able login")
	public void login_test() {
		login.login(Constants.email, Constants.password, Constants.baseUri);
	}
	
	@Test(priority=2, enabled = true)
	@Description("Validate if user is able to reset password")
	public void resetPassword_test() {
		login.resetPassword();
	}
	
	@Test(priority=3, enabled = true)
	@Description("Validate if user is able to refresh password")
	public void refreshPassword_test() {
		login.refreshToken();
	}
	
	@Test(priority=4, enabled = true)
	@Description("Validate if user is able to get his profile")
	public void whoAmI_test() {
		login.whoAmI();
	}

	@Test(priority=5, enabled = true)
	@Description("Validate if user is able to get all other users")
	public void getUsers_test() {
		login.getUsers();
	}

}
