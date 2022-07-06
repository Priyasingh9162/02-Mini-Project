package priya.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state_master")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	private Integer stateId;
	@Column(name = "state_name")
	private String stateName;
	@Column(name = "countery_id")
	private Integer counteryId;

}
