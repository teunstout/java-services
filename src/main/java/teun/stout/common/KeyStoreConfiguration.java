package teun.stout.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class KeyStoreConfiguration {

    @Value("${app.keystore.path}")
    private Resource keystoreResource;

    @Value("${app.keystore.password}")
    private String keystorePassword;

    @Value("${app.keystore.alias}")
    private String alias;

    @Bean
    public KeyStore keyStore() throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (InputStream is = keystoreResource.getInputStream()) {
            ks.load(is, keystorePassword.toCharArray());
        }
        return ks;
    }

    @Bean
    public PrivateKey privateKey(KeyStore keyStore) throws Exception {
        return (PrivateKey) keyStore.getKey(alias, keystorePassword.toCharArray());
    }

    @Bean
    public PublicKey publicKey(KeyStore keyStore) throws Exception {
        return keyStore.getCertificate(alias).getPublicKey();
    }
}
