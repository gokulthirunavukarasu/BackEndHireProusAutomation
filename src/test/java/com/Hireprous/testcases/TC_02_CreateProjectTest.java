package com.Hireprous.testcases;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.Hireprous.Engine.ExcelParserUtils;
import com.Hireprous.Engine.GenerateData;
import com.aventstack.extentreports.Status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
public class TC_02_CreateProjectTest extends BaseClass {
	@Test(dependsOnMethods = {"com.Hireprous.testcases.TC_01_LoginTest.TC_01_Login" })
	@SuppressWarnings("unchecked")
	public void TC_02_CreateProject() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the created project");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		JSONObject param = new JSONObject();
		String ProjectName = "Automation Project" + GenerateData.getProjectName();
		param.put("projectname",ProjectName);
		String BU = ExcelParserUtils.getSingleCellData(loginUserfile_path, "createproject", "BusinessUnitId",2);
		param.put("BusinessUnit", BU);
		String Custid = ExcelParserUtils.getSingleCellData(loginUserfile_path, "createproject", "CustomerId",2);
		param.put("CustomerId",Custid );
		String status = ExcelParserUtils.getSingleCellData(loginUserfile_path, "createproject", "Status",2);
		param.put("Status", status);
		String jsonString = param.toJSONString();
		ExtentTestManager.getTest().log(Status.INFO, "Request payloads : " +jsonString);
		res=RequestMethod.POSTRequest("v1/addProject",jsonString);
		String strResponse = res.getBody().asString();
		int statusCode = res.getStatusCode();
		res = res.then().body("any { it.key == 'id' }", is(notNullValue())).extract().response();
		ExtentTestManager.getTest().log(Status.INFO, "Status code is: " + res.getStatusCode());
		ExtentTestManager.getTest().log(Status.INFO, "Verified response message in the response");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the created project");
	}
}
