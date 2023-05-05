package citrusleaf.contact_form_submission.services;

import citrusleaf.contact_form_submission.models.Contact;
import citrusleaf.contact_form_submission.repository.ContactRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public Contact saveContactForm(Contact contact) {
        return contactRepository.save(contact);

    }

    public Optional<Contact> getFormByID(Integer id) {
        Optional<Contact> form = Optional.ofNullable(contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The given Id is not a valid")));
        return form;
    }

    public Contact formUpdate(final Contact contact) {

        Contact contactById = contactRepository.findById(Math.toIntExact(contact.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Provided ID is not a valid"));
        contactById.setId(contact.getId());
        contactById.setEmail(contact.getEmail());
        contactById.setPhone(contact.getPhone());
        contactById.setFullName(contact.getFullName());
        contactById.setLookingFor(contact.getLookingFor());

        Contact updatedContact = contactRepository.save(contactById);
        return updatedContact;

    }

    //Return boolean from here
    public Contact deleteFormById(Integer id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Provided ID is not a valid"));
        ;
        contactRepository.deleteById(id);
        return contact;
    }

    public String sendingMail(String sender, String receiver, String subject, String text) throws IOException, MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(msg);
        return "Mail send successfully";
    }
}

