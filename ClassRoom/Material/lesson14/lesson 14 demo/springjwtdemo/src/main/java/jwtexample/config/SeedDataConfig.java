package jwtexample.config;

import jwtexample.models.Role;
import jwtexample.models.User;
import jwtexample.repositories.UserRepository;
import jwtexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class SeedDataConfig implements CommandLineRunner {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserService userService;

    @Override
    public void run(String... args) throws Exception {
        
      if (userRepository.count() == 0) {

        User admin = new User("admin","admin","admin@admin.com", passwordEncoder.encode("password"),Role.ROLE_ADMIN);
        userService.save(admin);
      }
    }

}
