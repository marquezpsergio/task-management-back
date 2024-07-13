package dev.sergiomarquez.taskmanagementback.services.auth;

import dev.sergiomarquez.taskmanagementback.entities.User;
import dev.sergiomarquez.taskmanagementback.enums.UserRole;
import dev.sergiomarquez.taskmanagementback.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email:admin@test.com}")
    private String adminEmail;

    @Value("${admin.password:admin}")
    private String adminPassword;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail(adminEmail);
            user.setName("admin");
            user.setPassword(passwordEncoder.encode(adminPassword));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            logger.info("Admin account created successfully!");
        } else {
            logger.info("Admin account already exists!");
        }
    }
}
