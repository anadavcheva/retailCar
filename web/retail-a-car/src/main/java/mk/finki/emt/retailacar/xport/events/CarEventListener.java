package mk.finki.emt.retailacar.xport.events;


import lombok.AllArgsConstructor;
import mk.finki.emt.retailacar.domain.models.CarId;
import mk.finki.emt.retailacar.service.CarService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarEventListener {

    private final CarService carService;


    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "retailCar")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            carService.orderItemCreated(CarId.of(event.getCarId()), event.getQuantity());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "retailCar")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage, OrderItemRemoved.class);
            carService.orderItemRemoved(CarId.of(event.getCarId()), event.getQuantity());
        }
        catch (Exception e) {

        }
    }
}
