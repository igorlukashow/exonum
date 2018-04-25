package com.bitfury.utils.common;

public class SessionInfo {

    private String email_addr;
    private long email_timestamp;
    private String sid_token;

    public SessionInfo(String email_addr, long email_timestamp, String sid_token) {
        this.email_addr = email_addr;
        this.email_timestamp = email_timestamp;
        this.sid_token = sid_token;
    }

    public String getSid_token() {
        return sid_token;
    }

    public String getEmail_addr() {
        return email_addr;
    }
}
