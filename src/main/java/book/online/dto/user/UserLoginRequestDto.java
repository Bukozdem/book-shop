package book.online.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotNull(message = "Field email should not be empty")
    @Email
    private String email;
    @NotNull(message = "Field password should not be empty")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
}
