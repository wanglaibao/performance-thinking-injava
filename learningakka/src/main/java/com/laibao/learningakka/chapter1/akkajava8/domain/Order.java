package com.laibao.learningakka.chapter1.akkajava8.domain;

import com.laibao.learningakka.chapter1.akkajava8.enums.CoffeeType;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class Order {

    public final CoffeeType type;

    private Order(final CoffeeType type) {
        this.type = type;
    }

    public static Order of(CoffeeType type) {
        return new Order(type);
    }

    @Override
    public String toString() {
        return type.name();
    }
}
