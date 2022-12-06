import java.util.*;

public class HammingCode{
    public static void main(String args[]){
        Random ran = new Random();
        int data[]= new int[16];
        for ( int i=0;i<16;i++){
            data[i]=ran.nextInt(2);
        }

        System.out.println("The created message");
        
        print(data);
       

        
        String parity = Integer.toBinaryString(checkParity(data));
        for(int i=0;i<parity.length();i++){
            if(parity.charAt(i)=='1'){
                int temp=(int)Math.pow(2, parity.length()-i-1);
                System.out.println("[+]Check "+i+" "+temp);
                data[temp]=data[temp]==1?0:1;
            }
        }

        print(data);

        checkParity(data);
        System.out.println("Introducing noise... ");

        int temp=ran.nextInt(16);
        data[temp]= data[temp]==1?0:1;
        System.out.println("Error at "+temp);

        print(data);
        checkParity(data);

    }

    static void print(int a[]){
        for(int i=0;i<16;i++){
            if(i%4==0){
                System.out.println("");
            }
            System.out.print(a[i]+" ");
        }
        System.out.println("");

    }

    static int checkParity(int a[]){
        int p=0000;
        for(int i=0;i<16;i++){
            if(a[i]==1){
                p=p^i;
            }
        }
        System.out.println("Parity value "+p);
        System.out.println("Parity value "+Integer.toBinaryString(p));
        return p;
    }
    
}