package mk.finki.emt.ordermanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.finki.emt.ordermanagement.domain.models.Order;
import mk.finki.emt.ordermanagement.domain.models.OrderId;
import mk.finki.emt.ordermanagement.domain.models.OrderItem;
import mk.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.finki.emt.ordermanagement.service.OrderService;
import mk.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable OrderId id) {
        return this.orderService.findById(id);
    }

    @GetMapping("/total/{id}")
    public Money getTotalPrice(@PathVariable OrderId id) {
        return this.orderService.findById(id).totalPrice();
    }

    @GetMapping("/items/{id}")
    public Set<OrderItem> findItemsById(@PathVariable OrderId id) {
        return this.orderService.findAllById(id);
    }

    @GetMapping("/new-order")
    public OrderId createNewOrder() {
        return this.orderService.createOrder();
    }

    @PostMapping("/cancel-order/{id}")
    public void deleteOrder(@PathVariable OrderId id) {
        this.orderService.cancelOrder(id);
    }

    @PostMapping("/place-order/{id}")
    public OrderId placeOrder(@PathVariable OrderId id) {
        return this.orderService.placeOrder(id);
    }

    @PostMapping("/add/{id}")
    public OrderItem addItem(@PathVariable OrderId id, @RequestBody OrderItemForm orderItemForm) {
        return this.orderService.addItem(id, orderItemForm);
    }

    @PostMapping("/delete/{id}")
    public void deleteItem(@PathVariable OrderId id, @RequestBody OrderItemId orderItemId) {
        this.orderService.deleteItem(id, orderItemId);
    }

    @PostMapping("/increase/{id}")
    public void increaseQuantity(@PathVariable OrderId id, @RequestBody OrderItemId orderItemId) {
        this.orderService.increaseQuantity(id, orderItemId);
    }

    @PostMapping("/decrease/{id}")
    public void decreaseQuantity(@PathVariable OrderId id, @RequestBody OrderItemId orderItemId) {
        this.orderService.decreaseQuantity(id, orderItemId);
    }

}
