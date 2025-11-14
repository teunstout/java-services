package teun.stout.webhook;

import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

@Service
public class SignatureVerifierImpl implements SignatureVerifier {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public SignatureVerifierImpl(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public boolean verify(String signatureHeader, String payload) {
        if (signatureHeader == null || payload == null) return false;

        try {
            final String prefix = "sha256=";
            String b64 = signatureHeader.startsWith(prefix)
                    ? signatureHeader.substring(prefix.length())
                    : signatureHeader;
            byte[] sigBytes = Base64.getDecoder().decode(b64);

            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(payload.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return sig.verify(sigBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
