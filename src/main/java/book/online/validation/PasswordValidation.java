package book.online.validation;

import book.online.dto.user.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidation
        implements ConstraintValidator<Password, UserRegistrationRequestDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(Password constraintAnnotation) {
        password = constraintAnnotation.password();
        repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRegistrationRequestDto userRegistrationRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(userRegistrationRequestDto)
                .getPropertyValue(this.password);
        Object repeatPassword = new BeanWrapperImpl(userRegistrationRequestDto)
                .getPropertyValue(this.repeatPassword);
        return password != null && password.equals(repeatPassword);
    }
}
