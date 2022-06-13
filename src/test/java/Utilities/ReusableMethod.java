package Utilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Payload.LoginReqBod;
import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	public static JsonPath rawToJson(String response) {
		JsonPath js1 = new JsonPath(response);
		return js1;
	}


	public static String LoginAsSuperAdmin() {
		String response = given().log().all().header("Content-Type","application/json")
				.body(LoginReqBod.loginBod("as", "bs")).when().post("/auth/login")
				.then().log().all().assertThat().statusCode(200).body("success", equalTo(true))
				.header("X-Powered-By", "Express").extract().response().asString();
				
				System.out.println(response);
				JsonPath js = ReusableMethod.rawToJson(response);//for parsing json
				String accToken = js.getString("data.authToken.accessToken");
				return accToken;
	}
}
