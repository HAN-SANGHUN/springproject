/**
 * File : DateStringConverter.java
 *
 * Copyright (C) 2012 - 2013, KONA I Co.Ltd.
 * All rights reserved.
 *
 * This program is a property of KONA I. you can not redistribute it and/or modify it
 * without any permission of KONA I.
 *
 * @author			: Kim,HyeongRae(hrkim@konai.co.kr)
 */
package com.konai;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.Assert;

/**
 * This class convert the data between the Date and the String.
 * 
 * @author Kim,HyeongRae
 * @version 1.0
 * @since 1.0
 *
 */
public class DateStringConverter {	
	/**
	 * Convert the Date from the String.
	 * @param strDate
	 * 				the String of the date.
	 * @return the Date of the date.
	 */
	public static Date convertDate(String strDate) {
		Assert.hasLength(strDate);		
		
		Date convertDate = null;		
		SimpleDateFormat hyphenTypeFormatter = new SimpleDateFormat("yy-MM-dd");		
		String[] tempStr = null;
		tempStr = strDate.split("-");
		
		if(tempStr[0].length() == 4){
			// year(2) + month(2) + day(2)
			strDate = tempStr[0].substring(2) + "-" + tempStr[1] + "-" + tempStr[2];
		}	
		
		try {		
			// Input Date Format : yy-mm-dd				
			convertDate = new Date(hyphenTypeFormatter.parse(strDate).getTime());				
			return convertDate;			
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.state(false, "Invalid input string: " + convertDate);
			return null;
		}			
	}
	
	/**
	 * Convert the String from the Date.
	 * @param date
	 * 				the Date of the date.
	 * @return the String of the date.
	 */
	public static String convertString(Date date) {
		Assert.hasLength(date.toString());		
		return date.toString();				 
	}	
}