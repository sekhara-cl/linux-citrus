package citrusleaf.contact_form_submission.controllers;


import citrusleaf.contact_form_submission.models.Contact;


import citrusleaf.contact_form_submission.models.ContactResponse;
import citrusleaf.contact_form_submission.services.ContactService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class ContactController {
    @Autowired
    private ContactService contactService;


    @PostMapping("/")
    public ContactResponse storeContract(@RequestBody @Valid Contact contact) throws IOException, MessagingException {
        ContactResponse response = new ContactResponse();
        response.setHttpCode(201);
        response.setStatus("Create");
        final List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contactService.saveContactForm(contact));
        response.setContacts(Optional.of(contactList));
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><body>");
        sb.append("Email : &nbsp;&nbsp;");sb.append(contact.getEmail());
        sb.append("<br/>");
        sb.append("Phone : &nbsp;&nbsp;");
        sb.append(contact.getPhone());
        sb.append("<br/>");
        sb.append("Skype ID : &nbsp;&nbsp;");
        sb.append(contact.getSkypeId());
        sb.append("<br/>");
        sb.append("Looking for : &nbsp;&nbsp;");
        sb.append(contact.getLookingFor());
        sb.append("<br/>");
        sb.append("Details : &nbsp;&nbsp;");
        sb.append(contact.getDetails());
        sb.append("<br/>");
        sb.append("</body></head></html>");
        contactService.sendingMail("b.sekhar@citrusleaf.in",
                "sales@citrusleaf.in", " Contact form submitted", sb.toString());
        return response;
    }

    //sales@citrusleaf.in
    @GetMapping("/{id}")
    public ContactResponse getForm(@PathVariable("id") Integer id) {
        ContactResponse response = new ContactResponse();
        response.setHttpCode(200);
        response.setStatus("OK");
        response.setContacts(Optional.of(contactService.getFormByID(id).stream().collect(Collectors.toList())));
        return response;


    }

    @PutMapping("/")
    public ContactResponse update(@RequestBody @Valid Contact contactForm) {
        ContactResponse response = new ContactResponse();
        response.setHttpCode(200);
        response.setStatus("OK");
        response.setContacts(Optional.of(Arrays.asList(contactService.formUpdate(contactForm))));
        return response;

    }

    @DeleteMapping("/{id}")
    public ContactResponse deleteById(@PathVariable Integer id) {
        ContactResponse response = new ContactResponse();
        response.setHttpCode(200);
        response.setStatus("OK");
        response.setContacts(Optional.of(Arrays.asList(contactService.deleteFormById(id))));
        return response;

    }
}


