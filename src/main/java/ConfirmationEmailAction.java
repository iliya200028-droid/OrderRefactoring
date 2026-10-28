import java.util.Objects;

/** Имитирует отправку подтверждения заказа. */
public final class ConfirmationEmailAction implements OrderAction {
    /** Средство вывода сообщений. */
    private final OrderLogger logger;

    /**
     * Создает операцию с общим средством вывода.
     * @param logger средство вывода сообщений
     */
    public ConfirmationEmailAction(OrderLogger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    /**
     * Имитирует отправку подтверждения заказа.
     * @param order обрабатываемый заказ
     */
    @Override
    public void execute(Order order) {
        logger.log("Sending confirmation email", order);
    }
}
