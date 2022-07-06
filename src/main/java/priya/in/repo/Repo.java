package priya.in.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import priya.in.entity.User;

public interface Repo extends JpaRepository<User, Integer> {

	@Query(value = "select * from user_details where email=:email", nativeQuery = true)
	User findByEmaill(String email);

	@Query(value = "select * from user_details where password=:pass", nativeQuery = true)
	User findByPass(String pass);
	
	
	@Modifying
	@Transactional
	@Query(value= "update USER_DETAILS set PASSWORD=:newPass, ACC_STATUS=:status where USER_ID =:id", nativeQuery=true)
	void updatePass(int id,  String newPass, String status);
	
	@Query(value="select * from user_details where email=:email and password=:pass" , nativeQuery=true)
	User findByEmailAndPass(String email, String pass);
	
	@Query(value="select * from user_details where acc_status=:status and email=:email" , nativeQuery=true)
	User findAccStatus(String status, String email);

}
