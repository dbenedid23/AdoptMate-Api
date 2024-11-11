/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adoptmate.converters;

import com.adoptmate.enumerations.Specie;
import jakarta.persistence.AttributeConverter;


/**
 *
 * @author Mario Nevado
 */
public class SpecieConverter implements AttributeConverter<Specie, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Specie specie) {
        return specie.getValue();
    }

    @Override
    public Specie convertToEntityAttribute(Integer value) {
        return switch (value) {
            case 1 -> Specie.DOG;
            case 2 -> Specie.CAT;
            default -> Specie.NA;
        };
    }
}
