/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.exception.jwt;

/**
 *
 * @author Mate Forster
 */
public class LimesInvalidJwtTokenException extends Throwable {

    /**
     * Creates a new instance of <code>LimesPasswordHelperException</code>
     * without detail message.
     */
    public LimesInvalidJwtTokenException() {
    }

    /**
     * Constructs an instance of <code>LimesPasswordHelperException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LimesInvalidJwtTokenException(String msg) {
        super(msg);
    }
}
