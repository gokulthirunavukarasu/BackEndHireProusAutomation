package com.Hireprous.testcases;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.Hireprous.Engine.CommonMethod;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
public class TC_03_GetAllProjectTest extends BaseClass{
	@Test
	public void TC_03_GetAllProject() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the project list");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		CommonMethod.verifyStatusCode(res, HttpStatus.SC_OK);
		res = res.then().body("any { it.key == 'id' }", is(notNullValue())).extract().response();
		res=RequestMethod.GETObjectPathparam("v1/getAllProjects");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the project list");
	}
}
