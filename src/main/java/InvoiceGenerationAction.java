import java.util.Objects;

/** Имитирует формирование счета. */
public final class InvoiceGenerationAction implements OrderAction {
    /** Средство вывода сообщений. */
    private final OrderLogger logger;

    /**
     * Создает операцию с общим средством вывода.
     * @param logger средство вывода сообщений
     */
    public InvoiceGenerationAction(OrderLogger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    /**
     * Имитирует формирование счета.
     * @param order обрабатываемый заказ
     */
    @Override
    public void execute(Order order) {
        logger.log("Generating invoice", order);
    }
}
