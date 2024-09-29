package cokoball.back.domain.User.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private LocalDateTime createdAt;
}
