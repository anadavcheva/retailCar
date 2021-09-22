package mk.finki.emt.ordermanagement.domain.models;


import lombok.Getter;
import lombok.NonNull;
import mk.finki.emt.ordermanagement.domain.valueobjects.Car;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant date;

    @Enumerated(value = EnumType.STRING)
    private OrderState state;

    // this is for further work: switching currencies
    @Column(name = "order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemSet = new HashSet<>();

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name = "total_amount")),
            @AttributeOverride(name="currency", column = @Column(name = "total_currency")),
    })
    private Money total;

    public Order() {
        super(OrderId.randomId(OrderId.class));
        this.date = Instant.now();
        this.currency = Currency.MKD;
        this.state = OrderState.CREATED;
        this.total = this.totalPrice();
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.date = now;
        this.currency = currency;
        this.total = this.totalPrice();
    }

    public void changeOrderState(OrderState orderState) {
        this.state = orderState;
    }

    public Money totalPrice() {
        return orderItemSet.stream()
                .map(OrderItem::subtotal)
                .reduce(new Money(currency, 0), Money::add);
    }
    public void setMoney(Money money) {
        this.total = money;
    }


    public OrderItem addItem(@NonNull Car car, int quantity) {
        Objects.requireNonNull(car, "car value must not be null");
        var item = new OrderItem(car.getId(), car.getModel(), car.getPrice(), quantity);
        orderItemSet.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId, "orderItemId value must not be null");
        orderItemSet.removeIf(v -> v.getId().equals(orderItemId));
    }


}
