package client;

import java.io.*;
import java.net.*;

public class ClientSender extends Thread {
    private String message;
    private InetAddress ipAddress;
    private DatagramSocket clientSocket;


    public ClientSender(InetAddress ipAddress, DatagramSocket clientSocket){
        this.clientSocket = clientSocket;
        this.ipAddress = ipAddress;
    }

    public void run(){
        while(!clientSocket.isClosed()) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            try {
                message = inFromUser.readLine();
            } catch (IOException e) {
            }

            if(message.length() >= 5 && message.substring(0, 5).equals("@quit")){
                message = message + " Client has left us:((";
                DatagramPacket closePack = new DatagramPacket(message.getBytes(), message.getBytes().length, ipAddress, 8080);
                try {
                    clientSocket.send(closePack);
                } catch (IOException e) {
                }
                clientSocket.close();
            }

            else if(message.length() >= 4 && message.substring(0, 4).equals("@cat")){
                try {
                    Socket tcpClientSocket = new Socket(ipAddress, 8888);
                    BufferedReader in = new BufferedReader(new InputStreamReader(tcpClientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(tcpClientSocket.getOutputStream(), true);
                    out.println(message);
                    String res;
                    while((res = in.readLine()) != null) {
                        System.out.println(res);
                    }
                    tcpClientSocket.close();
                } catch (IOException e) {
                }
            }

            else {
                byte[] sendData;

                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 8080);
                try {
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                }
            }
        }
    }
}
