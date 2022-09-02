# Creacion de certificados (cliente pruebas)

1.  se crea el almacen para el servidor
````bash
keytool -genkey -keyalg RSA -alias serverKey -keystore serverKey.jks -storepass servpass
````
2. A partir del almacen se crea un certificado publico
````bash
keytool -export -keystore serverKey.jks -alias serverKey -file ServerPublicKey.cer
````
3. se ingresa en los certificados de confianza del cliente
````bash
keytool -import -v -trustcacerts -alias serverKey -file ServerPublicKey.cer -keystore clientTrustedCerts.jks -keypass clientpass -storepass clientpass
````
4. Se crea un almacen para el cliente
````bash
keytool -genkey -keyalg RSA -alias clientKey -keystore clientKey.jks -storepass clientpass
````
5. Se crea un certificado publico del cliente
````bash
keytool -export -keystore clientKey.jks -alias clientKey -file ClientPublicKey.cer
````
6. se ingresa el certificado en los del confianza del servidor
````bash
keytool -import -v -trustcacerts -alias clienKey -file ClientPublicKey.cer -keystore serverTrustedCerts.jks -keypass serverTrustpass -storepass serverTrustpass
````

