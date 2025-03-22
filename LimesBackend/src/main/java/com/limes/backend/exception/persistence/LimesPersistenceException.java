/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.exception.persistence;


public class LimesPersistenceException extends Throwable {
    public LimesPersistenceException(){
    }
    
    public LimesPersistenceException(String message){
        super(message);
    }
}
