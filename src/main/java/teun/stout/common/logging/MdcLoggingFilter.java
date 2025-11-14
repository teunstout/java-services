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
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdcLoggingFilter extends OncePerRequestFilter {

    private static final String MDC_KEY_REQUEST_KEY = "X-Request-ID";

    private static final String MDC_KEY_REQUEST_ID = "request.id";
    private static final String MDC_KEY_ENDPOINT = "request.uri";
    private static final String MDC_KEY_METHOD = "request.method";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Generate or get a unique request ID
        String requestId = request.getHeader(MDC_KEY_REQUEST_KEY);
        if (requestId == null || requestId.isEmpty()) {
            requestId = generateRequestId();
        }

        try {
            MDC.put(MDC_KEY_REQUEST_ID, requestId);
            MDC.put(MDC_KEY_ENDPOINT, request.getRequestURI());
            MDC.put(MDC_KEY_METHOD, request.getMethod());

            request.setAttribute(MDC_KEY_REQUEST_KEY, requestId);
            filterChain.doFilter(request, response);

        } finally {
            // Clear MDC to avoid memory leaks
            MDC.remove(MDC_KEY_REQUEST_ID);
            MDC.remove(MDC_KEY_ENDPOINT);
            MDC.remove(MDC_KEY_METHOD);
        }
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
