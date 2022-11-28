package Socket;
import java.util.*;
import java.net.*;
import java.io.*;

/*
 * create socket
 * connect to the server
 * 
 * read/write data
 * 
 * close socket()
 */

public class Client {
    public static void main(String args[]){
        System.out.println("[+]Client program");
        Scanner in = new Scanner(System.in);
        try{
            Socket s = new Socket("localhost",1225);
            DataOutputStream netOut = new DataOutputStream(s.getOutputStream());
            String msg="";
            while(!msg.equals("close")){
                msg = in.nextLine();
                netOut.writeUTF(msg);
            }
            s.close();
        }
        catch(UnknownHostException e){
            System.out.println("[+]Unable to find the server");
        }
        catch(IOException e){
            System.out.println("[+]Io Exception "+e);
        }

       
    }
}
