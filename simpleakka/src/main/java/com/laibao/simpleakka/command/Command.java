package com.laibao.simpleakka.command;

import java.io.Serializable;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class Command implements Serializable {

    private final String data;

    public Command(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Command{" +
                "data='" + data + '\'' +
                '}';
    }
}
