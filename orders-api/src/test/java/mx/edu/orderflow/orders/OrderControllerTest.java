package mx.edu.orderflow.orders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderControllerTest {

    private OrderService service;
    private OrderController controller;

    @BeforeEach
    void setUp() {
        service = mock(OrderService.class);
        controller = new OrderController(service);
    }

    // --- list() ---

    @Test
    void listReturnsOrders() {
        var order = new Order(1001L, "c-1", new BigDecimal("100"), OrderStatus.CREATED);
        when(service.list()).thenReturn(List.of(order));

        var result = controller.list();

        assertEquals(1, result.size());
        assertEquals("c-1", result.get(0).customerId());
    }

    @Test
    void listReturnsEmptyWhenNoOrders() {
        when(service.list()).thenReturn(List.of());

        var result = controller.list();

        assertTrue(result.isEmpty());
    }

    // --- get(id) ---

    @Test
    void getReturnsOkWhenOrderExists() {
        var order = new Order(1001L, "c-1", new BigDecimal("100"), OrderStatus.CREATED);
        when(service.find(1001L)).thenReturn(Optional.of(order));

        var response = controller.get(1001L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1001L, response.getBody().id());
    }

    @Test
    void getReturnsNotFoundWhenOrderMissing() {
        when(service.find(9999L)).thenReturn(Optional.empty());

        var response = controller.get(9999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // --- create() ---

    @Test
    void createReturns201WhenValid() {
        var req = new CreateOrderRequest("c-1", new BigDecimal("150"));
        var created = new Order(1001L, "c-1", new BigDecimal("150"), OrderStatus.CREATED);
        when(service.create("c-1", new BigDecimal("150"))).thenReturn(created);

        var response = controller.create(req);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(created, response.getBody());
    }

    @Test
    void createReturns400WhenInvalid() {
        var req = new CreateOrderRequest("", new BigDecimal("10"));
        when(service.create(any(), any()))
            .thenThrow(new IllegalArgumentException("customerId required"));

        var response = controller.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("customerId required", response.getBody());
    }

    // --- CreateOrderRequest record ---

    @Test
    void createOrderRequestStoresFields() {
        var req = new CreateOrderRequest("c-42", new BigDecimal("99.99"));
        assertEquals("c-42", req.customerId());
        assertEquals(new BigDecimal("99.99"), req.total());
    }
}
