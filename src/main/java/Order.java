/** Неизменяемые данные заказа. */
public final class Order {
    /** Номер заказа. */
    private final int id;
    /** Статус заказа. */
    private final String status;

    /**
     * Создает заказ.
     * @param id номер заказа
     * @param status исходный строковый статус
     */
    public Order(int id, String status) {
        this.id = id;
        this.status = status;
    }

    /** @return номер заказа */
    public int getId() {
        return id;
    }

    /** @return статус заказа */
    public String getStatus() {
        return status;
    }
}
