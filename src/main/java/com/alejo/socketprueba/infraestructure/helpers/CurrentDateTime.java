package com.alejo.socketprueba.infraestructure.helpers;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTime {

    public static String getCurrentDate() {
        String pattern = "yyyy_MM_dd";
        return getCurrentDateFormatter(pattern);
    }

    public static String getCurrentDateTime(){
        String pattern = "yyyy/MM/dd HH:mm:ss";
        return getCurrentDateFormatter(pattern);
    }

    public static String getCurrentTime(){
        String pattern = "HH:mm:ss.SSSSSS";
        return getCurrentDateFormatter(pattern);
    }

    private static String getCurrentDateFormatter(String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

}
