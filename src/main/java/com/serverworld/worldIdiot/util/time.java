package com.serverworld.worldIdiot.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class time {
    //ex 10s 1y 5h
    public static long converttime(String times){
        String dateform = times.substring(times.length() - 1);
        String timent = times.substring(0,times.length() - 1);
        //check dateform import
        Date date = new Date();
        switch (dateform){
            case "s":return date.getTime()+ TimeUnit.SECONDS.toMillis(Integer.valueOf(timent));//sec
            case "m":return date.getTime()+ TimeUnit.MINUTES.toMillis(Integer.valueOf(timent));//min
            case "h":return date.getTime()+ TimeUnit.HOURS.toMillis(Integer.valueOf(timent));//hour
            case "d":return date.getTime()+ TimeUnit.DAYS.toMillis(Integer.valueOf(timent));//day
            case "o":return date.getTime()+ TimeUnit.DAYS.toMillis(Integer.valueOf(timent)*30);//mount
            case "y":return date.getTime()+ TimeUnit.DAYS.toMillis(Integer.valueOf(timent)*365);//year
            default: return 0l;
        }

    }

}
