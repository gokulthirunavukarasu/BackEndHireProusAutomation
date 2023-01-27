package com.Hireprous.testcases;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.Hireprous.Engine.ExcelParserUtils;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
public class TC_01_LoginTest extends BaseClass {
	@SuppressWarnings("unchecked")
	@Test
	public void TC_01_Login() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the created project");
	    ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		String email = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Email",2);
		String password = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Password",2);
		JSONObject param = new JSONObject();
		param.put("email", email);
		param.put("Password", password);
		String jsonString = param.toJSONString();
		ExtentTestManager.getTest().log(Status.INFO, "jsonString : " +jsonString);
		res=RequestMethod.POSTRequest("v1/login",jsonString);
		res = res.then().body("any { it.key == 'id' }", is(notNullValue())).extract().response();
		ExtentTestManager.getTest().log(Status.INFO, "Status code is: " + res.getStatusCode());
		ExtentTestManager.getTest().log(Status.INFO, "Verified response message in the response");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "Verifies credentials and Writes token in the excel sheet for authenticated user");
	}
}