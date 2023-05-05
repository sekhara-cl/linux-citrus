package citrusleaf.contact_form_submission.services;


import citrusleaf.contact_form_submission.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getJwtUserByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, user.getAuthorities());
    }

}