package com.sandeep.guestbook.api.entity;

import javax.persistence.*;
import java.util.Date;

public class Guest {

    private Long id;

    private String guestName;

    public Long getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setId(Long id) {

        this.id = id;
    }

    private String comment;

    private Date creationDateTime;

}
