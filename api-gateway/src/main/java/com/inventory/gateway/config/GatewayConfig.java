package com.inventory.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${order.service.url}")
    private String orderServiceUrl;
    
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;
    
    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order-service", r -> r.path("/api/orders/**")
                        .uri(orderServiceUrl))
                .route("inventory-service", r -> r.path("/api/inventory/**")
                        .uri(inventoryServiceUrl))
                .route("payment-service", r -> r.path("/api/payments/**")
                        .uri(paymentServiceUrl))
                .build();
    }
}
