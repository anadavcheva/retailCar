package mk.finki.emt.retailacar.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject  {
    // ne se naogja vo shared kernel bidejki se odnesuva samo za ovoj ogranichen kontekst.
    private final int quantity;

    protected  Quantity() {
        this.quantity = 0;
    }

}
