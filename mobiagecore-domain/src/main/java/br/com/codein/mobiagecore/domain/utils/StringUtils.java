package br.com.codein.mobiagecore.domain.utils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by gelatti on 26/05/17.
 */
public class StringUtils {

    public static String doubleQuotedStr(String string) {
        if (string == null) {
            return null;
        }
        if (string.equalsIgnoreCase("")) {
            return "";
        }
        int lastPos = string.length() - 1;

        if (lastPos < 0 || (string.charAt(0) == '"'
                && string.charAt(lastPos) == '"')) {
            return string;
        }

        return '"' + string + '"';
    }

    public static String singleQuotedStr(String string) {
        if (string == null) {
            return null;
        }
        if (string.equalsIgnoreCase("")) {
            return "";
        }
        int lastPos = string.length() - 1;

        if (lastPos < 0 || (string.charAt(0) == '\''
                && string.charAt(lastPos) == '\'')) {
            return string;
        }

        return '\'' + string + '\'';
    }

    public static String coalesceNotBlankString(String ...strings){
        try {
            return Arrays.stream(strings).filter(str -> !org.apache.commons.lang3.StringUtils.isBlank(str)).findFirst().get();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public static Boolean isInteger(String string){
        try {
            Integer.parseInt(string);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static Boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

}
