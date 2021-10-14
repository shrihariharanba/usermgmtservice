package org.usermgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usermgmt.config.ProductException;
import org.usermgmt.model.User;
import org.usermgmt.service.LoginService;
import org.usermgmt.service.UserService;
import org.usermgmt.util.ExceptionType;
import org.usermgmt.util.SecurityUtil;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    SecurityUtil securityUtil;

    /**
     * Method validates the login details
     * @param emailAddress
     * @param password
     */
    @Override
    public void validate(String emailAddress, String password) {
        User user = userService.findUserByEmailAddress(emailAddress);
        if (user != null) {
            if (!securityUtil.check(password, user.getPassword())) {
                throw new ProductException(ExceptionType.AUTHORIZATION.getCode(),
                        ExceptionType.AUTHORIZATION.getType(), "Password not matched");
            }
        } else {
            throw new ProductException(ExceptionType.USER_VALIDATION.getCode(),
                    ExceptionType.USER_VALIDATION.getType(),
                    "No User found for the " + emailAddress);
        }
        userService.updateLastLogin(user);
    }
}
