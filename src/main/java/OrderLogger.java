import java.io.PrintStream;
import java.util.Objects;

/** Централизует формат консольных сообщений об операциях. */
public final class OrderLogger {
    /** Поток вывода. */
    private final PrintStream output;

    /**
     * Задает поток вывода.
     * @param output поток сообщений
     */
    public OrderLogger(PrintStream output) {
        this.output = Objects.requireNonNull(output);
    }

    /**
     * Выводит действие и номер заказа в общем формате.
     * @param action описание действия
     * @param order заказ
     */
    public void log(String action, Order order) {
        output.println(action + " for order #" + order.getId());
    }
}
