package mk.finki.emt.ordermanagement.service.forms;

import lombok.*;
import mk.finki.emt.ordermanagement.domain.valueobjects.Car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class OrderItemForm {

    @NotNull
    private Car car;

    @Min(1)
    private int quantity = 1;
}
