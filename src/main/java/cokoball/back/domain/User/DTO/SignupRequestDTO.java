package cokoball.back.domain.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SignupRequestDTO {
    private String username;
    private String password;
    private String passwordConfirm;
}
