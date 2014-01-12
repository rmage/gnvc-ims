/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.io;

/**
 *
 * @author programmer
 */
public class PossWebUtils extends PossFileUtils {
    

    public static String generateMimeType(String file){
        FileExtension ext = getFileExtension(file);
        switch(ext){
            case TXT:
                return "text/plain";
            case XLS:
                return "application/vnd.ms-excel";
            case CSV:
                return "text/csv";
            case PDF:
                return "application/pdf";
            case RTF:
                return "application/msword";
            case HTML:
                return "text/html";
            default:
                System.out.println("Exception should be occured for extension= " + ext);
                return "tmp";
        }
    }

    public static String generateMimeTypeForFileType(String filetype){
        FileExtension ext = FileExtension.valueOf(filetype.toUpperCase());
        switch(ext){
            case TXT:
                return "text/plain";
            case XLS:
                return "application/vnd.ms-excel";
            case CSV:
                //return "text/csv";
                return "text/comma-separated-values";
            case PDF:
                return "application/pdf";
            case RTF:
                return "application/msword";
            case HTML:
                return "text/html";
            default:
                System.out.println("Exception should be occured for extension= " + ext);
                return "tmp";
        }
    }

    
}
