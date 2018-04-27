package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Value("${webmaster.username}")
	private String webmasterUsername;
	@Value("${webmaster.password}")
	private String webmasterPassword;
	@Value("${webmaster.email}")
	private String webmasterEmail;

	/* The Application Logger */
	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Set<UserRole> userRoles = new HashSet<>();
		User basicUser = UserUtils.createBasicUser(webmasterUsername, webmasterEmail);
		basicUser.setPassword(webmasterPassword);
		userRoles.add(new UserRole(basicUser, new Role(RolesEnum.ADMIN)));
		LOG.debug("Creating user with username {}", basicUser.getUsername());
		userService.createUser(basicUser, PlanEnum.BASIC, userRoles);
		LOG.debug("User {} created", basicUser.getUsername());

	}
}
