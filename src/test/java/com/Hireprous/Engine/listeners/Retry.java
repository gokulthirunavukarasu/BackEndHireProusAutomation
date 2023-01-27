package com.Hireprous.Engine.listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.Hireprous.Engine.BaseClass;

public class Retry  extends BaseClass implements IRetryAnalyzer {
    private int count = 0;
    private int maxTry = 3;

// Below method returns 'true' if the test method has to be retried else 'false' 
//and it takes the 'Result' as parameter of the test method that just ran
    public boolean retry(ITestResult iTestResult) {
    	System.out.println("-----------"+res.getStatusCode());
    	if ((!iTestResult.isSuccess()) && (res.getStatusCode()==502 || res.getStatusCode()==429)) {//Check if test not succeed
    		try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                    //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
    		
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }

}
