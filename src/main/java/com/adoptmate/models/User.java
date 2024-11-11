package com.adoptmate.models;

import com.adoptmate.enumerations.HomeType;
import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.USER.TABLE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.USER.NAME, unique = true, nullable = false)
    private String name;
    @Column(name = SQLConfiguration.COLUMN_NAMES.USER.PASSWORD, nullable = false)
    private byte[] password;
    @Column(name = SQLConfiguration.COLUMN_NAMES.USER.ZIP_CODE)
    private String zipCode;
    @Column(name=SQLConfiguration.COLUMN_NAMES.USER.PHONE, nullable = false)
    int phone;
    @Column(name = SQLConfiguration.COLUMN_NAMES.USER.DESCRIPTION)
    private String description;
    @Column(name=SQLConfiguration.COLUMN_NAMES.USER.HOME)
    private HomeType home;
    @Column(name=SQLConfiguration.COLUMN_NAMES.USER.PETS)
    private Boolean pets;
    @Column(name=SQLConfiguration.COLUMN_NAMES.USER.KIDS)
    private Boolean kids;
    @Column(name= SQLConfiguration.COLUMN_NAMES.USER.PROFILE_IMAGE)
    private byte[] profileImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = SQLConfiguration.COLUMN_NAMES.USER.MAPPED_BY, fetch = FetchType.EAGER)
    private List<IncomeStatement> statements;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = SQLConfiguration.COLUMN_NAMES.USER.MAPPED_BY)
    private List<Message> messages = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.REACHED_USERS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.SHELTER.ID))
    private List<Shelter> reachedShelters = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.REACHED_USERS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.SHELTER.ID))
    private List<Shelter> matchedShelters = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.USERS_PETS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.PET.ID))
    private List<Pet> likedPets = new ArrayList<>();
    public User() {
    }

    public User(String name, String password, int phone) {
        this.name = name;
        this.password = password.getBytes();
        this.phone = phone;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password.getBytes();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HomeType getHome() {
        return home;
    }

    public void setHome(HomeType home) {
        this.home = home;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getKids() {
        return kids;
    }

    public void setKids(Boolean kids) {
        this.kids = kids;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public List<IncomeStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<IncomeStatement> statements) {
        this.statements = statements;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Shelter> getReachedShelters() {
        return reachedShelters;
    }

    public void setReachedShelters(List<Shelter> reachedShelters) {
        this.reachedShelters = reachedShelters;
    }

    public List<Shelter> getMatchedShelters() {
        return matchedShelters;
    }

    public void setMatchedShelters(List<Shelter> matchedShelters) {
        this.matchedShelters = matchedShelters;
    }

    public List<Pet> getLikedPets() {
        return likedPets;
    }

    public void setLikedPets(List<Pet> likedPets) {
        this.likedPets = likedPets;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
