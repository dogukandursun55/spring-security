package com.example.authservice.authservice.validation.PhoneNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String>{
    private static final String PHONE_NUMBER_FORMAT_REGEX = "^+905\\d{9}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
        
    }
    
}
