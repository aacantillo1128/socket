package com.alejo.socketprueba.infraestructure.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyManager {

    private static volatile PropertyManager propertyManager;
    private Properties properties;
    private static final Logger LOGGER = Logger.getLogger(PropertyManager.class.getName());

    private PropertyManager(){
        properties = new Properties();
    }


    public static PropertyManager getInstance(){

        if ( propertyManager==null ){
            synchronized (PropertyManager.class){
                if (propertyManager == null){
                    propertyManager = new PropertyManager();
                }
            }
        }
        return propertyManager;
    }

    public String getProperty(String property){

        String responseProperty = "";
        try{

            String path = "properties" + File.separator + "SocketPrueba.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            responseProperty = properties.getProperty(property) == null ? "" : properties.getProperty(property);
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE,"PropertyManager/getProperty() -> Error getting property", ex);
        }
        return responseProperty;
    }



}
