/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.adoptmate.enumerations;

/**
 *
 * @author Mario Nevado
 */
public enum Breed {
    DOG(1), CAT(2);
    
    private final int code;
    
    private Breed (int code){
        this.code = code;
    }
    
    public int getValue() {
        return code;
    }
    
    
}
