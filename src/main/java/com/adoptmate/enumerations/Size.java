package com.adoptmate.enumerations;

public enum Size {
    SMALL(0), MEDIUM(1), LARGE(2), XXL(3);
    private final int code;

    private Size(int code){
        this.code = code;
    }

    public int getValue() {
        return code;
    }

    public static Size getSize(String name){
        name = name.toLowerCase();
        switch (name) {
            case "pequeño":
                return Size.SMALL;
            case "mediano":
                return Size.MEDIUM;
            case "grande":
                return Size.LARGE;
            case "xxl":
                return Size.XXL;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (code) {
            case 0:
                return "Pequeño";
            case 1:
                return "Mediano";
            case 2:
                return "Grande";
            default:
                return "XXL";
        }
    }
}
