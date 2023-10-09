package book.online.dto;

import book.online.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Password(password = "password", repeatPassword = "repeatPassword")
public class UserRegistrationRequestDto {
    @NotNull(message = "Field email should not be empty")
    @Email
    private String email;
    @NotNull(message = "Field password should not be empty")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
    @NotNull(message = "Field repeat password should not be empty")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String repeatPassword;
    @NotNull(message = "Field First Name should not be empty")
    private String firstName;
    @NotNull(message = "Field Last Name should not be empty")
    private String lastName;
    private String shippingAddress;
}
