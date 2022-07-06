package priya.in.service;

import java.util.Map;

import javax.mail.MessagingException;

import priya.in.bind.Login;
import priya.in.bind.Password;
import priya.in.entity.User;

public interface UserService {
	String emailSender(String email) throws MessagingException, Exception;

	String saveUser(User user);

	String savePass(Password pass);

	String loginUser(Login login);

	Map<Integer, String> getCountery();

	Map<Integer, String> getState(int counteryId);

	Map<Integer, String> getCity(int stateId);
}
