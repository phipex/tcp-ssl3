package co.com.ies.pruebas;


/**
 * Example with SSL sockets using system properties.
 */
public class AppCustom {
   public static void main(String[] args) throws Exception {
      new SSLCustomServer(5557).start();

      new SSLCustomClient("localhost",5557).start();

      //new SSLCustomClientSmol("172.16.1.244", 1099).start();
   }
}
