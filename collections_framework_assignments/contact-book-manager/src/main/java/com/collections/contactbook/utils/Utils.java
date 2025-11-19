package com.collections.contactbook.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils  {
    private static final String _emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String _phoneNumberRegex = "^\\d{10}$";
    Utils(){}

    public boolean validateEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(_emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher match = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return match.matches();
    }

    public boolean validatePhone(String phone){
        Pattern VALID_PHONE_REGEX = Pattern.compile(_phoneNumberRegex);
        Matcher match = VALID_PHONE_REGEX.matcher(phone);
        return match.matches();
    }
}
