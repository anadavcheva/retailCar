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

    // switch order state from CREATED to PROCESSING
    OrderId placeOrder(OrderId orderId);

    List<Order> findAll();

    Order findById(OrderId id);

    //get order items
    Set<OrderItem> findAllById(OrderId id);

    OrderItem addItem(OrderId orderId, OrderItemForm orderItemForm)
            throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderItemIdNotExistsException;

    OrderId createOrder();

    // increasing quantity for given order item
    void increaseQuantity(OrderId orderId, OrderItemId orderItemId);

    // decreasing quantity for given order item
    void decreaseQuantity(OrderId orderId, OrderItemId orderItemId);

    // Cancel the order before you click place order
    void cancelOrder(OrderId orderId);
}
