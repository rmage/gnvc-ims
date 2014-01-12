/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jeco
 * @since June 29, 2010. 23:44 GMT+7
 */
public class DBUtil {

    /**
     * Used for data list table paging row.
     * Jeco. June 29, 2010. 23:25
     */
    public static final int PAGING = 10;

    public static String createPagingSql(String query, int page, int paging) {
        try {
            StringBuffer sb = new StringBuffer();
            int end = page * paging;
            int start = end - paging + 1;
            sb.append("SELECT * FROM (SELECT v.*, ROWNUM rn FROM  (");
            sb.append(query);
            sb.append(") v WHERE ROWNUM <= ");
            sb.append(end);
            sb.append(") WHERE rn >= ");
            sb.append(start);
            return sb.toString();
        } catch (Exception ex) {
            return query;
        }
    }

    /**
     * Used for creating where clause sql query.
     * Jeco. June 29, 2010. 23:25
     */
    public static String createWhereSql(String searchCriteria, String searchValue) {
        try {
            StringBuffer sb = new StringBuffer("");
            if ((searchCriteria != null && !"".equals(searchCriteria.trim())) && (searchValue != null && !"".equals(searchValue.trim()))) {
                sb.append(" WHERE ");
                sb.append(searchCriteria);
                sb.append(" LIKE '%");
                sb.append(searchValue);
                sb.append("%' ");

            }
            return sb.toString();
        } catch (Exception ex) {
            System.out.println("ERROR:createWhereSql():"+ ex.getMessage());
            return "";
        }
    }

    /**
     * Used for calculating total pages from given parameters
     * Jeco. July 4, 2010. 01:30
     */
    public static int getTotalPages(int rows, int paging){
        return ((rows>paging)?(((rows/paging)*paging == rows)?rows/paging:rows/paging+1):1);
    }
}
