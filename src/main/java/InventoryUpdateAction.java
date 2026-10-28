import java.util.Objects;

/** Имитирует обновление складских запасов. */
public final class InventoryUpdateAction implements OrderAction {
    /** Средство вывода сообщений. */
    private final OrderLogger logger;

    /**
     * Создает операцию с общим средством вывода.
     * @param logger средство вывода сообщений
     */
    public InventoryUpdateAction(OrderLogger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    /**
     * Имитирует обновление складских запасов.
     * @param order обрабатываемый заказ
     */
    @Override
    public void execute(Order order) {
        logger.log("Updating inventory", order);
    }
}
