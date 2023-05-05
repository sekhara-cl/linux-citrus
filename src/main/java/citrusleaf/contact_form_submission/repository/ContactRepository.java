package citrusleaf.contact_form_submission.repository;


import citrusleaf.contact_form_submission.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

    Optional<Contact> findByEmail(String email);
}
