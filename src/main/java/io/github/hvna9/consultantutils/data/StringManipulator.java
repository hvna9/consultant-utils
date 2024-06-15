package io.github.hvna9.consultantutils.data;

import io.github.hvna9.consultantutils.constants.Placeholder;
import org.apache.commons.lang3.StringUtils;

public class StringManipulator {
	
	/**
	 * This method is defined to reverse the string passed as parameter.
	 * 
	 * @param str
	 * @return the reversed string
	 */
	public static String reverse(String str) {
		if(StringUtils.isEmpty(str))
			throw new IllegalArgumentException("Invalid string");
		
		if(str.length() < 2)
			return str;
		
		char[] normal = str.toCharArray();
		char[] reversed = new char[normal.length];
		
		int strLen = normal.length;
		
		for(int i = 0; i < strLen; i++) {
			reversed[i] = normal[strLen - (i+1)];
		}
		
		return new String(reversed);
	}
	
	/**
	 * This method return a placeholder string with a specified length.
	 * The specified length correspond to the number of characters in the returned string.
	 * 
	 * It returns a standard "Lorem Ipsum" text, if you use a fixed size of 10, 100, 200, 500 or 1000.
	 * It returns a combined "Lorem Ipsum" text, with a number of character specified by size parameter.
	 * 
	 * @param size
	 * @return a placeholder string
	 */
	public static String generatePleaceholder(int size) {
		if(size == 10)
			return Placeholder.LOREM_IPSUM_10;
		if(size == 100)
			return Placeholder.LOREM_IPSUM_100;
		if(size == 200)
			return Placeholder.LOREM_IPSUM_200;
		if(size == 500)
			return Placeholder.LOREM_IPSUM_500;
		if(size == 1000)
			return Placeholder.LOREM_IPSUM_1000;
		
		if(size < 1000)
			return Placeholder.LOREM_IPSUM_1000.substring(0, size);
		else {
			int multiplier = (int) Math.ceil(size/1000d);
			
			String concatString = "";
			for( ; multiplier > 0; multiplier--) {
				concatString += Placeholder.LOREM_IPSUM_1000 + " ";
			}
			
			String retVal = concatString.substring(0, size);
			
			//Terminate all phrases with '.'
			if(retVal.endsWith(", ")) 
				return retVal.substring(0, retVal.length() - 2) + ".";
			else 
				return retVal.substring(0, retVal.length() - 1) + ".";
		}
	}

}
