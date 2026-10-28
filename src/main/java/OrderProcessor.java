import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Выбирает операции по статусу и выполняет их в заданном порядке. */
public final class OrderProcessor {
    /** Правила обработки по статусам. */
    private final Map<String, List<OrderAction>> actionsByStatus;

    /**
     * Принимает правила обработки извне и сохраняет их защитную копию.
     * @param actionsByStatus соответствие статусов последовательностям операций
     */
    public OrderProcessor(Map<String, List<OrderAction>> actionsByStatus) {
        this.actionsByStatus = new HashMap<>();
        actionsByStatus.forEach((status, actions) ->
                this.actionsByStatus.put(
                        Objects.requireNonNull(status), List.copyOf(actions)));
    }

    /**
     * Обрабатывает заказы в порядке поступления.
     * @param orders заказы с ненулевыми статусами
     */
    public void processOrders(List<Order> orders) {
        for (Order order : orders) {
            processOrder(order);
        }
    }

    /**
     * Выполняет операции одного заказа; неизвестный статус пропускает.
     * @param order заказ с ненулевым статусом
     */
    private void processOrder(Order order) {
        String status = Objects.requireNonNull(order.getStatus());
        for (OrderAction action : actionsByStatus.getOrDefault(status, List.of())) {
            action.execute(order);
        }
    }
}
