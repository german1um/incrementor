package com.german1um.incrementor.controller;

import com.german1um.incrementor.service.IIncrementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("number")
public class NumberController {

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
    public void setMaximumValue(
            @RequestBody
            int maximumValue
    ) {
        incrementor.setMaximumValue(maximumValue);
    }

}
