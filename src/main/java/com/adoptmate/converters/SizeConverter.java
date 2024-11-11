package com.adoptmate.converters;

import com.adoptmate.enumerations.Size;
import jakarta.persistence.AttributeConverter;

public class SizeConverter implements AttributeConverter<Size, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Size size) {
        return size.getValue();
    }

    @Override
    public Size convertToEntityAttribute(Integer value) {
        return switch (value) {
            case 0 -> Size.SMALL;
            case 1 -> Size.MEDIUM;
            case 2 -> Size.LARGE;
            case 3 -> Size.XXL;
            default -> null;
        };
    }
}
