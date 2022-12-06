
import java.net.*;


public class DHCPServer {
    public static String MACTable[]= new String[100];
    public static void main(String args[])throws Exception
    {
        String subNetIp="192.168.0.";
        int nextIp=0;
        InetAddress toIp=InetAddress.getByName("127.0.0.1");
        String ComCodes[]={"111","110"};
        DatagramSocket ds = new DatagramSocket(1234);
        byte buf[]= new byte[1024];
        DatagramPacket dpr = new DatagramPacket(buf, 1024);
        while(true){

            ds.receive(dpr);
            int clientPort = dpr.getPort();
            String str = new String(dpr.getData(),0,dpr.getLength());
            if(str.equalsIgnoreCase(ComCodes[0])){
                System.out.println("[+]New client asking for ip...");
                DatagramPacket dps = new DatagramPacket(ComCodes[1].getBytes(),ComCodes[1].length(),toIp,clientPort);
                ds.send(dps);
                ds.receive(dpr);
                str= new String(dpr.getData(),0,dpr.getLength());
                System.out.println("[+]Mac of the client is "+str);
                int ip= findIp(str);
                setIp(ip, str);
                String finalIp=subNetIp+ip;
                dps = new DatagramPacket(finalIp.getBytes(),finalIp.length(),toIp,clientPort);
                ds.send(dps);
            }
        }
            // System.out.println(str);
        // ds.close();
    }    

    public static int findIp(String mac){
        int ip=-1;
        for(int i=0;i<100;i++){
            if(MACTable[i]==null){
                // System.out.println("[+]"+i);
                if(ip==-1){
                    ip=i;
                }
            }
            else{
                // System.out.println("[+]TT"+MACTable[i]);
                if(MACTable[i].equals(mac)){
                    return i;
                }
            }
        }

        return ip;
        
    }
    public static void setIp(int ip,String mac){
        MACTable[ip]=mac;
        
    }
}
