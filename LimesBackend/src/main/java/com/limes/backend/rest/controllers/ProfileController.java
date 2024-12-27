/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@RestController
public class ProfileController {

 protected static final Logger logger = LogManager.getLogger();
 
    @GetMapping("/profile/data/{username}")
    public String getProfileDataForUser(@PathVariable String username) {
        logger.info(String.format("Request received for user: %s",username));
        return username;
    }

}
