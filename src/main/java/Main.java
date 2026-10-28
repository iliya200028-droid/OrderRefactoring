import java.util.List;
import java.util.Map;

/** Точка входа и место настройки зависимостей приложения. */
public final class Main {
    /** Запрещает создание экземпляра служебного класса. */
    private Main() {
    }

    /**
     * Запускает пример обработки заказов.
     * @param args аргументы командной строки; не используются
     */
    public static void main(String[] args) {
        OrderLogger logger = new OrderLogger(System.out);
        OrderProcessor processor = new OrderProcessor(Map.of(
                "confirmed", List.of(
                        new ConfirmationEmailAction(logger),
                        new InventoryUpdateAction(logger),
                        new InvoiceGenerationAction(logger)),
                "shipped", List.of(new ShippingStatusAction(logger))));
        processor.processOrders(List.of(
                new Order(1, "confirmed"),
                new Order(2, "shipped"),
                new Order(3, "pending")));
    }
}
