package com.laibao.simpleakka.actor;

import akka.actor.AbstractLoggingActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.alibaba.fastjson.JSON;
import com.laibao.simpleakka.command.Command;
import com.laibao.simpleakka.event.Event;

import java.util.UUID;

/**
 * SimpleActor receives an instance of Command and emits an Event.
 *
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class SimpleActor extends AbstractLoggingActor {
    private final LoggingAdapter logger = Logging.getLogger(context().system(), this);

    // 继承了 AbstractLoggingActor 后下面的代码就可以不用了
    //LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private SimpleActor() {
        log().info("SimpleActor constructor");
    }

    @Override
    public Receive createReceive() {
        log().info("Received Command: ");
        return receiveBuilder()
                     .match(Command.class,command -> {
                                    String data = command.getData();
                                    Event event = new Event(data, UUID.randomUUID().toString());
                                    System.out.println("data  : " + data);
                                    System.out.println(JSON.toJSONString(event));
                     })
                    .matchEquals("echo",str -> log().info(str.toUpperCase()))
                    .matchAny(o -> logger.info("received unknown message: {}", o))
                    .build();
    }


}
