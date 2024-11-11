package com.adoptmate.models;

import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = SQLConfiguration.COLUMN_NAMES.IMAGE.TABLE)
public class Image  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.IMAGE.IMAGE)
    private byte[] image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.PET.ID)
    private Pet pet;

    public Image() {
    }

    public Image(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
