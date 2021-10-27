package org.usermgmt.util;

import org.apache.commons.lang.StringUtils;
import org.usermgmt.config.ProductException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static String PASSWORD_MATCH_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$";
    private static String EMAIL_MATCH_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

    public static void validatePassword(String password) {
        if(StringUtils.isBlank(password)){
            throw new ProductException(ExceptionType.VALIDATION.getCode(),ExceptionType.VALIDATION.getType(),"Password should not be blank");
        }
        Pattern p = Pattern.compile(PASSWORD_MATCH_REGEX);
        Matcher m = p.matcher(password);
//        if(!m.matches()) {
//            throw new ProductException(ExceptionType.PASSWORD_MATCH.getCode(),ExceptionType.PASSWORD_MATCH.getType(),"Password should match the constrains");
//        }
    }

    public static void validateEmail(String email) {
        if(StringUtils.isBlank(email)) {
            throw new ProductException(ExceptionType.VALIDATION.getCode(),ExceptionType.VALIDATION.getType(),"Email should not be blank");
        }
        Pattern pattern = Pattern.compile(EMAIL_MATCH_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ProductException(ExceptionType.VALIDATION.getCode(),ExceptionType.VALIDATION.getType(),"Not a valid email");
        }
    }
}
