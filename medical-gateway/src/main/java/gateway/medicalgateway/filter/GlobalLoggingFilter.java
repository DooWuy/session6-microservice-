package gateway.medicalgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalLoggingFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        String remoteAddress = getRemoteAddress(exchange);
        String httpMethod = exchange.getRequest().getMethod().toString();
        String path = exchange.getRequest().getURI().getPath();

        // Log request

        log.info("🔹 Remote Address (IP): {}", remoteAddress);
        log.info("🔹 HTTP Method: {}", httpMethod);
        log.info("🔹 Path: {}", path);

        return chain.filter(exchange).doFinally(signalType -> {
            // Log response details after processing
            int statusCode = exchange.getResponse().getStatusCode() != null
                    ? exchange.getResponse().getStatusCode().value()
                    : 0;


            log.info("🔹 Remote Address (IP): {}", remoteAddress);
            log.info("🔹 HTTP Method: {}", httpMethod);
            log.info("🔹 Path: {}", path);
            log.info("🔹 Response Status: {}", statusCode);

        });
    }

    private String getRemoteAddress(ServerWebExchange exchange) {

        String xForwardedFor = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }


        if (exchange.getRequest().getRemoteAddress() != null) {
            return exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        }

        return "UNKNOWN";
    }


}
