package com.adoptmate.models;

import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.PET.TABLE)
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.PET.NAME, nullable = false)
    private String name;
    @Column(name = SQLConfiguration.COLUMN_NAMES.PET.SEX, nullable = false)
    private char sex;
    @Column(name = SQLConfiguration.COLUMN_NAMES.PET.AGE, nullable = false)
    private int age;
    @Column(name = SQLConfiguration.COLUMN_NAMES.PET.DESCRIPTION)
    private String description;
    @Column(name=SQLConfiguration.COLUMN_NAMES.PET.SHELTER, nullable = false)
    private String shelter;
    @Column(name=SQLConfiguration.COLUMN_NAMES.PET.BREED, nullable = false)
    private String breed;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name =SQLConfiguration.COLUMN_NAMES.BREED.ID)
    private Breed breedFinal;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.SHELTER.ID)
    private Shelter shelterFinal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = SQLConfiguration.COLUMN_NAMES.PET.MAPPED_BY)
    private List<Image> images = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = SQLConfiguration.COLUMN_NAMES.USERS_PETS,
            joinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.PET.ID),
            inverseJoinColumns = @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID))
    private List<User> likedUsers = new ArrayList<>();


    public Pet() {
    }

    public Pet(String name, char sex, int age, String shelter, String breed) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.shelter = shelter;
        this.breed = breed;
    }

    public Pet(String name, char sex, int age, Breed breedFinal, Shelter shelterFinal, List<Image> images) {
        this.name = name;
        this.sex = Character.toUpperCase(sex);
        this.age = age;
        this.breedFinal = breedFinal;
        this.shelterFinal = shelterFinal;
        this.images = images;
    }

    public java.util.List<Image> getImages() {
        return images;
    }

    public java.util.List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(java.util.List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Breed getBreedFinal() {
        return breedFinal;
    }

    public void setBreedFinal(Breed breedFinal) {
        this.breedFinal = breedFinal;
    }

    public Shelter getShelterFinal() {
        return shelterFinal;
    }

    public void setShelterFinal(Shelter shelterFinal) {
        this.shelterFinal = shelterFinal;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Pet{" + "name=" + name + ", sex=" + sex + ", age=" + age + ", description=" + description + ", breed=" + breedFinal + '}';
    }

}
