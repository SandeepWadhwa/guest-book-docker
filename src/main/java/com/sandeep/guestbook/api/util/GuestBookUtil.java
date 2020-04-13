package com.sandeep.guestbook.api.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GuestBookUtil {

    public static boolean isFilled(Object aObj) {
        if (aObj == null) {
            return false;
        }


        if (aObj instanceof String) {
            if (((String) aObj).trim().length() == 0) {
                return false;
            }
        } else if (aObj instanceof Object[]) {
            if (((Object[]) aObj).length == 0) {
                return false;
            }
            //check if first element is filled
            return isFilled(((Object[]) aObj)[0]);
        } else if (aObj instanceof char[]) {
            if (((char[]) aObj).length == 0) {
                return false;
            }
        }
        return true;
    }

    public static Date getTimeInUTC() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return cal.getTime();
    }

    public static Date getTimeStampInUTCDate(long aTimeStamp) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(new Date(aTimeStamp));

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return cal.getTime();
    }
}
