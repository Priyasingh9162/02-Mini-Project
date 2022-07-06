package priya.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "countery_master")
public class Countery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "countery_id")
	private Integer counteryId;
	@Column(name = "countery_code")
	private Integer counteryCode;
	@Column(name = "countery_name")
	private String counteryName;

	public Countery() {

	}

}
