/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.util;

import java.math.BigInteger;

/**
 *
 * @author ruli
 */
public class SerializeUtil {

    public boolean checkSnInRange(String rangeBeginSn, String rangeEndSn, String toFindSnStart, String toFindSnEnd) {
        boolean isAvailable = false;
        java.util.ArrayList<java.math.BigDecimal> ranges = new java.util.ArrayList<java.math.BigDecimal>();
        ranges = copySnToList(rangeBeginSn, rangeEndSn);
        java.util.ArrayList<java.math.BigDecimal> rangesToFind = new java.util.ArrayList<java.math.BigDecimal>();
        rangesToFind = copySnToList(toFindSnStart, toFindSnEnd);

//        System.out.println("toFindSnStart:" + toFindSnStart);
//        System.out.println("toFindSnEnd:" + toFindSnEnd);
//        System.out.println("Ranges:");
//        for (java.math.BigDecimal itemRange : ranges) {
//            System.out.println(itemRange);
//        }


        if (ranges != null) {
//            if (ranges.contains(new java.math.BigDecimal(toFindSnStart)) || ranges.contains(new java.math.BigDecimal(toFindSnEnd))) {
//                isAvailable = true;
//            }

            for (java.math.BigDecimal itemRange : rangesToFind) {
                if (ranges.contains(itemRange)) {
                    isAvailable = true;
                    break;
                }
            }
        }

        return isAvailable;
    }

    private java.util.ArrayList<java.math.BigDecimal> copySnToList(String snStart, String snEnd) {
        java.util.ArrayList<java.math.BigDecimal> ranges = new java.util.ArrayList<java.math.BigDecimal>();
        java.math.BigDecimal i = new java.math.BigDecimal(snStart);

        while (i.compareTo(new java.math.BigDecimal(snEnd)) <= 0) {
            ranges.add(i);
            i = i.add(new java.math.BigDecimal(1));
        }

        return ranges;
    }

    public boolean checkSnExistInRange(String rangeBeginSn, String rangeEndSn, String toFindSnStart, String toFindSnEnd) {
        boolean isAvailable = false;
        java.util.ArrayList<java.math.BigDecimal> ranges = new java.util.ArrayList<java.math.BigDecimal>();
        ranges = copySnToList(rangeBeginSn, rangeEndSn);
        
        if (ranges != null)
        {
            if (ranges.contains( new java.math.BigDecimal(toFindSnStart) ) && ranges.contains( new java.math.BigDecimal(toFindSnEnd) ))
            {
                isAvailable = true;
            }
        }

        return isAvailable;
    }

}
