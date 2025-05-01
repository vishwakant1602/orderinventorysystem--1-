package com.inventory.order.client;

import com.inventory.order.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;
    private final String inventoryServiceUrl;

    public InventoryClient(RestTemplate restTemplate, 
                          @Value("${inventory.service.url}") String inventoryServiceUrl) {
        this.restTemplate = restTemplate;
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    public InventoryResponse getInventory(String productId) {
        return restTemplate.getForObject(
            inventoryServiceUrl + "/api/inventory/" + productId, 
            InventoryResponse.class
        );
    }

    public InventoryResponse reduceInventory(String productId, int quantity) {
        return restTemplate.postForObject(
            inventoryServiceUrl + "/api/inventory/" + productId + "/reduce?quantity=" + quantity,
            null,
            InventoryResponse.class
        );
    }
}
