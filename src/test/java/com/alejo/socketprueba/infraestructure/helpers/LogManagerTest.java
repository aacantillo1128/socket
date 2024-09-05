package com.alejo.socketprueba.infraestructure.helpers;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.SQLDataException;

import static org.junit.jupiter.api.Assertions.*;

class LogManagerTest {

    private LogManager logManager = LogManager.getInstance();
    private String logFolder = PropertyManager.getInstance().getProperty("LogFolder");

    @Test
    void getInstance() {
        LogManager logManager1 = LogManager.getInstance();
        LogManager logManager2 = LogManager.getInstance();

        assertEquals(logManager1,logManager2);

    }

    @Test
    void logInfo() {
        logManager.logInfo(1, "Test log");

    }

    @Test
    void logError() {
        logManager.logError(1,"Test error log", new SQLDataException());
    }
}