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

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class MdcLoggingFilter extends OncePerRequestFilter {

    static final String MDC_KEY_ENDPOINT = "request.url";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // Add the request URI to MDC
            MDC.put(MDC_KEY_ENDPOINT, request.getRequestURI());
            filterChain.doFilter(request, response);
        } finally {
            // Clear MDC after request to avoid memory leaks
            MDC.remove(MDC_KEY_ENDPOINT);
        }
    }
}
