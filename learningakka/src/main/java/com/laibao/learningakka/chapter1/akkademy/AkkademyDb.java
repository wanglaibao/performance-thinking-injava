package com.laibao.learningakka.chapter1.akkademy;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.laibao.learningakka.chapter1.akkademy.messages.SetRequest;

import java.util.HashMap;
import java.util.Map;

import static akka.japi.pf.UnitMatch.matchAny;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version
 * 1.0
 */
public class AkkademyDb extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    private final Map<String, Object> map = new HashMap<String, Object>();


    private AkkademyDb() {
        //receive(createReceive());
        createReceive();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                        .match(SetRequest.class, message -> {
                                log.info("Received Set request: {}", message);
                                map.put(message.getKey(), message.getValue());
                        })
                .matchAny(o -> log.info("received unknown message: {}", o)).build();
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
