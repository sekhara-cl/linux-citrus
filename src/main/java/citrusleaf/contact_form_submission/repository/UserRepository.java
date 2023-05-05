package citrusleaf.contact_form_submission.repository;


import citrusleaf.contact_form_submission.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//UserRepository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);


    Optional<User> findJwtUserByEmail(String email);

    Optional<Object> findJwtUserByUsername(String username);
}