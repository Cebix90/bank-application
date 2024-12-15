package com.mkdev.springboot.bankapplicationbackend.security.password;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PasswordConstraintValidatorTests {
    private final PasswordConstraintValidator validator = new PasswordConstraintValidator();

    @Test
    void shouldPassWhenPasswordIsValid() {
        String password = "StrongPassword1!";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isTrue();
    }

    @Test
    void shouldFailForPasswordWithoutUpperCase() {
        String password = "weakpassword1!";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isFalse();
    }

    @Test
    void shouldFailForPasswordWithoutSpecialCharacter() {
        String password = "Weakpassword1";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isFalse();
    }

    @Test
    void shouldFailForTooShortPassword() {
        String password = "Short1!";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isFalse();
    }

    @Test
    void shouldFailForTooLongPassword() {
        String password = "ThisPasswordIsWayTooLong123!";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isFalse();
    }

    @Test
    void shouldFailForPasswordWithWhitespace() {
        String password = "Weak Password1!";

        boolean isValid = validator.isValid(password, null);

        assertThat(isValid).isFalse();
    }

    @Test
    void shouldFailWhenPasswordIsNull() {
        String password = null;
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);

        boolean isValid = validator.isValid(password, context);

        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("Password cannot be null");
    }

    @Test
    void shouldFailWhenPasswordIsInvalid() {
        String password = "weak";
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);

        boolean isValid = validator.isValid(password, context);

        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate(contains("Password must contain"));
    }
}
