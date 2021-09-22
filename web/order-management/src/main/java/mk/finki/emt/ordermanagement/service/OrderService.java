package mk.finki.emt.ordermanagement.service;

import mk.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotExistsException;
import mk.finki.emt.ordermanagement.domain.models.Order;
import mk.finki.emt.ordermanagement.domain.models.OrderId;
import mk.finki.emt.ordermanagement.domain.models.OrderItem;
import mk.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderService {

    OrderId placeOrder(OrderId orderId);

    List<Order> findAll();

    Order findById(OrderId id);

    Set<OrderItem> findAllById(OrderId id);

    OrderItem addItem(OrderId orderId, OrderItemForm orderItemForm)
            throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderItemIdNotExistsException;

    OrderId createOrder();

    void increaseQuantity(OrderId orderId, OrderItemId orderItemId);

    void decreaseQuantity(OrderId orderId, OrderItemId orderItemId);

    void cancelOrder(OrderId orderId);
}
