/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.scheduler;

import java.text.SimpleDateFormat;

/**
 *
 * @author zyrex
 */
public class EtlJob {

    protected static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    protected static String milisToSec(long millis) {
        int menit = (int) millis / 60000;
        int detik = (int) (millis % 60000) / 1000;
        int koma = (int) (millis % 1000) / 10;

        return new String(menit + " menit, " + detik + "." + koma + " detik");
    }
}
