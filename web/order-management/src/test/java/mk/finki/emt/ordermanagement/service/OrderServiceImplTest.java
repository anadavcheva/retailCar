package mk.finki.emt.ordermanagement.service;

import mk.finki.emt.ordermanagement.domain.models.Order;
import mk.finki.emt.ordermanagement.domain.models.OrderId;
import mk.finki.emt.ordermanagement.domain.valueobjects.Car;
import mk.finki.emt.ordermanagement.domain.valueobjects.CarId;
import mk.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.finki.emt.ordermanagement.xport.client.CarClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarClient carClient;

    private static Car newCar(String model, String color, Money price) {
        Car car = new Car(CarId.randomId(CarId.class), model, color, 0, price);
        return car;
    }

//    @Test
//    public void testPlaceOrder() {
//        OrderItemForm form1 = new OrderItemForm();
//        form1.setCar(newCar("Audi", "black", Money.valueOf(Currency.MKD, 500)));
//        form1.setQuantity(1);
//
//        OrderItemForm form2 = new OrderItemForm();
//        form2.setCar(newCar("BMW", "gold", Money.valueOf(Currency.MKD, 2500)));
//        form2.setQuantity(2);
//
//        OrderForm orderForm = new OrderForm();
//        orderForm.setCurrency(Currency.MKD);
//        orderForm.setItems(Arrays.asList(form1, form2));
//
//        OrderId newOrderId = orderService.placeOrder(orderForm);
//        Order newOrder = orderService.findById(newOrderId);
//        Assertions.assertEquals(newOrder.totalPrice(),Money.valueOf(Currency.MKD,2500));
//
//    }

//    @Test
//    public void testPlaceOrderWithRealData() {
//        List<Car> carList = carClient.findAll();
//        Car car1 = carList.get(0);
//        Car car2 = carList.get(1);
//
//        OrderItemForm oi1 = new OrderItemForm();
//        oi1.setCar(car1);
//        oi1.setQuantity(1);
//
//        OrderItemForm oi2 = new OrderItemForm();
//        oi2.setCar(car2);
//        oi2.setQuantity(2);
//
//        OrderForm orderForm = new OrderForm();
//        orderForm.setCurrency(Currency.MKD);
//        orderForm.setItems(Arrays.asList(oi1,oi2));
//
//        OrderId newOrderId = orderService.placeOrder(orderForm);
//        Order newOrder = orderService.findById(newOrderId);
//
//        Money outMoney = car1.getPrice().multiply(oi1.getQuantity()).add(car2.getPrice().multiply(oi2.getQuantity()));
//        Assertions.assertEquals(newOrder.totalPrice(),outMoney);
//    }
}
