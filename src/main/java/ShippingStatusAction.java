import java.util.Objects;

/** Имитирует обновление статуса доставки. */
public final class ShippingStatusAction implements OrderAction {
    /** Средство вывода сообщений. */
    private final OrderLogger logger;

    /**
     * Создает операцию с общим средством вывода.
     * @param logger средство вывода сообщений
     */
    public ShippingStatusAction(OrderLogger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    /**
     * Имитирует обновление статуса доставки.
     * @param order обрабатываемый заказ
     */
    @Override
    public void execute(Order order) {
        logger.log("Updating shipping status", order);
    }
}
