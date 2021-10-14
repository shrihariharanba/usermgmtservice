package org.usermgmt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("securityUtil")
public class SecurityUtil {

    @Autowired
    public PasswordEncoder ENCODER;

    public String encode(String password) {
        return ENCODER.encode(password);
    }

    public boolean check(String password, String hash) {
        return ENCODER.matches(password, hash);
    }
}
