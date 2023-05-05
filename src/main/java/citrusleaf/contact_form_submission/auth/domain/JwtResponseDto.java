package citrusleaf.contact_form_submission.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponseDto {

    private String token;


    public static JwtResponseDto of(String token) {
        return new JwtResponseDto(token);
    }

}