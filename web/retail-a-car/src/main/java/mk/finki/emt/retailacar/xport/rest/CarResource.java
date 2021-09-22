package mk.finki.emt.retailacar.xport.rest;


import lombok.AllArgsConstructor;
import mk.finki.emt.retailacar.domain.models.Car;
import mk.finki.emt.retailacar.domain.models.CarId;
import mk.finki.emt.retailacar.service.CarService;
import mk.finki.emt.retailacar.service.form.CarForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarResource {

    private final CarService carService;

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/model/{id}")
    String findModelById(@PathVariable CarId id) {
        return this.carService.getModelByCarId(id);
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable CarId id) {
        return this.carService.findById(id);
    }

    @PostMapping
    public Car save(@RequestBody CarForm carForm) {
        return this.carService.createCar(carForm);
    }
}
