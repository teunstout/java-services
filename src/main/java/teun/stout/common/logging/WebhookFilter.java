package teun.stout.common.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.UUID;

import static teun.stout.webhook.WebhookController.IDEMPOTENCY_PARAM;
import static teun.stout.webhook.WebhookController.SIGNATURE_PARAM;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 1)
public class WebhookFilter extends OncePerRequestFilter {
    public static String MDC_KEY_X_SIGNATURE = "request.X-Signature";
    public static String MDC_KEY_X_IDEMPOTENCY_KEY = "request.X-Idempotency-Key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().startsWith("/webhook")) {
            filterChain.doFilter(request, response);
        }

        try {
            putMdcIfPresent(MDC_KEY_X_IDEMPOTENCY_KEY, SIGNATURE_PARAM);
            putMdcIfPresent(MDC_KEY_X_SIGNATURE, IDEMPOTENCY_PARAM);
            filterChain.doFilter(request, response);
        } finally {
            // Clear MDC to avoid memory leaks
            MDC.remove(MDC_KEY_X_IDEMPOTENCY_KEY);
            MDC.remove(MDC_KEY_X_SIGNATURE);
        }
    }

    private void putMdcIfPresent(String mdcKey, String headerValue) {
        if (headerValue != null) MDC.put(mdcKey, headerValue);
    }
}
