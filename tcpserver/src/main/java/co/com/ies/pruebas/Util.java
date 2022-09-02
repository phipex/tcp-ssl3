package co.com.ies.pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Util {
   public static void startClientWorking(final Socket client){
      System.out.println("client start");
      new Thread() {
         public void run() {
            try {
               PrintWriter output = new PrintWriter(client.getOutputStream());
               String msg = "&0004A37D0205&1ACF&ConfInit&497B7F9A2004FAF396846F7595D46330&HTTP/1.0";
               output.println(msg);
               output.flush();
               System.out.println("client sent: " + msg);
               BufferedReader input = new BufferedReader(new InputStreamReader(
                     client.getInputStream()));
               String received = input.readLine();
               System.out.println("client: Received : " + received);
               client.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }.start();
   }
   
   public static void startServerWorking(final ServerSocket serverSocket) {
      System.out.println("server start");
      new Thread() {
         public void run() {
            try {
               Socket aClient = serverSocket.accept();
               System.out.println("server:client accepted");
               aClient.setSoLinger(true, 1000);
               BufferedReader input = new BufferedReader(new InputStreamReader(
                     aClient.getInputStream()));
               String recibido = input.readLine();
               System.out.println("server Recibido: " + recibido);
               PrintWriter output = new PrintWriter(aClient.getOutputStream());
               output.println("ConfInit=RTC=20220901101400&APAGAR&METER=6F&HABILITAR&OKHP&");
               output.flush();
               aClient.close();
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      }.start();
   }

}
