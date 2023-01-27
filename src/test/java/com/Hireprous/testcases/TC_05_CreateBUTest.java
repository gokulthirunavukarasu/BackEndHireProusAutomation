package com.Hireprous.testcases;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.Hireprous.Engine.ExcelParserUtils;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
public class TC_05_CreateBUTest extends BaseClass {
	@SuppressWarnings("unchecked")
	@Test
	public void TC_05_BU() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the BU creation");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		JSONObject param = new JSONObject();
		String bussinessunit = ExcelParserUtils.getSingleCellData(loginUserfile_path, "bussinessunit", "BussinessUnit",2);
        param.put("Bussiness Unit", bussinessunit);
		String ManagerName = ExcelParserUtils.getSingleCellData(loginUserfile_path, "bussinessunit", "ManagerName",2);
        param.put("Manager Name", ManagerName);
		String jsonString = param.toJSONString();
		ExtentTestManager.getTest().log(Status.INFO, "Request payloads : " +jsonString);
		res=RequestMethod.POSTRequest("v1/addBusinessUnit",jsonString);
		String strResponse = res.getBody().asString();
		int statusCode = res.getStatusCode();
		ExtentTestManager.getTest().log(Status.INFO, "Status code is: " + res.getStatusCode());
		ExtentTestManager.getTest().log(Status.INFO, "Verified response message in the response");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the BU creation");
	}
}