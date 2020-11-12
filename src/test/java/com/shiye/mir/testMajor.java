package com.shiye.mir;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class testMajor {
    public static String getYesterdayByCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    @Test
    public void testYesterday(){
        System.out.println("昨天日期"+getYesterdayByCalendar());
    }
}
