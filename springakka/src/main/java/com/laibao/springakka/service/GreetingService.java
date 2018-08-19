package com.laibao.springakka.service;

import org.springframework.stereotype.Service;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */
@Service
public class GreetingService {
    public String greet(String name) {
        return "Hello, " + name;
    }
}
