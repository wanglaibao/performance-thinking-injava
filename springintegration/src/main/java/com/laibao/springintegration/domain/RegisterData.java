package com.laibao.springintegration.domain;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class RegisterData {

    private final int id;

    private final String name;

    private final String street;

    public RegisterData(int id, String name, String street) {
        this.id = id;
        this.name = name;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    /**
     *  静态工厂方法
     * @param id
     * @param name
     * @param street
     * @return RegisterData
     */
    public static RegisterData of(int id, String name, String street) {
        return new RegisterData(id, name, street);
    }
}
