package org.usermgmt.util;

/**
 * Handle different type of exception
 */
public enum ExceptionType {
    AUTHORIZATION(401, "Unauthorized"),
    PASSWORD_MATCH(403, "Forbidden"),
    USER_VALIDATION(404, "Resource Not Found"),
    USER_CONFLICT(409, "User already present");

    private final Integer code;
    private final String type;

    ExceptionType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
