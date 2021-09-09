package com.tiozao.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="message")
public class MessageEntity {

    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private UserEntity fromUser;

    private String to;
    private String msg;

    private boolean readed;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
