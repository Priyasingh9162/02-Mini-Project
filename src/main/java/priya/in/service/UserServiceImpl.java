package priya.in.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Example;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import priya.in.bind.Login;
import priya.in.bind.Password;
import priya.in.entity.City;
import priya.in.entity.Countery;
import priya.in.entity.State;
import priya.in.entity.User;
import priya.in.repo.CityRepo;
import priya.in.repo.CounteryRepo;
import priya.in.repo.Repo;
import priya.in.repo.StateRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private Repo repo;
	@Autowired
	private CounteryRepo cr;
	@Autowired
	private StateRepo sr;
	@Autowired
	private CityRepo cityr;

	@Autowired(required=true)
	private JavaMailSender mailsender;

	
	@Override
	public String emailSender(String email) throws MessagingException, Exception {
		User emaill = repo.findByEmaill(email);
		if (emaill != null) {
			String tempPass = new String(this.generatePassword(6));
			emaill.setPassword(tempPass);
			repo.save(emaill);
			this.sendEmailWithAttachment(email, "<h1>"+" This body contains hyperlink to set new password tempPass:-"+" </h1>" + tempPass,
					"Regarding:your password genration");
			return "sent an email with temporary password on " + email;
		}

		return "Invalid Credential";
	}

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		if (repo.findByEmaill(user.getEmail()) != null) {
			return "email already exist";
		}
		user.setAccStatus("N");
		User save = repo.save(user);
		if (save.getId() != null)
			return "Resistration successfull";
		return "failed to resister";

	}

	@Override
	public String savePass(Password pass) {
		User user = repo.findByPass(pass.getTempPass());
		if (user == null)
			return "Wrong credential";
		user.setPassword(pass.getNewPass());
		user.setAccStatus("Y");
		User save = repo.save(user);
		if (save.getId() != null)
			return "Your account is unlocked and password has been updated";
		return "Unable to unlock your account";
	}

	@Override
	public String loginUser(Login login) {
		User user = repo.findByEmailAndPass(login.getEmail(), login.getPass());
		if (user != null) {
			User status = repo.findAccStatus("Y", login.getEmail());
			if (status != null)
				return "Success";
			return "Your account is not unlock";
		}

		return "Inavlid Credential";
	}

	private static char[] generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password;
	}

	public void sendEmail(String toemail, String body, String Sub) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("priyakumarishalu@gmail.com");
		message.setTo(toemail);
		message.setText(body);
		message.setSubject(Sub);
		mailsender.send(message);
		System.out.println("mail sent..!!");
	}

	@Override
	public Map<Integer, String> getCountery() {
		Map<Integer, String> hs = new HashMap<>();
		List<Countery> all = cr.findAll();
		for (Countery c : all) {
			hs.put(c.getCounteryId(), c.getCounteryName());
		}
		return hs;
	}

	@Override
	public Map<Integer, String> getState(int counteryId) {
		Map<Integer, String> hs = new HashMap<>();
		State st = new State();
		st.setCounteryId(counteryId);
		List<State> all = sr.findAll(Example.of(st));
		for (State s : all) {
			hs.put(s.getStateId(), s.getStateName());
		}

		return hs;
	}

	@Override
	public Map<Integer, String> getCity(int stateId) {
		Map<Integer, String> hs = new HashMap<>();
		City c = new City();
		c.setStateId(stateId);
		List<City> list = cityr.findAll(Example.of(c));
		for (City ct : list) {
			hs.put(ct.getCityId(), ct.getCityName());
		}
		return hs;
	}
	public void sendEmailWithAttachment(String toemail, String body, String Sub)
			throws MessagingException, Exception {
		MimeMessage message = mailsender.createMimeMessage();
		MimeMessageHelper mime = new MimeMessageHelper(message, true);
		mime.setFrom("priyakumarishalu@gmail.com");
		mime.setTo(toemail);
		mime.setText(body, true);
		mime.setSubject(Sub);
		mailsender.send(message);
		System.out.println("mail sent with attachment");
	}

}
