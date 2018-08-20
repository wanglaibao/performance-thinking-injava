package com.laibao.learningakka.chapter1.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.laibao.learningakka.chapter1.akkademy.messages.Greeting;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class PrinterActor extends AbstractActor {

    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    public PrinterActor() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> {
                    logger.info(greeting.message);
                    System.out.println("greeting.message : " + greeting.message);
                })
                .build();
    }

    //#printer-messages
    static public Props props() {
        return Props.create(PrinterActor.class, () -> new PrinterActor());
    }

    public String sayHello(String message) {
        //System.out.println("hello, "+message);
        return "hello, "+message;
    }
}
