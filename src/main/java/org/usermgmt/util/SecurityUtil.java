package org.usermgmt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Password Encryption and decryption
 */
@Component("securityUtil")
public class SecurityUtil {

    @Autowired
    public PasswordEncoder ENCODER;

    /**
     * Method to encode the password
     * @param password
     * @return encoded hash
     */
    public String encode(String password) {
        return ENCODER.encode(password);
    }

    /**
     * Method to validate the password
     * @param password
     * @param hash
     * @return boolean result
     */
    public boolean check(String password, String hash) {
        return ENCODER.matches(password, hash);
    }
}
