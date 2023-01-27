package com.Hireprous.Engine;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
public class GenerateData extends BaseClass {
	

	public static String getUSCityName() {
		String USCity = USfaker.address().cityName();
		return USCity;
		
	}
	
	public static String getUSStateName() {
		String USState = USfaker.address().state();
		return USState;
		
	}
	
	public static String getUSStreetAddress() {
		String USAddress = USfaker.address().streetAddress();
		return USAddress;
		
	}
	
	public static String getUSJobTitle() {
		String JobTitle = USfaker.job().title();
		return JobTitle;
		
	}
	
	public static String getUSZipCode() {
		String USPostalCode = USfaker.address().zipCode();
		return USPostalCode;
		
	}
	public static String getEmail() {
		String randomString = RandomStringUtils.randomAlphabetic(3);
		return "test"+randomString+"@gmail.com";
		
	}
	public static String getOrganization() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "testcompany"+randomString;
		
	}
	public static String getCompany() {
		String randomString = randomNumber();
		return "DDTestCompany"+randomString;
		
	}
	public static String getFirstName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "test"+randomString;
		
	}
	public static String getLastName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "user"+ randomString;
		
	}
	public static String getProjectName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "Test Project"+ randomString;
		
	}
	public static String getNumeric() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return  randomString;
		
	}
	public static String getPostalcode() {
		String randomString = RandomStringUtils.randomNumeric(6);
		return  randomString;
		
	}
	public static String getTestString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return "Test"+ randomString;
		
	}
	public static String getPhone() {
		String randomString = RandomStringUtils.randomNumeric(10);
		return  randomString;
		
	}
	
	public static String getPortfolioName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "Test Portfolio"+ randomString;
		
	}
	
	public static String getHSRName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "Test HSR"+ randomString;
		
	}
	
	public static String getSubsetName() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return "TestSubset"+ randomString;
		
	}
	
	public static String getCityName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "test city"+randomString;
		
	}
	
	public static String getStateName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return "test state"+randomString;
		
	}
	
	public static String getPostalCode() {
		String randomString = RandomStringUtils.randomNumeric(6);
		return "PostalCode"+ randomString;
		
	}
	
	public static String getScore() {
		String randomString = RandomStringUtils.randomNumeric(2);
		return  randomString;
		
	}
	
	public static String getCreditName() {
		String randomString = "Test Credit" + RandomStringUtils.randomNumeric(6);
		return  randomString;
		
	}
	
	public static String GenerateCurrentDate() {
		LocalDateTime current = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);
        return formatted;
	}
	public static String GeneratePreviousYear() {
		LocalDateTime current = LocalDateTime.now();
		current = current.minusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);
        System.out.println("previous year is"+formatted);
        return formatted;
	}
	
	public static String GenerateExpireMonth() {
		LocalDateTime current = LocalDateTime.now();
		current = current.plusMonths(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);
       return formatted;
	}
	
	public static String GenerateRenewalDate() {
		LocalDateTime current = LocalDateTime.now();
		current = current.plusMonths(6);
		current = current.plusYears(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 String nextyear = current.format(formatter);
        System.out.println("Date of this is"+nextyear);
        return nextyear;
	}
	
	public static String GenerateNextYear() {
		LocalDateTime current = LocalDateTime.now();
		current = current.plusYears(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);
        System.out.println("next year is"+formatted);
        return formatted;
	}
	
	public static int GenerateNextYearValue() {
		LocalDateTime current = LocalDateTime.now();
		current = current.plusYears(1);
		int year = current.getYear();
       System.out.println("next year is"+year);
        return year;
	}
	
	public static String GenerateEstimatedDate() {
		LocalDateTime current = LocalDateTime.now().plusDays(25);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);
        return formatted;
	}

	public static String GenerateDateOfBirth() {
	    final int maxAge = 100 * 12 * 31;
	    LocalDateTime current = LocalDateTime.now().minusDays(new Random().nextInt(maxAge));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String formatted = current.format(formatter);
	    System.out.println("dob is"+formatted);
        return formatted;
	    
	}
	
	public static int GetCurrentMonth() {
		LocalDate currentdate = LocalDate.now();
		int currentMonth = currentdate.getMonthValue();
		 System.out.println("Current month: "+currentMonth);
		return currentMonth;
	}
	
	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		return formatter.format(currentDate.getTime());
	}
	
	public static int generateSpaceTypes() {
		Random random = new Random();
		int rand = 0;
		while (true){
		    rand = random.nextInt(35);
		    if(rand !=0 && rand!=10) break;
		}
		return rand;
	}
	
	public static int generateSpaceTypesForV2() {
		Random random = new Random();
		int rand = 0;
		while (true){
		    rand = random.nextInt(35);
		    if(rand !=0 && rand!=10 && rand!=20) break;
		}
		return rand;
	}
	
	public static String randomNumber(){
    	
	        int random_num = 1;
	    Random t = new Random();
	    random_num=	(t.nextInt(800000));
	    RandomNum = String.valueOf(random_num);
	    System.out.println(RandomNum);
		return RandomNum;
		
}

public static String randomNumberBetweenRanges(int firstNum, int LastNum){
	
	Random r = new Random();
	int low = firstNum;
	int high = LastNum;
	int result = r.nextInt(high-low) + low;
	RandomNum = String.valueOf(result);

			return RandomNum;
			
  }


}


