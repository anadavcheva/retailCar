package mk.finki.emt.retailacar.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;



public class RetailAgencyId extends DomainObjectId {

    protected RetailAgencyId() {
        super(RetailAgencyId.randomId(RetailAgencyId.class).getId());
    }

    public RetailAgencyId(@NonNull String uuid) {
        super(uuid);
    }

    public static RetailAgencyId of(String uuid) {
        RetailAgencyId retailAgencyId = new RetailAgencyId(uuid);
        return retailAgencyId;
    }
}
