package test.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.BaseTest;

import java.util.ArrayList;

import static automation.framework.reports.ExtentListener.writeLog;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetMethodTest extends BaseTest {
    String TAG = this.getClass().toString();

    @Test(description = "This test method will validate get method test1",groups = {"Regression"})
    public void getAllResponse(){
        writeLog("Get all values from json");
        given().get().then().assertThat().statusCode(200);
        Response response = given().get().then().extract().response();
        System.out.println(""+response.path("title"));
    }
}
