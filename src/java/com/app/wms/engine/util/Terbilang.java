/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.util;

/**
 *
 * @author kaskus
 * Source code diperoleh dari snapDragon dengan link
 * http://www.kaskus.us/showpost.php?p=171986236&postcount=6
 */
public class Terbilang {
    public static String bilang(long uang) {
        String nama[] = {"nol", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan"};
        String besar[] = {"triliun", "milyar", "juta", "ribu", ""};
        if (uang == 0) {
            return nama[0];
        }
        long p = 1000000000000l;
        String hasil = "";
        for (int i = 0; i < besar.length; i++, p /= 1000) {
            if (uang < p) {
                continue;
            }
            long temp = uang / p;
            boolean seribu = p == 1000;
            if (temp >= 100) {
                hasil += nama[(int) temp / 100] + " ratus ";
                temp %= 100;
                seribu = false;
            }
            if (temp >= 11 && temp <= 19) {
                hasil += nama[(int) temp - 10] + " belas ";
                temp = 0;
                seribu = false;
            }
            if (temp >= 10) {
                hasil += nama[(int) temp / 10] + " puluh ";
                temp %= 10;
            }
            if (temp > 0) {
                if (seribu && temp == 1) {
                    hasil += "se";
                } else {
                    hasil += nama[(int) temp] + " ";
                }
            }
            uang %= p;
            hasil += besar[i] + " ";
        }
        hasil = hasil.replaceAll("satu ratus", "seratus");
        hasil = hasil.replaceAll("satu belas", "sebelas");
        hasil = hasil.replaceAll("satu puluh", "sepuluh");
        return hasil.trim();
    }
}
