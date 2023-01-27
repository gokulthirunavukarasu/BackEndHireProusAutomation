
package com.Hireprous.testcases;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.Hireprous.Engine.CommonMethod;
import com.Hireprous.Engine.RequestMethod;
import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import java.io.IOException;
import com.Hireprous.Engine.BaseClass;
public class TC_06_GetBUTest extends BaseClass{
	@Test
	public void TC_06_GetBU() throws IOException {
		ExtentTestManager.startTest(methodName +" "+ getLabel(responsetime),"User verifies the BU list");
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Name : " +methodName);
		res=RequestMethod.GETObjectPathparam("v1/searchBusinessUnit?key=QA");
		res.then().assertThat().contentType(ContentType.JSON);
		ExtentTestManager.getTest().log(Status.INFO, "Asserted the JSON content type");        
		ExtentTestManager.getTest().log(Status.INFO, "**Verifying Response Code**");
		CommonMethod.verifyStatusCode(res, HttpStatus.SC_OK);
		String ProjectId =(res.path("id[0]")).toString();
		data.setCellDataByColName("bu","BusinessUnit",2, ProjectId);
		ExtentTestManager.getTest().log(Status.INFO, "**Verified Status code successfully**");
		ExtentTestManager.getTest().log(Status.PASS, "Verifies user registration successfully");
	}
}
