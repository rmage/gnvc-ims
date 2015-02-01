package com.spfi.ims.helper;

public class StringHelper {

    private static final String[] dataSeparator = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "="};

    public static String[] getDataSeparator(String data, int length) {
        int i = 0;
        String[] separator = new String[length];

        for (String s : dataSeparator) {
            if (!data.contains(s)) {
                separator[i] = s;
                i++;

                if (i >= length) {
                    break;
                }
            }
        }

        return separator;
    }

    public static final String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
