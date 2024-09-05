package com.alejo.socketprueba.infraestructure.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class SocketLoggerTest {


    private SocketLogger socketLogger;
    private String nameFile;
    private String message;
    private static final Logger LOGGER = Logger.getLogger(SocketLogger.class.getName());

    @BeforeEach
    void setUp() {
        socketLogger = new SocketLogger();
        nameFile = "Log";
        message = "Prueba log";
    }

    @Test()
    void writeToLogFileNoShouldCreateTheLogFileWhenPathFileIsNull() {

        String pathFile = null;

        File logFile = new File(pathFile + File.separator+ nameFile +"_"+ CurrentDateTime.getCurrentDate() + ".txt");

        socketLogger.writeToLogFile(nameFile,pathFile,message);

        assertFalse(logFile.exists());

    }

    @Test()
    void writeToLogFileShouldCreateTheLogFileWhenPathFileIsValid() {

        String pathFile = "logs";

        File logFile = new File(pathFile +File.separator+ nameFile +"_"+ CurrentDateTime.getCurrentDate() + ".txt");

        socketLogger.writeToLogFile(nameFile,pathFile,message);

        assertTrue(logFile.exists());
        logFile.delete();

    }

    @Test()
    void writeToLogFileNoShouldWriteInLogFileWhenMessageIsNull() {

        String pathFile = "logs";
        nameFile = "WriteLog";
        message = null;

        File logFile = new File(pathFile +File.separator+ nameFile +"_"+ CurrentDateTime.getCurrentDate() + ".txt");

        socketLogger.writeToLogFile(nameFile,pathFile,message);

        assertTrue(logFile.exists());
        logFile.delete();

    }


}