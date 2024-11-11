package com.adoptmate.enumerations;

public enum Issuer {
    USER(0), SHELTER(1);
    private final int code;

    private Issuer(int code){
        this.code = code;
    }

    public int getValue(){
        return code;
    }


    @Override
    public String toString() {
        return switch (code) {
            case 1 -> "Usuario";
            case 2 -> "Refugio";
            default -> "N/A";
        };
    }
}
