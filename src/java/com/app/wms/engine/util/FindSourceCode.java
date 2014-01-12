/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.util;

import java.math.BigDecimal;
import javax.swing.text.html.HTML;

/**
 *
 * @author sistema
 */
public class FindSourceCode {

	static enum NumeralEnum {
		WIC_ADMIN, WIC_SPV, WIC_BM, WIC_SM, SCM , DSA_ADMIN, DSA_SPV, DSA_BM, DSA_SM;
	}
//	public static void main(final String[] args) {
       public static String getSourceCode(String SourceCode){

	final NumeralEnum numeral;

		try {
			numeral = NumeralEnum.valueOf(SourceCode);
		} catch(IllegalArgumentException e) {
			throw new RuntimeException("String has no matching NumeralEnum value");
		}

 		switch(numeral) {
		case WIC_ADMIN:
                   return "WIC_SPV";
                case WIC_SPV:
                    return "WIC_BM";
                case WIC_BM:
                    return "WIC_SM";
                case WIC_SM:
                    return "SCM";
                case DSA_ADMIN:
                    return "DSA_SPV";
                case DSA_SPV:
                    return "DSA_BM";
                case DSA_BM:
                    return "DSA_SM";
                case DSA_SM:
                    return "SCM";

            default:
                System.out.println("Exception should be occured for extension= " + SourceCode);
                return SourceCode;
        }
    }
}
