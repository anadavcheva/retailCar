package mk.finki.emt.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CarId extends DomainObjectId {

    protected CarId() {
        super(CarId.randomId(CarId.class).getId());
    }

    @JsonCreator
    public CarId(@JsonProperty(value = "id") String uuid) {
        super(uuid);
    }

    public static CarId of(String uuid) {
        CarId carId = new CarId(uuid);
        return carId;
    }

}
