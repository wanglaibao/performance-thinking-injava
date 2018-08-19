package com.laibao.springakka.model;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */
public class Greet {
    private final String name;

    public Greet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
