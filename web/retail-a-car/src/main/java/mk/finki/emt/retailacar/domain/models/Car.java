package mk.finki.emt.retailacar.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.finki.emt.retailacar.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
public class Car extends AbstractEntity<CarId> {

    private String model;

    private String color;

    private int sales = 0;

    private String image;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;

    @ManyToOne
    private RetailAgency agency;

    protected Car() {
        super(CarId.randomId(CarId.class));
    }

    public static Car build(String model, String color, int sales, Money price, RetailAgency retailAgency, String image) {
        Car car = new Car();
        car.model = model;
        car.color = color;
        car.sales = sales;
        car.price = price;
        car.agency = retailAgency;
        car.image = image;
        return car;
    }

    public void addSales(int qty) {
        this.sales = this.sales + qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }


}
