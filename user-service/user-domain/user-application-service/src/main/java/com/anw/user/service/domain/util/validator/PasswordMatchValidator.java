package com.anw.user.service.domain.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

  private String passwordFieldName;
  private String passwordMatchFieldName;

  @Override
  public void initialize(PasswordMatch constraintAnnotation) {
    passwordFieldName = constraintAnnotation.passwordField();
    passwordMatchFieldName = constraintAnnotation.passwordConfirmationField();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    try {
      Class<?> clazz = value.getClass();
      Field passwordField = clazz.getDeclaredField(passwordFieldName);
      Field passwordMatchField = clazz.getDeclaredField(passwordMatchFieldName);
      passwordField.setAccessible(true);
      passwordMatchField.setAccessible(true);

      String password = (String) passwordField.get(value);
      String passwordMatch = (String) passwordMatchField.get(value);

      return password != null && password.equals(passwordMatch);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      log.error("PasswordMatchValidator error", e);
      return false;
    }
  }
}
