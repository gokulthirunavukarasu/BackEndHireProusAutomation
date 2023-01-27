package com.Hireprous.testcases;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.Hireprous.Engine.ExcelParserUtils;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
public class TC_07_CreateVendorTest extends BaseClass {
	@SuppressWarnings("unchecked")
	@Test
	public void TC_07_CreateVendor() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the Vendor creation");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		JSONObject param = new JSONObject();
		String VendorName = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "VendorName",2);
        param.put("Vendor Name", VendorName);
		String Email = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "Email",2);
        param.put("Email", Email);
		String BusinessUnit = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "ContactNumber",2);
        param.put("Business Unit", BusinessUnit);
		String NoOfOpenings = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "Location",2);
        param.put("No of Openings", NoOfOpenings);
		String CurrencyType = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "VendorPriority",2);
        param.put("Currency Type", CurrencyType);
		String ProjectStartDate  = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "Address",2);
        param.put("Project Start Date", ProjectStartDate);
		String PlacementFor  = ExcelParserUtils.getSingleCellData(loginUserfile_path, "vendor", "CCEmailID",2);
        String jsonString = param.toJSONString();
        ExtentTestManager.getTest().log(Status.INFO, "Request payloads : " +jsonString);
    	res=RequestMethod.POSTRequest("v1/addVendor",jsonString);
	    ExtentTestManager.getTest().log(Status.INFO, "Status code is: " + res.getStatusCode());
		ExtentTestManager.getTest().log(Status.INFO, "Verified response message in the response");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the Vendor creation");
	}
}