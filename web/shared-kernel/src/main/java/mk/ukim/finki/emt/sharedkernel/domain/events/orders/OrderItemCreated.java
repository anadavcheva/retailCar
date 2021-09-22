package mk.ukim.finki.emt.sharedkernel.domain.events.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class OrderItemCreated extends DomainEvent {

    private String carId;

    private int quantity;

    public OrderItemCreated() {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.carId = null;
        this.quantity = 0;
    }

    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderItemCreated(@JsonProperty(value = "carId") String carId, @JsonProperty(value = "quantity") int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.carId = carId;
        this.quantity = quantity;
    }

}
