package com.adoptmate.enumerations;

public enum Specie {
    NA(0), DOG(1), CAT(2);
    private final int code;

    private Specie(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }

    public static Specie getSpecieFromName(String name){
        if (name.equalsIgnoreCase("perro")) return Specie.DOG;
        else if (name.equalsIgnoreCase("gato"))return Specie.CAT;
        else return Specie.NA;
    }

    @Override
    public String toString() {
        return switch (code) {
            case 1 -> "Perro";
            case 2 -> "Gato";
            default -> "N/A";
        };
    }
}
