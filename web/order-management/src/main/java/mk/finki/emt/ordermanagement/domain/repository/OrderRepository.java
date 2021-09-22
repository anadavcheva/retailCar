package mk.finki.emt.ordermanagement.domain.repository;

import mk.finki.emt.ordermanagement.domain.models.Order;
import mk.finki.emt.ordermanagement.domain.models.OrderId;
import mk.finki.emt.ordermanagement.domain.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface    OrderRepository extends JpaRepository<Order, OrderId> {

}
