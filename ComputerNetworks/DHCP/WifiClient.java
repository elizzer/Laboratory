
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

public class WifiClient {
    public static void main(String args[])throws Exception
    {
        String subNetIp="192.168.0.";
        int nextIp=0;
        int clientPort=1235,dhcpPort=1234;
        DatagramSocket ds = new DatagramSocket(clientPort);
        String ComCodes[]={"111","110"};

        String mac="aa:aa:aa:aa:aa:aa:aa:aa";
        System.out.println("[+]Enter the mac of the device:");
        Scanner sin = new Scanner(System.in);
        mac = sin.next();
        InetAddress toIp= InetAddress.getByName("127.0.0.1");
        DatagramPacket dp = new DatagramPacket(ComCodes[0].getBytes(),ComCodes[0].length(),toIp,dhcpPort);
        ds.send(dp);
        byte buf[]= new byte[1024];
        DatagramPacket dpr = new DatagramPacket(buf, 1024);
        ds.receive(dpr);
        String str = new String(dpr.getData(),0,dpr.getLength());
        if(str.equals(ComCodes[1])){
            System.out.println("[+]Ip config req accepted...");
            System.out.println("[+]DHCP is asking for the MAC");
            dp=new DatagramPacket(mac.getBytes(),mac.length(),toIp,dhcpPort);
            ds.send(dp);
            ds.receive(dpr);
            str=new String(dpr.getData(),0,dpr.getLength());
            System.out.println("[+]Assigned Ip "+str);
        }


        ds.close();
    }    
}
