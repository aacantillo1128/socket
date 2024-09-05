package com.alejo.socketprueba.application;

import com.alejo.socketprueba.infraestructure.entrypoints.tcp.ProcessClient;
import com.alejo.socketprueba.infraestructure.helpers.LogManager;
import com.alejo.socketprueba.infraestructure.helpers.PropertyManager;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketPruebaApplication{


    private static  LogManager log = LogManager.getInstance();
    private static String SOCKET_NAME = PropertyManager.getInstance().getProperty("Name");
    private static int SOCKET_PORT = Integer.parseInt(PropertyManager.getInstance().getProperty("Port"));
    private static int SOCKET_TIMEOUT = Integer.parseInt(PropertyManager.getInstance().getProperty("Timeout"));

    public static void main(String[] args) {
        log.logInfo("Start/main() *** " + SOCKET_NAME + " has started ***");
        new SocketPruebaApplication().turnOnService();
    }

    public void turnOnService(){
        try(ServerSocket serverSocket = new ServerSocket(SOCKET_PORT)){
            Socket newClient;
            do{
                newClient = serverSocket.accept();
                newClient.setSoTimeout(SOCKET_TIMEOUT);
                runThread(newClient);
            }while (!serverSocket.isClosed());
        }catch (Exception ex){
            log.logError("Start/turnOnService() -> failed to initialize the socket ",ex);
        }
    }

    public void runThread(Socket newClient){
        Runnable process = new ProcessClient(newClient);
        Thread client = new Thread(process,"Thread-Socket");
        client.start();
    }

}
