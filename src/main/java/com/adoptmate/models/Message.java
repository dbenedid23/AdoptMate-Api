package com.adoptmate.models;

import com.adoptmate.enumerations.Issuer;
import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.MESSAGE.TABLE)
public class Message  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.MESSAGE.TIME)
    private LocalDateTime time;
    @Column(name = SQLConfiguration.COLUMN_NAMES.MESSAGE.TEXT)
    private String text;
    @Column(name = SQLConfiguration.COLUMN_NAMES.MESSAGE.ISSUER)
    private Issuer issuer;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Shelter shelter;

    public Message() {
    }

    public Message(String text) {
        this.time = LocalDateTime.now();
        this.text = text;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
