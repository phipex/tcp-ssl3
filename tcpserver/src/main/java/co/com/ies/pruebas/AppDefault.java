package co.com.ies.pruebas;

import java.io.IOException;
import java.security.Security;

/**
 * Example with SSL sockets using system properties.
 */
public class AppDefault {
   public static void main(String[] args) throws IOException {
      Security.setProperty("jdk.tls.disabledAlgorithms", "");
      System.setProperty("javax.net.ssl.keyStore", "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/server/serverKey.jks");
      System.setProperty("javax.net.ssl.keyStorePassword","servpass");
//      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/server/serverTrustedCerts.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "servpass");
      System.setProperty("javax.net.ssl.trustStore", "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/client/clientTrustedCerts.jks");
      System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
      
      new SSLDefaultServerSocket(5557).start();

//      System.setProperty("javax.net.ssl.keyStore", "src/main/certs/client/clientKey.jks");
//      System.setProperty("javax.net.ssl.keyStorePassword","clientpass");
//      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/client/clientTrustedCerts.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
      new SSLDefaultClientSocket("localhost",5557).start();
   }
}
