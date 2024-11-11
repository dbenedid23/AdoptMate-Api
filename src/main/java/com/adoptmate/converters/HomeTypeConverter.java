package com.adoptmate.converters;

import com.adoptmate.enumerations.HomeType;
import jakarta.persistence.AttributeConverter;

public class HomeTypeConverter implements AttributeConverter<HomeType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HomeType homeType) {
        return homeType.getValue();
    }

    @Override
    public HomeType convertToEntityAttribute(Integer value) {
        if (value == 0){
            return HomeType.CHALET;
        }else if (value == 1){
            return HomeType.APARTMENT;
        }else return null;
    }
}
