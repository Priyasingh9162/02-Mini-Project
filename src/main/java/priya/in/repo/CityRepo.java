package priya.in.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import priya.in.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

}
