package priya.in.rest;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import priya.in.bind.Login;
import priya.in.bind.Password;
import priya.in.entity.User;
import priya.in.service.UserService;

@RestController
public class Rest {
	@Autowired
	private UserService userService;

	@PostMapping("/sign-up")
	public String sighUpUser(@RequestBody User user) throws MessagingException, Exception {
		String msg= userService.saveUser(user);
		if(msg.equals("Resistration successfull"))
			userService.emailSender(user.getEmail());
		return msg;
	}

	@PostMapping("/set-data")
	public String setPass(@RequestBody Password pass) {
		String msg = userService.savePass(pass);
		return msg;
	}

	@PostMapping("/log-in")
	public String logIn(@RequestBody Login login) {
		String loginUser = userService.loginUser(login);
		return loginUser;
	}

	@GetMapping("forget-pass/{email}")
	public String forgetPass(@PathVariable String email) throws MessagingException, Exception {
		String msg = userService.emailSender(email);
		return msg;
	}
	@GetMapping("/get-countery")
	public Map<Integer, String > getCountery(){
		Map<Integer, String> countery = userService.getCountery();
		return countery;
		
	}
	@GetMapping("/get-state/{ctId}")
	public Map<Integer, String > getState(@PathVariable Integer ctId){
		Map<Integer, String> state = userService.getState(ctId);
		return state;
		
	}
	@GetMapping("/get-city/{stId}")
	public Map<Integer, String > getCity(@PathVariable Integer stId){
		Map<Integer, String> city = userService.getCity(stId);
		return city;
		
	}

}
