package cokoball.back.domain.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
}
