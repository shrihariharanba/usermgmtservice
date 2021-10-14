package org.usermgmt.config;

public class ProductException extends RuntimeException {

    private Integer code;
    private String type;
    private String message;
    private Throwable cause;

    public ProductException(Integer code, String type, String message) {
        super(message);
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public ProductException(Integer code, String type, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.type = type;
        this.message = message;
        this.cause = cause;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
