package co.com.ies.pruebas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLCustomClient {
   private SSLSocket client;

   public SSLCustomClient(String address, int port) throws Exception {
      Security.setProperty("jdk.tls.disabledAlgorithms", "");
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new FileInputStream("/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/client/clientKey.jks"),
            "clientpass".toCharArray());

      KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      kmf.init(keyStore, "clientpass".toCharArray());

      KeyStore trustedStore = KeyStore.getInstance("JKS");
      trustedStore.load(new FileInputStream(
            "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/client/clientTrustedCerts.jks"), "clientpass"
            .toCharArray());

      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(trustedStore);
      //String protocol = "TLSv1.2";
      String protocol = "SSLv3";
      //SSLContext sc = SSLContext.getInstance("SSLv3");
      SSLContext sc = SSLContext.getInstance(protocol);
      TrustManager[] trustManagers = tmf.getTrustManagers();
      KeyManager[] keyManagers = kmf.getKeyManagers();
      sc.init(keyManagers, trustManagers, null);

      SSLSocketFactory ssf = sc.getSocketFactory();
      client = (SSLSocket) ssf.createSocket(address, port);

      client.setEnabledProtocols(
              new String[] { protocol });
      String[] enabledProtocols = client.getEnabledProtocols();

      for (String protocols: enabledProtocols){
         System.out.println("client protocols = " + protocols);
      }

      String[] enabledCipherSuites = client.getEnabledCipherSuites();
      for (String cipher: enabledCipherSuites){
         System.out.println("cipher = " + cipher);
      }


      client.startHandshake();
   }

   public void start() {
      Util.startClientWorking(client);
   }

}
