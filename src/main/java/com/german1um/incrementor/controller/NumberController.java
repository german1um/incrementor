package com.german1um.incrementor.controller;

import com.german1um.incrementor.exception.MaximumValueBelowZeroException;
import com.german1um.incrementor.service.IIncrementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("number")
public class NumberController {

    private final Logger logger = LoggerFactory.getLogger(NumberController.class);
    private final IIncrementor incrementor;

    public NumberController(@Autowired IIncrementor incrementor) {
        this.incrementor = incrementor;
    }

    @GetMapping
    public int getNumber() {
        return incrementor.getNumber();
    }

    @PostMapping("increment")
    public void incrementNumber() {
        incrementor.incrementNumber();
    }

    @PostMapping("setMaximumValue")
    public ResponseEntity<String> setMaximumValue(
            @RequestBody
            int maximumValue
    ) {
        try {
            incrementor.setMaximumValue(maximumValue);
        } catch(MaximumValueBelowZeroException e) {
            logger.info("Value below zero passed to /setMaximumValue", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
