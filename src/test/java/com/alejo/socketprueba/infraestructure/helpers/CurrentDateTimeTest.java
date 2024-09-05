package com.alejo.socketprueba.infraestructure.helpers;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CurrentDateTimeTest {

    @Test
    void getCurrentDate() {
        String pattern = "yyyy_MM_dd";
        String stringDateExpected = getCurrentDateFormatter(pattern);

        String stringDateResult = CurrentDateTime.getCurrentDate();

        assertEquals(stringDateExpected,stringDateResult);
    }

    @Test
    void getCurrentDateTime() {
        String pattern = "yyyy/MM/dd HH:mm:ss";
        String stringDateExpected = getCurrentDateFormatter(pattern);

        String stringDateResult = CurrentDateTime.getCurrentDateTime();

        assertEquals(stringDateExpected,stringDateResult);
    }

    @Test
    void getCurrentTime() {
        String pattern = "HH:mm:ss";
        String stringDateExpected = getCurrentDateFormatter(pattern);

        String stringDateResult = CurrentDateTime.getCurrentTime();

        assertEquals(stringDateExpected,stringDateResult);
    }

    private String getCurrentDateFormatter(String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }
}