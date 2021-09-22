package mk.finki.emt.ordermanagement.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import mk.finki.emt.ordermanagement.domain.valueobjects.CarId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem  extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name="id",  column = @Column(name = "car_id", nullable = false))
    private CarId carId;

    private String model;

    protected OrderItem() {
        super(DomainObjectId.randomId(OrderItemId.class));
    }

    public OrderItem(@NonNull CarId carId, @NonNull String model,  @NonNull Money itemPrice, int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.carId = carId;
        this.model = model;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(itemPrice, orderItem.itemPrice) && Objects.equals(carId, orderItem.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemPrice, quantity, carId);
    }
}
