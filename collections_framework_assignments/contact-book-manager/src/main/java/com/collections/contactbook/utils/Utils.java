package com.collections.contactbook.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils  {
    private static final String _emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String _phoneNumberRegex = "^\\d{10}$";

    // TODO IMPROVEMENT: Compile Pattern objects once as static final fields for better performance
    // Current code recompiles the pattern on EVERY validation call - inefficient!
    // Add these at class level:
    // private static final Pattern VALID_EMAIL_PATTERN = Pattern.compile(_emailRegex, Pattern.CASE_INSENSITIVE);
    // private static final Pattern VALID_PHONE_PATTERN = Pattern.compile(_phoneNumberRegex);

    Utils(){}

    public boolean validateEmail(String email){
        // ⚠️ PERFORMANCE ISSUE: Pattern.compile() is called every time this method runs
        // This is expensive - pattern compilation should happen once, not on every call
        // TODO FIX: Use pre-compiled pattern: return VALID_EMAIL_PATTERN.matcher(email).matches();
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(_emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher match = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return match.matches();
    }

    public boolean validatePhone(String phone){
        // ⚠️ PERFORMANCE ISSUE: Same as above - pattern compiled on every call
        // TODO FIX: Use pre-compiled pattern: return VALID_PHONE_PATTERN.matcher(phone).matches();
        Pattern VALID_PHONE_REGEX = Pattern.compile(_phoneNumberRegex);
        Matcher match = VALID_PHONE_REGEX.matcher(phone);
        return match.matches();
    }
}
