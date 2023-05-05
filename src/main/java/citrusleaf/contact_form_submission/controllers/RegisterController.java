package citrusleaf.contact_form_submission.controllers;

import citrusleaf.contact_form_submission.models.User;
import citrusleaf.contact_form_submission.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService jwtUserService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User jwtUser){

        return new ResponseEntity<>(jwtUserService.registerUser(jwtUser), HttpStatus.CREATED);

    }
}
