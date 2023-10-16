package com.bcbs.customer.survey;

import com.bcbs.customer.survey.constants.SurveyConstants;
import com.bcbs.customer.survey.repository.UserRepository;
import com.bcbs.customer.survey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CustomerSurveyApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerSurveyApplication.class, args);
	}

	public void run(String... args){

		// For local testing create admin if its not exist in db.
		User adminAccount = userRepository.findByRole(SurveyConstants.ADMIN_ROLE);
		if(null == adminAccount){
			User user = new User();

			user.setName(SurveyConstants.ADMIN_ROLE);
			user.setUsername(SurveyConstants.ADMIN_ROLE);
			user.setRole(SurveyConstants.ADMIN_ROLE);
			user.setPassword(new BCryptPasswordEncoder().encode(SurveyConstants.ADMIN_ROLE));

			userRepository.save(user);

		}
	}

}
