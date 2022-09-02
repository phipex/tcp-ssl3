package co.com.ies.pruebas;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;

public class TcpServer {
    static {
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        System.setProperty("javax.net.ssl.keyStore", "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/server/serverKey.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","servpass");
//      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/server/serverTrustedCerts.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "servpass");
        System.setProperty("javax.net.ssl.trustStore", "/home/felipe/Documentos/proyectos/github.com/phipex/tcp-ssl3/tcpserver/src/main/resources/certs/client/clientTrustedCerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
    }

    public static void main(String[] args) throws IOException {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket soc = (SSLSocket) factory.createSocket();

        // Returns the names of the protocol versions which are
        // currently enabled for use on this connection.
        String[] protocols = soc.getEnabledProtocols();

        System.out.println("Enabled protocols:");
        for (String s : protocols) {
            System.out.println(s);
        }
        System.out.println("Enabled providers:");
        Provider[] providers = Security.getProviders();
        for (Provider provider: providers){
            System.out.println(provider);
        }
    }
}
