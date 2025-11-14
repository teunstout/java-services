# Sour service

## Generate a certificate for webhooks

```bash
keytool -genkeypair \
  -alias mykey \
  -keyalg RSA \
  -keysize 2048 \
  -keystore keystore.p12 \
  -storetype PKCS12 \
  -storepass ChangeMe123! \
  -keypass ChangeMe123! \
  -validity 3650 \
  -dname "CN=teun-stout.com, OU=Dev, O=teun-stout, L=Tokyo, ST=Tokyo, C=JP"
```

```bash
keytool -exportcert -alias senderKeyPair -storetype JKS \
  -keystore sender_keystore.jks -file \
  sender_certificate.cer -rfc -storepass changeit
```