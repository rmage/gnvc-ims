/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.util;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.security.Security;
import java.text.DateFormat;
import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author root
 */
public class Utility {

    public boolean valueCheck(Integer value) {
        boolean status = false;
        if (value != null) {
            status = true;
        }
        return status;
    }

    public boolean valueCheck(String value) {
        boolean status = false;
        if (value != null) {
            if (value.length() > 0) {
                status = true;
            }
        }
        return status;
    }

    public static boolean isExist(String value, List<String> listToSearch) {
        boolean isExist = false;
        for (String item : listToSearch) {
            if (value.equalsIgnoreCase(item)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if (d == 0) {
            return 0;
        }
        return Double.valueOf(twoDForm.format(d));
    }

    public static String roundTwoDecimalsToString(BigDecimal number) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        double d = number.doubleValue();
        return twoDForm.format(d);
    }


    public static List<String> getListProvince() {
        List<String> listProvince = new ArrayList<String>();
        listProvince.add("Aceh");
        listProvince.add("Sumatera Utara");
        listProvince.add("Sumatera Barat");
        listProvince.add("Riau");
        listProvince.add("Jambi");
        listProvince.add("Sumatera Selatan");
        listProvince.add("Bengkulu");
        listProvince.add("Lampung");
        listProvince.add("Kep. Bangka Belitung");
        listProvince.add("Kep. Riau");
        listProvince.add("DKI Jakarta");
        listProvince.add("Jawa Barat");
        listProvince.add("Jawa Tengah");
        listProvince.add("DI Yogyakarta");
        listProvince.add("Jawa Timur");
        listProvince.add("Banten");
        listProvince.add("Bali");
        listProvince.add("Nusa Tenggara Barat");
        listProvince.add("Nusa Tenggara Timur");
        listProvince.add("Kalimantan Barat");
        listProvince.add("Kalimantan Tengah");
        listProvince.add("Kalimantan Selatan");
        listProvince.add("Kalimantan Timur");
        listProvince.add("Sulawesi Utara");
        listProvince.add("Sulawesi Tengah");
        listProvince.add("Sulawesi Selatan");
        listProvince.add("Sulawesi Tenggara");
        listProvince.add("Gorontalo");
        listProvince.add("Sulawesi Barat");
        listProvince.add("Maluku");
        listProvince.add("Maluku Utara");
        listProvince.add("Papua Barat");
        listProvince.add("Papua");
        return listProvince;
    }

    public static Date formatDate(String strDate) {
        Date retDate = null;

        try {
            java.text.SimpleDateFormat formatter;
            java.util.Date dateStr;
            String formattedDate;

            formatter = new SimpleDateFormat(AppConstant.DATE_FORMAT);
            dateStr = formatter.parse(strDate);
            formattedDate = formatter.format(dateStr);
            retDate = formatter.parse(formattedDate);

        } catch (Exception e) {
            // do nothin
        }

        return retDate;
    }

    public static Date formatDateSecond(String strDate) {
        Date retDate = null;

        try {
            java.text.SimpleDateFormat formatter;
            java.util.Date dateStr;
            String formattedDate;

            formatter = new SimpleDateFormat(AppConstant.DATE_FORMAT_SECOND);
            dateStr = formatter.parse(strDate);
            formattedDate = formatter.format(dateStr);
            retDate = formatter.parse(formattedDate);

        } catch (Exception e) {
            // do nothin
        }

        return retDate;
    }

    public static String formatDate(Date date) {
        String retStrDate = "";

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(AppConstant.DATE_FORMAT);
        if (date != null) {
            retStrDate = formatter.format(date);
        }

        return retStrDate;
    }

    public static String formatDateSecond(Date date) {
        String retStrDate = "";

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(AppConstant.DATE_FORMAT_SECOND);
        if (date != null) {
            retStrDate = formatter.format(date);
        }

        return retStrDate;
    }

    public static java.math.BigDecimal formatBigDecimal(String strNumber) {
        java.math.BigDecimal retBigDecimal = null;

        try {
            retBigDecimal = new java.math.BigDecimal(strNumber);
        } catch (Exception e) {
            // do nothin
        }

        return retBigDecimal;
    }
    /* remark by rendra 07/11/2012 4:00 pm
    public static synchronized void sendMailSMTPAuth(String subject, String body, String sender, String recipients) throws Exception {

//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//
//        Properties props = new Properties();
//        props.setProperty("mail.transport.protocol", "smtp");
//        props.setProperty("mail.host", AppConstant.MAIL_HOST);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.quitwait", "false");
//
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(AppConstant.MAIL_USERNAME, AppConstant.MAIL_PASSWORD);
//            }
//        });
//
//        MimeMessage message = new MimeMessage(session);
//        message.setSender(new InternetAddress(sender));
//        message.setSubject(subject);
//        message.setContent(body, "text/plain");
//        if (recipients.indexOf(',') > 0) {
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//        } else {
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
//        }
//
//
//        Transport.send(message);

    }

    public static Date getToday() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        java.util.Date now = cal.getTime();
        return now;
    }
    /*
    public static boolean isMenuRepairDisplayed(String menuCode, HttpServletRequest request) throws Exception {
        
    	if (menuCode.equalsIgnoreCase("REPAIR_CONF_TO_VENDOR")
                || menuCode.equalsIgnoreCase("REPAIR_CONF_FROM_VENDOR")
                || menuCode.equalsIgnoreCase("REPAIR_MV_OUT")
                || menuCode.equalsIgnoreCase("REPAIR_MV")) {
    		
            SvcPicDao daoSvcPic = DaoFactory.createSvcPicDao();
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            List<SvcPic> dtoSvcPic = daoSvcPic.findByUser(new BigDecimal(lu.getUserId()));
            if (dtoSvcPic != null && dtoSvcPic.size() > 0) {
                return true;
            } else {
                return false;
            }
            
        }
        
        return true;
    }
	*/
    public static String generateTransToken() {
        DateFormat longFormatter = new SimpleDateFormat("yyMMddHHmmss");
        long n = Math.round(Math.random() * 1000000);

        String res = longFormatter.format(new Date()) + String.format("%6d", n);

        System.out.println("TransToken:" + res);
        return res;
    }
    
    public static String generateRandomHash() {
        return RandomStringUtils.randomAlphanumeric(10).toLowerCase();
    }
}
