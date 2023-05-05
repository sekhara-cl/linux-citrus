package citrusleaf.contact_form_submission.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class ContactResponse {
    public int httpCode;
    public String status;
   public Optional<List<Contact>> contacts;
}
