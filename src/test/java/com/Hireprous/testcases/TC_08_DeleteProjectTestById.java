package com.Hireprous.testcases;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.Hireprous.Engine.BaseClass;
import com.Hireprous.Engine.CommonMethod;
import com.Hireprous.Engine.ExcelParserUtils;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
public class TC_08_DeleteProjectTestById extends BaseClass{ 
	@Test
	public void TC_08_DeleteProjectById() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the deleted project");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		String projectid = ExcelParserUtils.getSingleCellData(loginUserfile_path, "project", "ProjectId",2);
		HashMap pathprms = new HashMap();
		pathprms.put("ProjectId", projectid);
		res=RequestMethod.DELETERequest("v1/deleteProjectById/{ProjectId}",pathprms);
		res.then().assertThat().contentType(ContentType.JSON);
		CommonMethod.verifyStatusCode(res, HttpStatus.SC_OK);
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "User verifies the deleted project");
	}
}
