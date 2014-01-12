/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.util;

import java.security.MessageDigest;
import org.apache.log4j.Logger;

/**
 *
 * @gnv solution
 */
public class EncryptionUtility {

    private static Logger logger = Logger.getLogger(EncryptionUtility.class);

    public static String getEncrypted(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((password+"iw76r6u2kk1o09sjsuewq").getBytes());

            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return (sb.toString());
        } catch (Exception e) {
            logger.error(e, e);
            return password;
        }
    }
}
