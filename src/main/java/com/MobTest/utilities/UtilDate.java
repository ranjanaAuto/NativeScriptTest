package com.MobTest.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.Assert;


public class UtilDate {
	
	/**
	 * Date utl 
	 * @param dateFormat
	 * @return todays date in expected format
	 */
	public static String dateNow(String dateFormat){
		String str="";
		try{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			str = sdf.format(cal.getTime());
		}catch(Exception ex){
			Assert.fail(ex.toString());
		}	    
		return str;
	}

}