package teun.stout.webhook;

public interface SignatureVerifier {

    boolean verify(String signature, String body);
}
