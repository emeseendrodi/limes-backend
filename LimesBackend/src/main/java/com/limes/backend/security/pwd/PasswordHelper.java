/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.security.pwd;

import com.limes.backend.exception.persistence.LimesPasswordHelperException;
import jakarta.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Mate Forster
 */
@Slf4j
public class PasswordHelper {

    private final static byte[] SALT = "P3ret&ty1S$cureHWsh1".getBytes();
    private final static String ALGORITHM = "PBKDF2WithHmacSHA1";

    public String hashPassword(String password) throws LimesPasswordHelperException {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return DatatypeConverter.printHexBinary(factory.generateSecret(spec).getEncoded());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            log.error(ex.getLocalizedMessage());
            throw new LimesPasswordHelperException("KeySpecFactory configuration error!");
        }
    }
}
