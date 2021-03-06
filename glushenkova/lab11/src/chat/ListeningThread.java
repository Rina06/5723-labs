package chat;
import java.io.*;
import java.net.*;

public class ClientThread extends Thread 
{
	DatagramSocket socket;
    DatagramPacket packet;
    String name;
    DataOutputStream out;
    
    public ClientThread(DatagramSocket s)
    {
    	this.socket = s;
    	this.name = "Server";
    }

    @Override
    public void run() 
    {
        while (true)
        {
        	try
        	{
                String filename;
        		byte[] receiveMessage = new byte[1024];
        		packet = new DatagramPacket(receiveMessage, receiveMessage.length);
        		socket.receive(packet);
        		String message = new String(packet.getData());
                if (message.startsWith("@send")) 
                {
                    filename = message.substring(6);
                    filename.trim();
                    File myFile = new File(filename);
                    if (myFile.isFile() == false) throw new Exception("file txt not found");
                    FileReader reader = new FileReader(filename);
                    byte[] file = new byte[(int)myFile.length()];
                    int c = 0;
                    while ((c = reader.read()) != -1)
                    {
                        out.write(file, 0, c);
                    }                    
                }
        		if (message.startsWith("@name")) name = message.substring(6);
        		else if (message.startsWith("@quit")) 
                {
                    System.out.println(name.trim() + " disconnected");
                    break;
                }
        		else if (packet != null) System.out.println("Message from " + name.trim() + ": " + message.trim());
        	}
        	catch(Exception e)
			{
				System.out.println("Error : " + e);
			}
        }
    }
}