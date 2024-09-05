package com.alejo.socketprueba.infraestructure.helpers;

public class LogManager extends SocketLogger{

    private static volatile LogManager logManager;
    private String logFolder = PropertyManager.getInstance().getProperty("LogFolder");

    private LogManager(){

    }

    public static LogManager getInstance(){

        if ( logManager == null ){
            synchronized (LogManager.class){
                if (logManager == null){
                    logManager = new LogManager();
                }
            }
        }
        return logManager;
    }

    public void logInfo(int threadId, String message){
        writeToLogFile("LogInfo",logFolder,threadId + " Thread -> "
                + CurrentDateTime.getCurrentTime() + " "+ message);
    }
    public void logInfo(String message){
        writeToLogFile("LogInfo",logFolder,CurrentDateTime.getCurrentTime() + " "+ message);
    }

    public void logError(String message, Exception ex){
        writeToLogFile("LogError",logFolder,CurrentDateTime.getCurrentTime() + " "
                + message + ". The following exception has occurred  " + ex);
    }

    public void logError(int threadId, String message, Exception ex){
        writeToLogFile("LogError",logFolder,threadId + " Thread -> " + CurrentDateTime.getCurrentTime() + " "
                + message + ". The following exception has occurred  " + ex);
    }

}
