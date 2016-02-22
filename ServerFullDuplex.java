/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfullduplex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFullDuplex {

  
    public static void main(String[] args) throws Exception
    {
        ServerSocket sskt = new ServerSocket(2000);
        System.out.println("Waiting fot client to connect .......");
        Socket clientSocket  = sskt.accept();
        System.out.println("Connected client  "+clientSocket);
        ServerRead servRead  = new ServerRead(clientSocket);
        ServerWrite servWrite = new ServerWrite(clientSocket);
    }
    
}


class ServerRead implements Runnable
{

    Socket skt;
    Thread t;
    DataInputStream di;
    InputStream in;
    String str;
    
    public ServerRead(Socket sk)
    {
        skt = sk;
        t= new Thread(this);
        t.start();
    }
    
   
    
    
    @Override
    public void run() {
                
            try
            {
                    while(true)
                    {
                        //skt.getInputStream() for readin
                        //skt.getOutputStram for writing
                    in= skt.getInputStream();   //reading the client socket 
                    di = new DataInputStream(in);//getting the input from client
                    
                    
                    str ="Client -"+di.readLine();
                    System.out.println(str+" "+skt);
                    
                    if(str.equals("x")) break;
                    }
            }catch (Exception e){}
    }

    
}

class ServerWrite implements Runnable
{
    
    Socket sok;
    String str;
    DataInputStream di;
    InputStream in;
    OutputStream os;
    Thread t;
    
    public ServerWrite(Socket sk)
    {
    
        sok= sk;
        t= new Thread(this);
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
                    os = sok.getOutputStream();
                    PrintStream ps  = new PrintStream(os);
                    ps.println(str+" "+sok);
                    if(str.equals("x")) break;
            }
        }catch(Exception e){}
    
    }
    

}