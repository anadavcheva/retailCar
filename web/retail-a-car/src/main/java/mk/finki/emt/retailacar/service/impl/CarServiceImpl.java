package mk.finki.emt.retailacar.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.emt.retailacar.domain.models.Car;
import mk.finki.emt.retailacar.domain.models.CarId;
import mk.finki.emt.retailacar.domain.models.exceptions.CarNotFoundException;
import mk.finki.emt.retailacar.domain.repository.CarRepository;
import mk.finki.emt.retailacar.service.CarService;
import mk.finki.emt.retailacar.service.form.CarForm;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car findById(CarId id) {
        return this.carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    @Override
    public Car createCar(CarForm form) {
        Car car = Car.build(form.getModel(), form.getColor(), form.getSales(), form.getPrice(), null, form.getImage());
        carRepository.save(car);
        return car;
    }

    @Override
    public Car orderItemCreated(CarId carId, int quantity) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        car.addSales(quantity);
        carRepository.saveAndFlush(car);
        return car;
    }

    @Override
    public Car orderItemRemoved(CarId carId, int quantity) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        car.removeSales(quantity);
        carRepository.saveAndFlush(car);
        return car;
    }

    @Override
    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    @Override
    public String getModelByCarId(CarId carId) {
        Car car = this.findById(carId);
        return car.getModel();
    }
}
