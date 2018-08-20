package com.laibao.learningakka.chapter1.akkajava8.domain;

import com.laibao.learningakka.chapter1.akkajava8.enums.CoffeeType;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class Coffee {

    public static final CoffeeType[] coffeeTypes = CoffeeType.values();

    public final CoffeeType coffeeType;

    private Coffee(final CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public static Coffee ofType(CoffeeType coffeeType) {
        return new Coffee(coffeeType);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "coffeeType=" + coffeeType +
                '}';
    }
}
