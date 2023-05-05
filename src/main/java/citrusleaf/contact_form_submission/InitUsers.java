package citrusleaf.contact_form_submission;



import citrusleaf.contact_form_submission.models.Role;
import citrusleaf.contact_form_submission.models.User;
import citrusleaf.contact_form_submission.repository.UserRepository;
import citrusleaf.contact_form_submission.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUsers implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findJwtUserByEmail("admin@test.com").isEmpty()) {
            User u = userService.save(User.builder()
                    .username("Admin")
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(Set.of(Role.ROLE_ADMIN))
                    .build());
            u.setEnabled(true);
            userRepository.save(u);
        }
        if (userService.findJwtUserByEmail("user@test.com").isEmpty()) {
            User u = userService.save(User.builder()
                    .username("User")
                    .email("user@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(Set.of(Role.ROLE_USER))
                    .build());
            u.setEnabled(true);
            userRepository.save(u);
        }
    }

}