package priya.in.bind;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Login {
private String email;
private String pass;
Login(){}

}
