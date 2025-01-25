/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.limes.backend.enums;

/**
 *
 * @author Mate Forster
 */
public enum TestTypeEnum {
    FIRST("Próbadolgozat I.","FIRST"),
    SECOND("Próbadolgozat II.","SECOND"),
    LAST("Összevont Próbadolgozat","LAST");

    public final String value;
    public final String label;
    
    private TestTypeEnum(String value,String label) {
        this.label = label;
        this.value = value;
    }
}
