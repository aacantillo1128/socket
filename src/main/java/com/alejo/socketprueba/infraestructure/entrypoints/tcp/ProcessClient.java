package com.alejo.socketprueba.infraestructure.entrypoints.tcp;

import com.alejo.socketprueba.infraestructure.helpers.LogManager;
import com.alejo.socketprueba.infraestructure.helpers.PropertyManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProcessClient implements Runnable{


    private LogManager log = LogManager.getInstance();
    private PropertyManager propertyManager = PropertyManager.getInstance();
    private static int lastThreadId = 0;
    private transient int threadId = 0;
    private Socket newClient;
    private transient long time;


    public ProcessClient(Socket newClient) {
        this.newClient = newClient;
        assignThreadId();
    }

    @Override
    public void run() {
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(newClient.getInputStream(),"UTF-8"))){
            log.logInfo(threadId,"ProcessClient/run() started processing the request");
            String requetFrame = getFrameFromClient(bufferedReader);
            String responseFrame = "Recibido";
            replyToClient(responseFrame);
        }catch (Exception ex){
            log.logError(threadId,"ProcessClient/run() failed to run ",ex);
        }

    }

    private void replyToClient(String reply){
        try(PrintWriter out = new PrintWriter(newClient.getOutputStream(),true)){
            out.println(reply);
            log.logInfo(threadId,"ProcessClient/replyToClient() replied: " + reply);
        }catch (Exception ex){
            log.logError(threadId,"ProcessClient/replyToClient() failed to reply to client ",ex);
        }
    }


    private String getFrameFromClient(BufferedReader bufferedReader){
        int character;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            do{
               character = bufferedReader.read();
               stringBuilder.append((char) character);
            }while (ValidateEndOfFrame(character));

            String clientIP = newClient.getInetAddress().getHostAddress();

            log.logInfo(threadId,"ProcessClient/getFrameFromClient() received: " + stringBuilder
                    + " from: " + clientIP);

        }catch (Exception ex){
            log.logError(threadId,"ProcessClient/getFrameFromClient() failed to receive a frame ",ex);
        }
        return stringBuilder.toString();
    }


    private boolean ValidateEndOfFrame(int character) {
        //return character != 126 && character != 13 && character != -1; 126 -> ~ 13 -> retorno de carro
        return character != 13 && character != -1;
    }


    private synchronized void assignThreadId(){
        if(lastThreadId == 999999){
            lastThreadId = 0;
        }
        lastThreadId++;
        threadId = lastThreadId;
    }


}
