package mx.edu.orderflow.notifications;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NotificationServiceTest {

    @Test
    void createsConfirmation() {
        var m = new NotificationService().confirmation("1001", "c-1");
        assertTrue(m.message().contains("1001"));
    }

    @Test
    void rejectsNullOrderId() {
        assertThrows(IllegalArgumentException.class,
            () -> new NotificationService().confirmation(null, "c-1"));
    }

    @Test
    void rejectsBlankOrderId() {
        assertThrows(IllegalArgumentException.class,
            () -> new NotificationService().confirmation("  ", "c-1"));
    }

    @Test
    void messageContainsCustomerId() {
        var m = new NotificationService().confirmation("1001", "c-99");
        assertEquals("c-99", m.customerId());
    }
}
