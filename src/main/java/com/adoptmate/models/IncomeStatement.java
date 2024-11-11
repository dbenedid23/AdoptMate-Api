package com.adoptmate.models;

import com.adoptmate.tools.SQLConfiguration;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name= SQLConfiguration.COLUMN_NAMES.INCOME_STATEMENT.TABLE)
public class IncomeStatement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = SQLConfiguration.COLUMN_NAMES.INCOME_STATEMENT.FILE, nullable = false)
    private byte[] file;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = SQLConfiguration.COLUMN_NAMES.USER.ID)
    private User user;

    public IncomeStatement() {
    }

    public IncomeStatement(byte[] file) {
        this.file = file;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "IncomeStatement{" +
                "file=" + Arrays.toString(file) +
                '}';
    }
}
