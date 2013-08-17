package com.vendertool.common.validation;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * A simple util that is commonly used for validations
 */
public class ValidationUtil {
	private static ValidationUtil INSTANCE = null;
	
	/**
	 * Get the singleton instance of the class
	 * 
	 * @return
	 */
	public static synchronized ValidationUtil getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new ValidationUtil();
		}
		return INSTANCE;
	}
	
	/**
	 * Checks for null
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNull(Object obj) {
		return (obj == null);
	}
	
	/**
	 * Checks if the object is not null
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	/**
	 * Checks if the given list is empty.
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isEmpty(List obj) {
		if(obj == null){
			return true;
		}
		return !(obj.size() > 0);
	}
	/**
	 * Checks if the string value is empty
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
		return (isNull(value) || (value.trim().length() <= 0));
	}
	
	/**
	 * Checks if the Decimal 'value' is greater or equal to the 'min-value'
	 * 
	 * Use this for all Number types by converting all Number types like Integer, Long, Double, Float, etc to BigDecimal
	 * Example: If you want to check the dynamic param value is less than or equal to 25 (both values of type int)
	 * use --> ValidationUtil.getInstance().isDecimalMin(new BigDecimal(25), new BigDecimal(param.getValue()), true);
	 * 
	 * @param minValue
	 * @param value
	 * @param includeEqualsInMinCheck
	 * @return
	 */
	public boolean isDecimalMin(BigDecimal minValue, BigDecimal value, boolean includeEqualsInMinCheck) {
		//Treat the value to be checked as minimum
		if(isNull(value) || isNull(minValue)){
			return true;
		}
		
		int result = value.compareTo(minValue);
		return includeEqualsInMinCheck ? result >= 0 : result > 0;
	}
	
	/**
	 * Checks if the Decimal 'value' is less than or equal to the 'max-value'
	 * 
	 * Use this for all Number types by converting all Number types like Integer, Long, Double, Float, etc to BigDecimal
	 * 
	 * @param maxValue
	 * @param value
	 * @param includeEqualsInMaxCheck
	 * @return
	 */
	public boolean isDecimalMax(BigDecimal maxValue, BigDecimal value, boolean includeEqualsInMaxCheck) {
		if(isNull(value) || isNull(maxValue)){
			return true;
		}

		int result = value.compareTo(maxValue);
		return includeEqualsInMaxCheck ? result <= 0 : result < 0;
	}
	
	/**
	 * Checks if the passed date is in the future
	 * 
	 * @param date
	 * @return
	 */
	public boolean future(Date date) {
		if (isNull(date)) {
			return true;
		}
		return date.after(new Date());
	}
	
	/**
	 * Checks if the passed date is in the past
	 * 
	 * @param date
	 * @return
	 */
	public boolean past(Date date) {
		if(isNull(date)) {
			return true;
		}
		return date.before(date);
	}
	
	/**
	 * This methods tries to find the matching regex pattern from the text
	 *  
	 * @param regex
	 * @param value
	 * @return
	 */
	public boolean matchesPattern(String regex, String value) {
		if(isNull(regex) || isNull(value)) {
			return false;
		}
		
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(value);
			return m.matches();
		}
		catch ( PatternSyntaxException e ) {
			return false;
		}
	}
	
	/**
	 * Checks for the size of the string within the min-max range
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean checkStringSize(String value, int min, int max) {
		if(isNull(value) || !isValidMinMaxForSize(min, max)) {
			return true;
		}
		
		int length = value.length();
		return ((length >= min) && (length <= max));
	}
	
	//Internal method to validate the size constraints
	private boolean isValidMinMaxForSize(int min, int max) {
		if((min < 0) || (max < 0) || (max < min)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks for the byte array size
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean checkByteArraySize(byte[] value, int min, int max) { 
		if(isNull(value) || !isValidMinMaxForSize(min, max)) {
			return true;
		}
		
		int length = Array.getLength(value);
		return ((length >= min) && (length <= max));
	}
}
