/**
 * here all the string utility operation will be done
 */
package com.oyedost.contactapp.util;

public class StringUtil {

    public static String toCommaSeperatedString(Object[] items) {
        StringBuilder sb = new StringBuilder();

        for (Object item : items) {
            sb.append(item).append(",");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
