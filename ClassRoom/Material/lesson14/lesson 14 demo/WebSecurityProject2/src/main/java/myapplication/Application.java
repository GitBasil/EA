package myapplication;

import myapplication.security.userservice.User;
import myapplication.security.userservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder ;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        userRepo.save(new User("user", passwordEncoder.encode("user"), "user"));
        userRepo.save(new User("admin", passwordEncoder.encode("admin"), "admin"));
    }
}
