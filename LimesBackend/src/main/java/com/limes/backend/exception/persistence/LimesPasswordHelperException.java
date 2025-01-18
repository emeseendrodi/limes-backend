/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.limes.backend.exception.persistence;

/**
 *
 * @author Mate Forster
 */
public class LimesPasswordHelperException extends Throwable{

    /**
     * Creates a new instance of <code>LimesPasswordHelperException</code>
     * without detail message.
     */
    public LimesPasswordHelperException() {
    }

    /**
     * Constructs an instance of <code>LimesPasswordHelperException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LimesPasswordHelperException(String msg) {
        super(msg);
    }
}
