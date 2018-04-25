package com.bitfury.utils.common;

public class Email {

    private long mail_id;
    private String mail_from;
    private String mail_subject;
    private String mail_excerpt;
    private long mail_timestamp;
    private long mail_read;
    private String mail_date;
    private long att;
    private long mailSize;
    private String mail_body;

    public Email(long mail_id, String mail_from, String mail_subject, String mail_excerpt, long mail_timestamp, long mail_read, String mail_date, long att, long mailSize) {
        this.mail_id = mail_id;
        this.mail_from = mail_from;
        this.mail_subject = mail_subject;
        this.mail_excerpt = mail_excerpt;
        this.mail_timestamp = mail_timestamp;
        this.mail_read = mail_read;
        this.mail_date = mail_date;
        this.att = att;
        this.mailSize = mailSize;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public long getMail_id() {
        return mail_id;
    }

    public void setMail_body(String mail_body) {
        this.mail_body = mail_body;
    }

    public String getMail_body() {
        return mail_body;
    }
}
