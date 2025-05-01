package com.inventory.payment.client;

import com.inventory.payment.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    public OrderClient(RestTemplate restTemplate, 
                      @Value("${order.service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }

    public OrderResponse getOrder(String orderId) {
        return restTemplate.getForObject(
            orderServiceUrl + "/api/orders/" + orderId, 
            OrderResponse.class
        );
    }

    public void updateOrderPaymentStatus(String orderId, String status) {
        restTemplate.postForObject(
            orderServiceUrl + "/api/orders/" + orderId + "/payment-status",
            status,
            Void.class
        );
    }
}
