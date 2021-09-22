package mk.finki.emt.ordermanagement.domain.exceptions;

public class OrderItemAlreadyExists extends RuntimeException{
    public OrderItemAlreadyExists(String message) {
        super(message);
    }
}
