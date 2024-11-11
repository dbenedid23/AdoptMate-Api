/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adoptmate.tools;

import com.adoptmate.converters.SizeConverter;
import com.adoptmate.converters.SpecieConverter;
import com.adoptmate.enumerations.Size;
import com.adoptmate.enumerations.Specie;
import com.adoptmate.models.Breed;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mario Nevado
 */
public class Configuration {

    private static final File BREEDS_FILE = new File("DW/breeds.csv");
    private static final String CSV_CHAR = ";";
    private final SizeConverter sizeConverter = new SizeConverter();
    private final SpecieConverter specieConverter = new SpecieConverter();
    //private final int TRUE_VALUE = 1, FALSE_VALUE = 0;

    public Configuration() {
        readCSV();
    }

    private void readCSV() {
        String str;
        String[] fields;
        Breed breed;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession(); 
                BufferedReader br = new BufferedReader(new FileReader(Configuration.BREEDS_FILE))) {
            br.readLine();
            while ((str = br.readLine()) != null) {
                fields = str.split(CSV_CHAR);
                breed = new Breed(specieConverter.convertToEntityAttribute(Specie.getSpecieFromName(fields[0]).getValue()),
                        fields[1], fields[2], sizeConverter.convertToEntityAttribute(Size.getSize(fields[3]).getValue()));
                if (!ignore(breed.getName())) {
                    tx = session.beginTransaction();
                    session.persist(breed);
                    tx.commit();
                }
            }
        } catch (IOException io) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    private boolean ignore(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Breed> cQuery = cb.createQuery(Breed.class);
            Root<Breed> root = cQuery.from(Breed.class);
            cQuery.where(cb.equal(root.get("name"), name));
            Query<Breed> query = session.createQuery(cQuery);
            return query.getSingleResult() != null;
        } catch (NoResultException ignored) {
        }
        return false;
    }
}
