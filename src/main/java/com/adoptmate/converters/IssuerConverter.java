package com.adoptmate.converters;

import com.adoptmate.enumerations.Issuer;
import jakarta.persistence.AttributeConverter;

public class IssuerConverter implements AttributeConverter<Issuer, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Issuer issuer) {
        return issuer.getValue();
    }

    @Override
    public Issuer convertToEntityAttribute(Integer value) {
        return switch (value) {
        case 1 -> Issuer.USER;
        case 2 -> Issuer.SHELTER;
        default -> null;
    };
    }
}
