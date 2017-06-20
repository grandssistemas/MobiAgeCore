package br.com.codein.mobiagecore.domain.utils;

import br.com.codein.mobiagecore.domain.exceptions.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gelatti on 26/05/17.
 */
public class DateUtils {
    private static final String DATE_FORMAT_INVOICE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static Date parseStringToDateInvoiceFormat(String date){
        return parseStringToDate(date, DATE_FORMAT_INVOICE);
    }

    public static Date parseStringToDate(String date, String format){
        SimpleDateFormat parser = new SimpleDateFormat(format);
        try {
            return parser.parse(date);
        } catch (ParseException e) {
            throw new ValidationException("Invalid date.");
        } catch (NullPointerException e){
            return null;
        }
    }


}
