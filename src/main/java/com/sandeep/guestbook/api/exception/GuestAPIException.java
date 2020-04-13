package com.sandeep.guestbook.api.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GuestAPIException extends Exception {

    private HttpStatus m_status;
    private List<String> m_errors;
    private String m_title;

    public GuestAPIException(HttpStatus aStatus, List<String> aErrors, String aTitle) {
        super();
        m_status = aStatus;
        m_errors = aErrors;
        m_title = aTitle;
    }

    public GuestAPIException(HttpStatus aStatus, String aMessage, List<String> aErrors, String aTitle) {
        super(aMessage);
        m_status = aStatus;
        m_errors = aErrors;
        m_title = aTitle;
    }

    public HttpStatus getStatus() {
        return m_status;
    }

    public void setStatus(HttpStatus aStatus) {
        m_status = aStatus;
    }

    public List<String> getErrors() {
        return m_errors;
    }

    public void setErrors(List<String> aErrors) {
        m_errors = aErrors;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String aTitle) {
        m_title = aTitle;
    }
}
