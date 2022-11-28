package Socket;
import java.util.*;

import java.io.*;
import java.net.*;
/*
 create socket
 bind socket
 listen in the port
 accept connection

 read/write data

 when the client closes it gives a End of file to the server

 close the socket
 */

public class Server {
    public static void main(String args[]){
        System.out.println("[+]Server ");
        try{
            ServerSocket server = new ServerSocket(5555);
            Socket s = server.accept();
            DataInputStream netIn = new DataInputStream(s.getInputStream());
            String msg="";
            while(true){
                msg = (String) netIn.readUTF();
                System.out.println("[+]Message from client "+msg);
            }
        }
        catch(IOException e){
            System.out.println("[+]IoException "+e);
            
        }
    }
}
