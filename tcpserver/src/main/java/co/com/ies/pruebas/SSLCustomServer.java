package co.com.ies.pruebas;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLCustomServer {
   private SSLServerSocket serverSocket;

   public SSLCustomServer(int port) throws Exception {
      Security.setProperty("jdk.tls.disabledAlgorithms", "");
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new FileInputStream("/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/server/serverKey.jks"),
            "servpass".toCharArray());

      KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      kmf.init(keyStore, "servpass".toCharArray());

      KeyStore trustedStore = KeyStore.getInstance("JKS");
      trustedStore.load(new FileInputStream(
            "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/server/serverKey.jks")
              , "servpass".toCharArray());

      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(trustedStore);


      //String protocol = "TLSv1.1";
      String protocol = "SSLv3";
      SSLContext sc = SSLContext.getInstance(protocol);
      //SSLContext sc = SSLContext.getInstance("SSLv3");
      //SSLContext sc = SSLContext.getInstance("TLSv1.1");
      TrustManager[] trustManagers = tmf.getTrustManagers();
      KeyManager[] keyManagers = kmf.getKeyManagers();
      sc.init(keyManagers, trustManagers, null);

      SSLServerSocketFactory ssf = sc.getServerSocketFactory();
      serverSocket = (SSLServerSocket) ssf.createServerSocket(port);
      serverSocket.setEnabledProtocols(
              new String[] { protocol });
      String[] enabledProtocols = serverSocket.getEnabledProtocols();
      for (String protocols: enabledProtocols){
         System.out.println("server protocols = " + protocols);
      }
   }

   public void start() {
      Util.startServerWorking(serverSocket);
   }

}
