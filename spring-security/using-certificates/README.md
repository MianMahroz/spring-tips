# SECURING MICROSERVICES USING CERTIFICATE

### ORDER CLIENT SERVICE 

#### Generating Server KeyStore and a Certificate
- keytool -genKey -alias mahroz-server -keystore server-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA -deststoretype pkcs12

![img_2.png](img_2.png)

#### Generating Client KeyStore and a Certificate
- keytool -genKey -alias mahroz-client -keystore client-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA -deststoretype pkcs12

![img_3.png](img_3.png)

#### Extracting Server Certificates from above generated KeyStore
- keytool -exportcert -alias mahroz-server -keystore server-keystore.jks -file server-cert.cer -storepass password

![img_4.png](img_4.png)

#### Extracting Client Certificate from above generated KeyStore
- keytool -exportcert -alias mahroz-client -keystore client-keystore.jks -file client-cert.cer -storepass password

![img_5.png](img_5.png)


### GENERATING TRUST STORES FROM THE CERTIFICATES

#### Importing Client Certificate into Server Trust Store

- ![img_6.png](img_6.png)

#### Importing Server Certificate into Client Trust Store

- keytool -importcert -keystore client-truststore.jks -file server-cert.cer -alias mahroz-server -storepass password -trustcacerts -deststoretype pkcs12

![img_7.png](img_7.png)

![img_8.png](img_8.png)

