package com.laibao.learningakka.chapter2;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Status;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class JavaPongActor extends AbstractActor{
    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        logger.info("Receive createReceive "+ JavaPongActor.class.getName());
        // pattern patching of scala language
        return receiveBuilder()
                        .matchEquals("Ping", str -> {
                            System.out.println("str : "+str);
                            sender().tell("Pong", ActorRef.noSender());
                        })
                        .matchAny(o -> sender().tell(new Status.Failure(new Exception("unknown message")), self()))
                        .build();
    }
}
