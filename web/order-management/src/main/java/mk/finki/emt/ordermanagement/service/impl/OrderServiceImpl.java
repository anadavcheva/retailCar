package mk.finki.emt.ordermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.emt.ordermanagement.domain.exceptions.OrderItemAlreadyExists;
import mk.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotExistsException;
import mk.finki.emt.ordermanagement.domain.models.*;
import mk.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.finki.emt.ordermanagement.service.OrderService;
import mk.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemRemoved;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OrderId placeOrder(OrderId orderId) {
        Order newOrder = this.findById(orderId);
        newOrder.changeOrderState(OrderState.PROCESSING);
        newOrder.setMoney(newOrder.totalPrice());
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order findById(OrderId id) {
        return this.orderRepository.findById(id).orElseThrow(OrderIdNotExistException::new);
    }

    @Override
    public Set<OrderItem> findAllById(OrderId id) {
        Order order = this.findById(id);
        return order.getOrderItemSet();
    }

    @Override
    public OrderItem addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        OrderItem orderItem = order.getOrderItemSet()
                .stream()
                .filter(item -> item.getModel().equals(orderItemForm.getCar().getModel()))
                .findAny().orElse(null);
        if (orderItem != null) {
            return orderItem;
        }
        OrderItem item = order.addItem(orderItemForm.getCar(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getCar().getId().getId(), orderItemForm.getQuantity()));
        return item;
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderItemIdNotExistsException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        OrderItem orderItem = order.getOrderItemSet()
                .stream()
                .filter(item -> item.getId().getId().equals(orderItemId.getId()))
                .findFirst().orElse(null);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
        if (orderItem != null) {
            domainEventPublisher.publish(new OrderItemRemoved(orderItem.getCarId().getId(), orderItem.getQuantity()));
        }
    }

    @Override
    public OrderId createOrder() {
        Order order = new Order();
        this.orderRepository.save(order);
        return order.getId();
    }
    @Override
    public void cancelOrder(OrderId orderId) {
        Order order = this.findById(orderId);
        this.orderRepository.delete(order);
    }

    @Override
    public void increaseQuantity(OrderId orderId, OrderItemId orderItemId) {
        Order order = this.findById(orderId);
        OrderItem orderItem = order.getOrderItemSet()
                .stream()
                .filter(item -> item.getId().getId().equals(orderItemId.getId())).findAny().orElseThrow(OrderItemIdNotExistsException::new);
        this.deleteItem(order.getId(), orderItem.getId());
        orderItem.increaseQuantity();
        order.getOrderItemSet().add(orderItem);
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public void decreaseQuantity(OrderId orderId, OrderItemId orderItemId) {
        Order order = this.findById(orderId);
        OrderItem orderItem = order.getOrderItemSet()
                .stream()
                .filter(item -> item.getId().getId().equals(orderItemId.getId())).findAny().orElseThrow(OrderItemIdNotExistsException::new);
        this.deleteItem(order.getId(), orderItem.getId());
        orderItem.decreaseQuantity();
        order.getOrderItemSet().add(orderItem);
        this.orderRepository.saveAndFlush(order);
    }


    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems()
                .forEach(item -> order.addItem(item.getCar(), item.getQuantity()));
        return order;
    }
}
