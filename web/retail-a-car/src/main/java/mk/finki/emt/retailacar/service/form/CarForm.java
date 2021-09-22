package mk.finki.emt.retailacar.service.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.finki.emt.retailacar.domain.models.RetailAgency;
import mk.finki.emt.retailacar.domain.models.RetailAgencyId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CarForm {

    private String model;

    private String color;

    private int sales;

    private Money price;

    private RetailAgencyId retailAgencyId;

    private String image;
}
