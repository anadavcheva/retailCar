package mk.finki.emt.retailacar.service;

import mk.finki.emt.retailacar.domain.models.Car;
import mk.finki.emt.retailacar.domain.models.CarId;
import mk.finki.emt.retailacar.service.form.CarForm;

import java.util.List;

public interface CarService {

    Car findById(CarId id);

    Car createCar(CarForm form);

    Car orderItemCreated(CarId carId, int quantity);

    Car orderItemRemoved(CarId carId, int quantity);

    List<Car> getAll();

    String getModelByCarId(CarId carId);
}
