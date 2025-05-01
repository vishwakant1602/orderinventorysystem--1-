package com.inventory.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")
    private String jwtSecret;

    // Updated to include more open endpoints since auth service is removed
    private final List<String> openApiEndpoints = List.of(
            "/api/orders/**",
            "/api/inventory/**",
            "/api/payments/**",
            "/eureka",
            "/actuator"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // Skip authentication for open endpoints
        if (isOpenEndpoint(request.getPath().toString())) {
            return chain.filter(exchange);
        }

        // For demonstration purposes only - since auth service is removed
        // In a real scenario, you would implement alternative authentication
        // or completely secure your endpoints differently
        
        // Add mock user info to headers for downstream services
        ServerHttpRequest modifiedRequest = request.mutate()
                .header("X-User-Id", "demo-user")
                .header("X-User-Role", "USER")
                .build();
        
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    private boolean isOpenEndpoint(String path) {
        return openApiEndpoints.stream().anyMatch(endpoint -> 
            path.startsWith(endpoint.replace("/**", "")));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1; // High priority
    }
}
