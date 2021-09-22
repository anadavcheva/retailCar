package mk.finki.emt.retailacar.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "retail_agency")
@AllArgsConstructor
@Getter
public class RetailAgency extends AbstractEntity<RetailAgencyId> {

    private String name;

    private String address;

    private String phone;

    private String email;

    protected RetailAgency() {
        super(RetailAgencyId.randomId(RetailAgencyId.class));
    }

}
