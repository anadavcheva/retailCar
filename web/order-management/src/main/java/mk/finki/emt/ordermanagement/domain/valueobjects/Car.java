package mk.finki.emt.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter

public class Car implements ValueObject {

    private final CarId id;

    private final String model;

    private final String color;

    private final int sales;

    private final Money price;



    private Car() {
        this.id = CarId.randomId(CarId.class);
        this.model = "";
        this.color = "";
        this.sales = 0;
        this.price = Money.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    // ovde treba da se dodadat @JsonProperty na sekoj parametar
    public Car(@JsonProperty("id") CarId id,
               @JsonProperty("model") String model,
               @JsonProperty("color") String color,
               @JsonProperty("sales") int sales,
               @JsonProperty("price") Money price) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.sales = sales;
        this.price = price;
    }
}
