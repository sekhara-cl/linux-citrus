package citrusleaf.contact_form_submission.services;

import citrusleaf.contact_form_submission.auth.JsonObjectAuthenticationFilter;
import citrusleaf.contact_form_submission.models.User;
import citrusleaf.contact_form_submission.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
//UserService
public class UserService {


    private JsonObjectAuthenticationFilter jsonObjectAuthenticationFilter;

    @Autowired
    private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        return userRepository.save(user);

    }

    public User registerUser(User user1) {
        User u = new User();
        u.setUsername(user1.getUsername());
        u.setEmail(user1.getEmail());
        u.setPassword(passwordEncoder.encode(user1.getPassword()));
        u.setRole(user1.getRole());
        u.setEnabled(user1.isEnabled());
        return userRepository.save(u);

    }

    public Optional<User> findJwtUserByEmail(String email) {

        return userRepository.findJwtUserByEmail(email);
    }

    public User getJwtUserByEmail(String email) {
        return userRepository.findJwtUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email!"));
    }

    public User getJwtUserByUsername(String username) {
        return (User) userRepository.findJwtUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found by username!"));
    }


}