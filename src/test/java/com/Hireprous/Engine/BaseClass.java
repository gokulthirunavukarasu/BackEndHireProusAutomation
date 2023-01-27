package com.Hireprous.Engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Hireprous.Engine.listeners.ExtentTestManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static final int STATUS_200 = 200;
	public static final int STATUS_401 = 401;
	public static final int STATUS_422 = 422;
	public static final String MESSAGE_401 = "Invalid email or password.";
	public static final String MESSAGE_422 = "The given data was invalid.";
	public static final String TOKEN = "token";
	public static final String LOGIN_INVALID_SHEET = "invalid_Login";
	public static final String loginUserfile_path = System.getProperty("user.dir")
			+ "/src/test/resources/TestData.xlsx";
	public static final String estimatefile_path = System.getProperty("user.dir")
			+ "/src/test/resources/EstimateTestData.xlsx";
	public static final String pdf_path = System.getProperty("user.dir") + "/output";
	public static final String Portfolio_properties_upload_sqft = System.getProperty("user.dir")
			+ "/src/test/resources/upload_portfolio_sqft.xlsm";
	public static final String Portfolio_properties_upload_sqm = System.getProperty("user.dir")
			+ "/src/test/resources/upload_portfolio_sqm.xlsm";
	public static final String emailSheet = "emails";
	public static final String tokenSheet = "verifyToken";
	public static final String emailListSheet = "email_list";
	public static final String CommonDataSheet = "CommonData";
	public static ExcelParserUtils data;
	public static String Environment;
	public static String username;
	public static String password;
	public static String role;
	public static String RandomNum;
	public static final String sheetName = "TestValues";
	public static int rowNumOne = 1;
	public static int rowNumTwo = 2;
	public static int rowNumThree = 3;
	public static int rowNumFour = 4;
	public static int rowNumFive = 5;
	public static String email;
	public static String header;
	
	public static String NoMembership_Header;
	public static OutputStream RequestoutputStream;
	public static OutputStream ResponseoutputStream;
	public static PrintStream RequestprintStream;
	public static PrintStream ResponseprintStream;
	public static RequestLoggingFilter requestLoggingFilter;
	public static ResponseLoggingFilter responseLoggingFilter;
	public static String RequestFilePath;
	public static String ResponseFilePath;
	
	public static Logger logger;
	public static ExtentTest test;
	public static ExtentReports extent;
	public RequestSpecification myRequest;
	public static Response res;
	public int userid;
	public String token;
	
	public String envVar;
	public Properties prop;
	public static String methodName;
	public static long responsetime;
	public static Faker USfaker = new Faker(new Locale("en-US"));
	protected String message = "<p style=\"color:#FF0000\";><B> ERROR ENCOUNTERED ON VALIDATING AND THE ERROR IS : </B></p>";

	@Parameters({ "environment" })
	@BeforeClass(alwaysRun = true)
	public void setup(@Optional("STG") final String environment) throws IOException {
		RequestFilePath = System.getProperty("user.dir") + "/src/test/resources/Request.txt";
		ResponseFilePath = System.getProperty("user.dir") + "/src/test/resources/Response.txt";
		RequestoutputStream = new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/Request.txt");
		 // use your OutputStream that will write where you need it
		ResponseoutputStream = new FileOutputStream(
				System.getProperty("user.dir") + "/src/test/resources/Response.txt");
		RequestprintStream = new PrintStream(RequestoutputStream, true);
		ResponseprintStream = new PrintStream(ResponseoutputStream, true);
		requestLoggingFilter = new RequestLoggingFilter(RequestprintStream);
		responseLoggingFilter = new ResponseLoggingFilter(ResponseprintStream);
		data = new ExcelParserUtils(System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/Environment.properties");
		Environment = environment;
		prop.load(file);
		switch (environment) {
		case "STG":
			envVar = prop.getProperty("ENV_STAGING");
			break;
		case "TEST":
			envVar = prop.getProperty("ENV_TEST");
			break;
		case "QAS":
			envVar = prop.getProperty("ENV_QAS");
			break;
		default:
			envVar = prop.getProperty("ENV_TEST");
		}
		System.out.println("BAse uri is" + envVar);
		RestAssured.baseURI = envVar;
		Object className = this.getClass().getName();
		logger = LogManager.getLogger((String) className);
		// PropertyConfigurator.configure(System.getProperty("user.dir")
		// + "/src/test/resources/log4j.properties");
		// logger.setLevel(Level.DEBUG);
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File logfile = new File((System.getProperty("user.dir") + "/src/test/resources/log4j.properties"));
		context.setConfigLocation(logfile.toURI());
		header = ExcelParserUtils.getSingleCellData(loginUserfile_path, emailSheet, "Securitykey",2);
		
		RestAssured.config=RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout",90000)
                        .setParam("http.connection.timeout", 90000));
		
	}

	@BeforeMethod
		public synchronized static ExtentReports getReporter(Method method) throws IOException {
		methodName = method.getName();
		System.out.println(methodName);
		String OS = System.getProperty("os.name");
		
		if (OS.contains("Window")) {

			RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
		}
	    	final String filePath = "./Report/APITestingExtentReport.html";

	        if (extent == null) {
				 final File CONF = new File(System.getProperty("user.dir")+"/src/test/resources/Extentconfig.json");
				 extent = new ExtentReports();
				 extent.setSystemInfo("Host Name", OS);
				 extent.setSystemInfo("Environment", Environment);
				 extent.setSystemInfo("User Name", "Abhishek Gupta");
					ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
				 extent.attachReporter(spark);
				 spark.loadJSONConfig(CONF);
			 }
			    return extent;

	}

	@AfterMethod
	protected void afterMethod(ITestResult result) throws IOException {
		
		ExtentTestManager.getTest().log(Status.INFO, "Class Name: " + this.getClass().getName());
		ExtentTestManager.getTest().log(Status.INFO, "Response Time in milliseconds : " + responsetime);
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(Status.INFO, CommonMethod.fetchRequest(RequestFilePath));
			ExtentTestManager.getTest().log(Status.INFO, "Response from server: </BR>"+CommonMethod.fetchRequest(ResponseFilePath));
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(Status.SKIP, "Test skipped " + result.getThrowable());
		} else {
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}
		if(responsetime>1000 && result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(Status.INFO, CommonMethod.fetchRequest(RequestFilePath));
			ExtentTestManager.getTest().log(Status.INFO, "Response from server: </BR>"+CommonMethod.fetchRequest(ResponseFilePath));
			
		}
		
		
		//ExtentTestManager.getTest().createNode(HelperMethods.getLabel(responsetime));
		//ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
		extent.flush();

		/*
		 * logger.info("------------------------------------------------------");
		 * logger.info("Method Name: " + methodName);
		 * logger.info("Response Time in MilliSeconds: " + responsetime);
		 * logger.info(res.getDetailedCookies()); // logger.info("Response from API::"+
		 * res.asString());
		 * logger.info("------------------------------------------------------");
		 */
	}
	private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();      
    }
	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();

	}

	public String trailcomma(String str) {
		String trailed = null;
		if (str.indexOf(",") != -1) {
			trailed = str.replaceAll(",", "");
			return trailed;
		} else
			return str;

	}

	public void exceptionMessage(Throwable t) throws IOException {
		String message = "<p style=\"color:#FF0000\";><B> ERROR ENCOUNTERED ON VALIDATING AND THE ERROR IS : </B></p>";
		ExtentTestManager.getTest().log(Status.FAIL,
				message + "<br><p style=\"color:#FF0000\";><B>" + t.getMessage() + "</B></p>");
		Error e1 = new Error(t.getMessage());
		throw e1;
	}
	
public static String getLabel(long responsetime) throws IOException {
		
		if (responsetime <= 1000){
			
	   return ""; //"<span class='badge badge-success'>" + responsetime + " Milliseconds" + "</span>";
		}
		
		else
		{
			
				
		return "<span class='badge badge-danger'>" + responsetime + " Milliseconds" + "</span>";
		}
	    
	    
	}

}
