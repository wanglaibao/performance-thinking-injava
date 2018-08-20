package com.laibao.simpleakka.event;

import java.io.Serializable;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class Event implements Serializable{

    private final String data;

    private final String uuid;

    public Event(String data, String uuid) {
        this.data = data;
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Event{" +
                "data='" + data + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
