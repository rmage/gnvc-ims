/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.util;

/**
 * @author   : JECO
 * @since    : Jul 9, 2010, 11:58:23 PM
 */
public class StringUtils {

    public static boolean isEmpty(String param){
        return (param==null || "".equals(param))?true:false;
    }

    public static String empty(String param){
        return (param==null?"":param);
    }

}