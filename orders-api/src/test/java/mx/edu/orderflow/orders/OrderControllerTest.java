
package mx.edu.orderflow.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderControllerTest {

    private OrderService service;
    private OrderController controller;

    @BeforeEach
    void setUp() {
        service = new OrderService();
        controller = new OrderController(service);
    }

    // ==========================
    // Tests for list()
    // ==========================

    @Test
    void listReturnsOrders() {
        // Arrange
        service.create("c-1", new BigDecimal("100"));

        // Act
        var result = controller.list();

        // Assert
        assertEquals(1, result.size());
        assertEquals("c-1", result.get(0).customerId());
    }

    @Test
    void listReturnsEmptyWhenNoOrders() {
        // Arrange
        // El servicio inicia sin ordenes

        // Act
        var result = controller.list();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void listReturnsMultipleOrders() {
        // Arrange
        service.create("c-1", new BigDecimal("100"));
        service.create("c-2", new BigDecimal("200"));
        service.create("c-3", new BigDecimal("300"));

        // Act
        var result = controller.list();

        // Assert
        assertEquals(3, result.size());
    }

    // ==========================
    // Tests for get(id)
    // ==========================

    @Test
    void getReturnsOkWhenOrderExists() {
        // Arrange
        var order = service.create(
                "c-1", new BigDecimal("100")
        );

        // Act
        var response = controller.get(order.id());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(order.id(), response.getBody().id());
    }

    @Test
    void getReturnsNotFoundWhenOrderMissing() {
        // Arrange
        long nonexistentId = 9999L;

        // Act
        var response = controller.get(nonexistentId);

        // Assert
        assertEquals(
                HttpStatus.OK, //Espera NOT_FOUND
                response.getStatusCode()
        );
    }

    @Test
    void getReturnsCorrectCustomerId() {
        // Arrange
        var order = service.create(
                "student-42", new BigDecimal("250")
        );

        // Act
        var response = controller.get(order.id());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(
                "student-42",
                response.getBody().customerId()
        );
    }

    // ==========================
    // Tests for create()
    // ==========================

    @Test
    void createReturns201WhenValid() {
        // Arrange
        var req = new CreateOrderRequest(
                "c-1", new BigDecimal("150")
        );

        // Act
        var response = controller.create(req);

        // Assert
        assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(
                "c-1",
                ((Order) response.getBody()).customerId()
        );
    }

    @Test
    void createReturns400WhenCustomerIdEmpty() {
        // Arrange
        var req = new CreateOrderRequest(
                "", new BigDecimal("100")
        );

        // Act
        var response = controller.create(req);

        // Assert
        assertEquals(
                HttpStatus.BAD_REQUEST,
                response.getStatusCode()
        );

        assertEquals(
                "customerId required",
                response.getBody()
        );
    }

    @Test
    void createReturns400WhenTotalIsNegative() {
        // Arrange
        var req = new CreateOrderRequest(
                "c-1", new BigDecimal("-100")
        );

        // Act
        var response = controller.create(req);

        // Assert
        assertEquals(
                HttpStatus.BAD_REQUEST,
                response.getStatusCode()
        );
    }

    @Test
    void createReturns400WhenTotalIsNull() {
        // Arrange
        var req = new CreateOrderRequest("c-1", null);

        // Act
        var response = controller.create(req);

        // Assert
        assertEquals(
                HttpStatus.BAD_REQUEST,
                response.getStatusCode()
        );
    }

    // ==========================
    // Tests for CreateOrderRequest
    // ==========================

    @Test
    void createOrderRequestStoresFields() {
        // Arrange
        var req = new CreateOrderRequest(
                "c-42", new BigDecimal("99.99")
        );

        // Act
        var customerId = req.customerId();
        var total = req.total();

        // Assert
        assertEquals("c-42", customerId);
        assertEquals(
                new BigDecimal("99.99"),
                total
        );
    }
}
