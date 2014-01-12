/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.helper;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author genevacons
 */
public class AppNumberHelper {

    public static boolean isNumeric(String number) {
	boolean isValid = false;

	/*Number: A numeric value will have following format:
	^[-+]?: Starts with an optional "+" or "-" sign.
	[0-9]*: May have one or more digits.
	\\.? : May contain an optional "." (decimal point) character.
	[0-9]+$ : ends with numeric digit.
	 */

	//Initialize reg ex for numeric data.
	String expression = "[0-9]*";
	CharSequence inputStr = number;
	Pattern pattern = Pattern.compile(expression);
	Matcher matcher = pattern.matcher(inputStr);

	if (matcher.matches()) {
	    isValid = true;
	}

	return isValid;
    }

    /**
     * Method 'parseBigDecimal'
     *
     * @param number
     * @return BigDecimal of parameter OR BigDecimal.ZERO if parameter is not numeric
     */
    public static BigDecimal parseBigDecimal(String number) {
	if (!StringHelper.nullOrEmpty(number) && AppNumberHelper.isNumeric(number)) {
	    return new BigDecimal(number);
	} else {
	    BigDecimal defaultValue = BigDecimal.ZERO;
	    return defaultValue;
	}
    }
}
