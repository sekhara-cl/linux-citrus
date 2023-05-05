package citrusleaf.contact_form_submission.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "_contact")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Full Name")
    @Pattern(regexp="^[a-zA-Z]{2,200}$")
    private String fullName;
    @Email
    @Column(name = "Email")
    private String email;
    @Size(min=6,max = 50)
    @Column(name = "Skype Id")
    private String skypeId;
    @Column(name = "Looking for")
    @Pattern(regexp = "^[a-zA-Z ]{4,100}$",message = "Invalid Input")
    private String lookingFor;
    @Pattern(regexp ="^\\d{10}$" )
    private String phone;
    @Column(name ="Details")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{4,500}$")
    private String details;

    private Boolean recaptcha;



}
