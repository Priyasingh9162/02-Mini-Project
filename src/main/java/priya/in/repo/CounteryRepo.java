package priya.in.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import priya.in.entity.Countery;

public interface CounteryRepo extends JpaRepository<Countery, Integer>{


	
}
