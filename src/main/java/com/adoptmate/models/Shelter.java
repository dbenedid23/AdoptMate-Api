package com.adoptmate.models;

import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.SHELTER.TABLE)
public class Shelter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.CIF, nullable = false, unique = true)
    private String cif;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.NAME, nullable = false, unique = true)
    private String name;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.PASSWORD, nullable = false)
    private String password;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.LOCATION, nullable = false)
    private String location;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.PHONE, nullable = false)
    private int phone;
    @Column(name = SQLConfiguration.COLUMN_NAMES.SHELTER.DESCRIPTION)
    private String description;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = SQLConfiguration.COLUMN_NAMES.SHELTER.MAPPED_BY)
    private List<Pet> pets = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = SQLConfiguration.COLUMN_NAMES.SHELTER.MAPPED_BY)
    private List<Message> messages = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.REACHED_USERS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.SHELTER.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID))
    private List<User> reachedUsers = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.MATCHED_USERS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.SHELTER.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID))
    private List<User> matchedUsers = new ArrayList<>();

    public Shelter() {
    }

    public Shelter(String cif, String name, String password, String location, int phone) {
        this.cif = cif;
        this.name = name;
        this.password = password;
        this.location = location;
        this.phone = phone;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getReachedUsers() {
        return reachedUsers;
    }

    public void setReachedUsers(List<User> reachedUsers) {
        this.reachedUsers = reachedUsers;
    }

    public List<User> getMatchedUsers() {
        return matchedUsers;
    }

    public void setMatchedUsers(List<User> matchedUsers) {
        this.matchedUsers = matchedUsers;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
