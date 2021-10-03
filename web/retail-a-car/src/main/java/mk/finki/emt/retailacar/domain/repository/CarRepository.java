package mk.finki.emt.retailacar.domain.repository;

import mk.finki.emt.retailacar.domain.models.Car;
import mk.finki.emt.retailacar.domain.models.CarId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, CarId> {
}
