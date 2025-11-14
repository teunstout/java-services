package teun.stout.webhook;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import teun.stout.common.exception.UnrecognizedSignatureException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping(value = "/webhook", version = "1.0.0")
public class WebhookController {

    public static final String SIGNATURE_PARAM = "X-Signature";
    public static final String IDEMPOTENCY_PARAM = "X-Idempotency-Key";

    private final WebhookService webhookService;
    private final SignatureVerifier signatureVerifier;

    public WebhookController(WebhookService webhookService, SignatureVerifier signatureVerifier) {
        this.webhookService = webhookService;
        this.signatureVerifier = signatureVerifier;
    }

    @PostMapping(value = "/provider", version = "1.0.0")
    public ResponseEntity<String> handleProviderWebhook(
            @RequestHeader(name = SIGNATURE_PARAM, required = false) String signature,
            @RequestHeader(name = IDEMPOTENCY_PARAM, required = false) String idempotencyKey,
            HttpServletRequest request) throws IOException {

        String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        if (!signatureVerifier.verify(signature, body)) {
            throw new UnrecognizedSignatureException("Signature verification failed");
        }

        // Enqueue and return immediately
        webhookService.enqueueEvent(body, idempotencyKey);
        return ResponseEntity.ok("accepted");
    }


}
