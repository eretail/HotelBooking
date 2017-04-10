package com.eretailservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

/**
 * @author yinchun
 */
@RestController
public class TestController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test() {
        throw new ConstraintViolationException("error", Collections.emptySet());
    }
}
