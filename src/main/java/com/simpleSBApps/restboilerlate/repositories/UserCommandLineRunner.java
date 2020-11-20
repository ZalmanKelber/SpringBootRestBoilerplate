package com.simpleSBApps.restboilerlate.repositories;

import com.simpleSBApps.restboilerlate.models.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Mimi", "USER"));
        userRepository.save(new User("Rodolfo", "USER"));
        userRepository.save(new User("Musetta", "USER"));
        userRepository.save(new User("Marcello", "USER"));
        userRepository.save(new User("Benoit", "ADMIN"));

        System.out.println("Admins:");

        for (User user : userRepository.findByRole("ADMIN")) {
            logger.info(user.toString());
        }

        System.out.println("Users:");

        for (User user : userRepository.findByRole("USER")) {
            logger.info(user.toString());
        }
    }
}
