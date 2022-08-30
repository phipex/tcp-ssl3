package co.com.ies.pruebas;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.Security;

public class TcpServer {
    static {
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
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
    }
}
