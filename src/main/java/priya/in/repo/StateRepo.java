package priya.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import priya.in.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {

}
