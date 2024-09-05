package com.alejo.socketprueba.infraestructure.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketLogger {

    private static final Logger LOGGER = Logger.getLogger(SocketLogger.class.getName());

    private void checkPathFile(String pathFile) {
            File logFolder = new File(pathFile);

            if (!logFolder.exists()){
                logFolder.mkdir();
            }
    }

    public void writeToLogFile(String nameFile, String pathFile, String message){
        synchronized (this){
            try{
                checkPathFile(pathFile);
                String fullNameFile = pathFile + File.separator + getLogFileName(nameFile);
                try(FileWriter logFile = new FileWriter(fullNameFile,true);
                    BufferedWriter writer = new BufferedWriter(logFile)){

                    writer.write(message);
                    writer.newLine();

                }
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE,"SocketLogger/writeToLogFile() -> Error writing to log file", ex);
            }
        }
    }

    private String getLogFileName(String name){
        return name +"_"+ CurrentDateTime.getCurrentDate() + ".txt";
    }


}
