package mk.finki.emt.retailacar.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

@Getter
public class CarId extends DomainObjectId {

    protected CarId() {
        super(CarId.randomId(CarId.class).getId());
    }

    public CarId(@NonNull String uuid) {
        super(uuid);
    }

    public static CarId of(String uuid) {
        CarId car = new CarId(uuid);
        return car;
    }
}
