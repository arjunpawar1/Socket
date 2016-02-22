/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientfullduplex;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author arjun
 */
public class ClientFullDuplex 
{

    
    public static void main(String[] args) throws IOException 
    {
       
       Socket s = new Socket("localhost",2000);
       ClientRead a  = new ClientRead(s);
       ClientWrite b = new ClientWrite(s);
    }
    
}


class ClientRead implements Runnable
{

    Thread t;
    String str;
    Socket serverskt;
    DataInputStream di;
    InputStream is;
    
    
    
    ClientRead(Socket clSkt)
    {
       serverskt = clSkt; 
       
        
    t = new Thread(this);
    t.start();
    }
    
    @Override
    public void run() 
    
    {
        
        try
        {
            while(true)
            {
            
                is = serverskt.getInputStream();
                di = new DataInputStream(is);
                
                str =" Server -  "+di.readLine();
                System.out.println(str);
                if(str.equals("x"))break;
                
            }
        }catch(Exception e){}
    }

}


class ClientWrite implements Runnable
{

    Thread t;
    DataInputStream di;
    InputStream in;
    OutputStream os;
    Socket serverSocket;
    String str;
    public ClientWrite(Socket st)
    {
    
        serverSocket = st;
        t  = new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run() 
    {
        
        try
        {
        
            while(true)
            {
            
                di = new DataInputStream(System.in);
                str = di.readLine();
                os = serverSocket.getOutputStream();
                PrintStream ps = new PrintStream(os);
                
                ps.println(str);
                if(str.equals("x")) break;
            }
            
            
        }catch(Exception e){}
    }
}