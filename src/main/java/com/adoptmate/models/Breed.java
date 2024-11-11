package com.adoptmate.models;

import com.adoptmate.converters.SizeConverter;
import com.adoptmate.converters.SpecieConverter;
import com.adoptmate.enumerations.Specie;
import com.adoptmate.enumerations.Size;
import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.BREED.TABLE)
public class Breed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Convert(converter = SpecieConverter.class)
    @Column(name = SQLConfiguration.COLUMN_NAMES.BREED.SPECIE, columnDefinition = SQLConfiguration.COLUMN_NAMES.BREED.DEFINITIONS.SPECIE)
    private Specie specie = Specie.NA;
    @Column(name = SQLConfiguration.COLUMN_NAMES.BREED.NAME, unique = true)
    private String name;
    @Column(name = SQLConfiguration.COLUMN_NAMES.BREED.TYPE)
    private String type;
    @Convert(converter = SizeConverter.class)
    @Column(name = SQLConfiguration.COLUMN_NAMES.BREED.SIZE)
    private Size size;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = SQLConfiguration.COLUMN_NAMES.BREED.MAPPED_BY)
    private List<Pet> pets = new ArrayList<>();
    public Breed() {
    }

    public Breed(Specie specie, String name, String type, Size size) {
        this.specie = specie;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public void setSize(Size size) {
        this.size = size;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Breed{" + "name=" + name + ", type=" + type + ", size=" + size + " specie= " + specie + '}';
    }

}
