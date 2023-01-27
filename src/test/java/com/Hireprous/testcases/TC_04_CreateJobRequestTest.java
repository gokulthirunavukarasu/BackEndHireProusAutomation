package com.Hireprous.testcases;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.Hireprous.Engine.ExcelParserUtils;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
public class TC_04_CreateJobRequestTest extends BaseClass {
	@SuppressWarnings("unchecked")
	@Test
	public void TC_04_CreateJobRequest() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the job request creation");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		JSONObject param = new JSONObject();
		String Customer = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Customer",2);
		ExtentTestManager.getTest().log(Status.INFO, "Customer : " +Customer);
		param.put("Customer", Customer);
		String RecruitmentRole = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Recruitment Role",2);
        param.put("Recruitment Role", RecruitmentRole);
		String BusinessUnit = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Business Unit",2);
		param.put("Business Unit", BusinessUnit);
		String NoOfOpenings = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "No of Openings",2);
		param.put("No of Openings", NoOfOpenings);
		String CurrencyType = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Currency Type",2);
		param.put("Currency Type", CurrencyType);
		String ProjectStartDate  = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Project Start Date ",2);
		param.put("Project Start Date", ProjectStartDate);
		String PlacementFor  = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Placement For",2);
		param.put("Placement For", PlacementFor);
		String JobDescription  = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Job Description",2);
		param.put("Job Description", JobDescription);
		String MandatorySkills   = ExcelParserUtils.getSingleCellData(loginUserfile_path, "jobrequest", "Mandatory Skills ",2);
		param.put("Mandatory Skills ", MandatorySkills );
		String jsonString = param.toJSONString();
		ExtentTestManager.getTest().log(Status.INFO, "Request payloads : " +jsonString );
		res=RequestMethod.POSTRequest("v1/addJobRequest",jsonString);
		String strResponse = res.getBody().asString();
		int statusCode = res.getStatusCode();
		ExtentTestManager.getTest().log(Status.INFO, "Status code is: " + res.getStatusCode());
		ExtentTestManager.getTest().log(Status.INFO, "Verified response message in the response");
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the job request creation");
	}
}