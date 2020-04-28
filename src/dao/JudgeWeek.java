package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ��ȡ��ǰ���������ڼ���
 */
public class JudgeWeek {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static int dateForWeek(String date) throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date));
        int  dayForWeek = 0 ;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1 ){
            dayForWeek = 7 ;
        }else {
            dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 ;
        }
        return  dayForWeek;
    }
}
