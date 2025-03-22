/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.CommonConstants;
import com.limes.backend.constants.MessageConstants;
import com.limes.backend.exception.jwt.LimesInvalidJwtTokenException;
import com.limes.backend.security.tokenization.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractController {

    protected String extractEmailFromToken(String token) throws LimesInvalidJwtTokenException {
        token = token.replace(CommonConstants.BEARER_CONSTANT, "");
        try {
            if (JwtService.isTokenExpired(token)) {
                throw new LimesInvalidJwtTokenException(MessageConstants.LOG_TOKEN_IS_VALID_BUT_EXPIRED);
            } else {
                return JwtService.extractEmail(token);
            }
        } catch (ExpiredJwtException | SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException ex) {
            log.error(ex.getLocalizedMessage());
            throw new LimesInvalidJwtTokenException(ex.getLocalizedMessage());
        }
    }
}
