package com.codiecon.hungerry.model.enums;

public enum ErrorCode {

    MEMBER_EXISTS("Memeber exists"),
    MAIL_NOT_SENT("Mail not sent"),
    INVALID_MEMBER("Invalid member"),
    INVALID_COLLECT_REQUEST("Invalid collectRequest"),
    INVALID_REQUEST("Invalid request"),
    DONATE_ENTITY_NOT_NULL("Donate Food cannot be null"),
    MEMBER_DOES_NOT_EXIST("Memeber does not exist"),
    INVALID_CREDENTIALS("invalid credentials");

    private String errorMessage;

    ErrorCode(String s) {
        this.errorMessage = s;
    }

    public static String getErrorMessage(ErrorCode errorCode) {
        return errorCode.errorMessage;
    }

}
