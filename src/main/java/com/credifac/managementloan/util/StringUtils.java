package com.credifac.managementloan.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringUtils {

    public static String formatCurrency(BigDecimal value) {
        var brazilLocale = new Locale("pt", "br");
        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(brazilLocale);
        return currencyFormater.format(value);
    }

    public static String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        if (phoneNumber.length() == 11) {
            return "(" + phoneNumber.substring(0, 2) + ") "
                    + phoneNumber.substring(2, 7) + "-"
                    + phoneNumber.substring(7);
        } else if (phoneNumber.length() == 10) {
            return "(" + phoneNumber.substring(0, 2) + ") "
                    + phoneNumber.substring(2, 6) + "-"
                    + phoneNumber.substring(6);
        } else {
            return phoneNumber;
        }
    }

    public static String formatDateToBrazilianFormat(LocalDate date) {
        var brazilianFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(brazilianFormatter);
    }
}
