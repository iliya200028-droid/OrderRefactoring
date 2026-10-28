/** Одна операция обработки заказа. */
@FunctionalInterface
public interface OrderAction {
    /**
     * Выполняет операцию.
     * @param order обрабатываемый заказ
     */
    void execute(Order order);
}
