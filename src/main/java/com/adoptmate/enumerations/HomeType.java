package com.adoptmate.enumerations;

public enum HomeType {
    CHALET(0), APARTMENT(1);
    private final int value;

    private HomeType(int value){
        if (value != 0 && value != 1) {
            this.value = -1;
        }else this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return switch (value) {
            case 0 -> "Chalet";
            case 1 -> "Apartamento";
            default -> "N/A";
        };
    }

}
