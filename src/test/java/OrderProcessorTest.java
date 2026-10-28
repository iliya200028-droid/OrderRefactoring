import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Регрессионные проверки без внешних библиотек. */
public final class OrderProcessorTest {
    /** Запрещает создание экземпляра. */
    private OrderProcessorTest() {
    }

    /**
     * Проверяет сообщения, их порядок и расширяемость.
     * @param args не используются
     */
    public static void main(String[] args) {
        checkOutput(List.of(new Order(7, "confirmed")),
                "Sending confirmation email for order #7",
                "Updating inventory for order #7",
                "Generating invoice for order #7");
        checkOutput(List.of(new Order(8, "shipped")),
                "Updating shipping status for order #8");
        checkOutput(List.of(new Order(9, "pending")));
        checkOutput(List.of());
        checkOutput(List.of(new Order(1, "shipped"), new Order(2, "confirmed")),
                "Updating shipping status for order #1",
                "Sending confirmation email for order #2",
                "Updating inventory for order #2",
                "Generating invoice for order #2");
        List<Integer> processedIds = new ArrayList<>();
        OrderAction cancel = order -> processedIds.add(order.getId());
        new OrderProcessor(Map.of("cancelled", List.of(cancel)))
                .processOrders(List.of(new Order(10, "cancelled")));
        if (!processedIds.equals(List.of(10))) {
            throw new AssertionError("New status was not processed");
        }
        System.out.println("PASS: 6 checks");
    }

    /**
     * Сравнивает фактический вывод с контрактом исходного примера.
     * @param orders тестовые заказы
     * @param expected ожидаемые строки в точном порядке
     */
    private static void checkOutput(List<Order> orders, String... expected) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            OrderLogger logger = new OrderLogger(output);
            OrderProcessor processor = new OrderProcessor(Map.of(
                    "confirmed", List.of(new ConfirmationEmailAction(logger),
                            new InventoryUpdateAction(logger),
                            new InvoiceGenerationAction(logger)),
                    "shipped", List.of(new ShippingStatusAction(logger))));
            processor.processOrders(orders);
        }
        List<String> actual = bytes.toString(StandardCharsets.UTF_8).lines().toList();
        if (!actual.equals(List.of(expected))) {
            throw new AssertionError("Expected: " + List.of(expected) + "; actual: " + actual);
        }
    }
}
