package com.Hireprous.Engine;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.hamcrest.core.Is;
import org.hamcrest.core.StringContains;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CommonMethod extends BaseClass {
	public static String ZipCode = "20037";
	//public static Response res;
	public static String fetchedID;
	public static String ProgramID;
	
	public static void assertResponseKeyValue (String key, String value) {
		
	res.then().assertThat().body(key, Is.is(value));
	}
	
	public static void assertResponseKeyValue (String key, int value) {
		
	res.then().assertThat().body(key, Is.is(value));
	
	}
	
	public static void assertResponseKeyValueContainsText (String key, String containsText) {
		
	res.then().assertThat().body(key, StringContains.containsString(containsText));		
	}
	
	public static void assertResponseHasKey (String key) {
		
	res.then().assertThat().body("$", hasKey(key));
		
	}
	
    public static void assertResponseKeyIsNotNull (String key) {
		
    res.then().assertThat().body("any { it.key == '"+key+"' }", is(notNullValue()));
		
	}
    
    public static String fetchResponseKeyValue (String key) {
		
    	String value =res.path(key).toString();
    	return value;
    }
	

	public static int getStatusCode(Response response){
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public static void verifyStatusCode(Response response, int status){
		int StatusCode = getStatusCode(response);
		
		Assert.assertEquals(StatusCode, status,"Verifying status code");
	}
	
	public static void verifyStatusMessage(Response response, String status){
		Assert.assertEquals(getStatusCode(response), status);
	}
	
	public static void responsetimevalidation(String Url) {
		given().when()
				.get(Url)
				.then()
				.time(lessThan(50000L));
	}
	
	public static void WaitForAPITimeSynchronization(int TimeInSeconds) {
		
	Awaitility.await().atMost(TimeInSeconds, TimeUnit.SECONDS);
	
	}
	
	public static String fetchRequest(String FilePath) throws IOException {
		
		String fileAsString;
	BufferedReader br = new BufferedReader(new FileReader(FilePath));
    try {
    	
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("<br />");
            line = br.readLine();
        }
        fileAsString = sb.toString();
    } finally {
        br.close();
    }
    //System.out.println(fileAsString);
    return fileAsString;
	}
	
	
    public static int GethighestNumberFromArray(int array[]) {
    	
    	 int arr[] = array;
    	   
    	    {
    	        int i;
    	         
    	        // Initialize maximum element
    	        int max = arr[0];
    	         
    	        // Traverse array elements from second and
    	        // compare every element with current max
    	        for (i = 1; i < arr.length; i++)
    	            if (arr[i] > max)
    	                max = arr[i];
    	         
    	        return max;
    	        
    	    }
    }
	
	public static void filewriteID(String url) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fetchedID);
		bw.close();
	}
	
public static void filewriteResponse(String url, String response) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(response);
		bw.close();
	}
	

	public static void testlog(String log,String message){
		System.out.println(test);
		switch(log){
		
		case "Pass":
			test.log(Status.PASS, message);
			break;
			
		case "Info":
			test.log(Status.INFO, message);
			break;
			
		 default:
	     	
	     	System.out.println("Not Valid Input");
		}
		
		
	}
	
		
	public static String filereadID(String url) throws IOException{
		
		 FileReader inputFile = new FileReader(url);
		
	     //Instantiate the BufferedReader Class
	     BufferedReader bufferReader = new BufferedReader(inputFile);

	     //Variable to hold the one line data
	     
	     String text;
	     // Read file line by line and print on the console
	     while ((text = bufferReader.readLine()) != null)   {
	        
	          fetchedID=text;
	         //System.out.println(CommonMethod.ProgramID);   
	     }
	        
	      //Close the buffer reader
	        bufferReader.close();
	        return fetchedID;
	}
	

	
}
