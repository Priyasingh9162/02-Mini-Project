package priya.in.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "user_details")
public class User {
	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "mobile")
	private long mobile;
	@Column(name = "dob")
	private LocalDate dob;
	@Column(name = "gender")
	private String gender;
	@Column(name = "country_id")
	private Integer country;
	@Column(name = "state_id")
	private Integer state;
	@Column(name = "city_id")
	private Integer city;
	@Column(name = "acc_status")
	private String accStatus;
	@CreationTimestamp
	@Column(name = "Create_Date", updatable = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(name = "Update_Date", insertable = false)
	private LocalDate updatedDate;
}
