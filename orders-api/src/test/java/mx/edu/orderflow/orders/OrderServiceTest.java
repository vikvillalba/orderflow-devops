package mx.edu.orderflow.orders;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderServiceTest {
    @Test
    void createsValidOrder() {
        var s = new OrderService();
        var o = s.create("student-1", new BigDecimal("150.00"));
        assertEquals(OrderStatus.CREATED, o.status());
        assertEquals("student-1", o.customerId());
    }

    @Test
    void rejectsNegativeTotal() {
        var s = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> s.create("student-1", new BigDecimal("-1")));
    }

    @Test
    void rejectsNullCustomerId() {
        var s = new OrderService();
        assertThrows(IllegalArgumentException.class,
            () -> s.create(null, new BigDecimal("50")));
    }

    @Test
    void rejectsBlankCustomerId() {
        var s = new OrderService();
        assertThrows(IllegalArgumentException.class,
            () -> s.create("   ", new BigDecimal("50")));
    }

    @Test
    void rejectsNullTotal() {
        var s = new OrderService();
        assertThrows(IllegalArgumentException.class,
            () -> s.create("student-1", null));
    }

    @Test
    void listReturnsCreatedOrders() {
        var s = new OrderService();
        s.create("student-1", new BigDecimal("100"));
        s.create("student-2", new BigDecimal("200"));
        assertEquals(2, s.list().size());
    }

    @Test
    void findReturnsOrderWhenExists() {
        var s = new OrderService();
        var created = s.create("student-1", new BigDecimal("100"));
        var found = s.find(created.id());
        assertTrue(found.isPresent());
        assertEquals(created.id(), found.get().id());
    }

    @Test
    void findReturnsEmptyWhenNotExists() {
        var s = new OrderService();
        assertTrue(s.find(9999L).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "-0.01", "0", "0.00"})
    void rejectsNonPositiveAmount(String total){
        // Arrange
        var s = new OrderService();

        //Act y Assert
        assertThrows(IllegalArgumentException.class, () -> s.create("student-1", new BigDecimal(total)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void rejectsInvalidCustomerId(String customerId) {
        //Arrange
        var s = new OrderService();
        var monto = new BigDecimal(100);
        // Act y Assert
        assertThrows(IllegalArgumentException.class, () -> s.create(customerId, monto));
    }
}
