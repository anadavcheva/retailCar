package mk.finki.emt.ordermanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;


public class OrderItemId extends DomainObjectId {

    private OrderItemId() {
        super(OrderItemId.randomId(OrderItemId.class).getId());
    }

    @JsonCreator
    public OrderItemId(@JsonProperty(value = "orderItemId") @NonNull  String uuid) {
        super(uuid);
    }

    public static OrderId of(String uuid) {
        OrderId orderId = new OrderId(uuid);
        return orderId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
