package com.laibao.learningakka.chapter1.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.laibao.learningakka.chapter1.akkademy.messages.Greeting;

/**
 * Created by A on 2018/8/20.
 */
public class GreeterActor extends AbstractActor {
    //#greeter-messages
    private final String message;

    private final ActorRef printerActor;

    private String greeting = "";

    public GreeterActor(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WhoToGreet.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(Greet.class, x -> {
                    //#greeter-send-message
                    printerActor.tell(new Greeting(greeting), getSelf());
                    //#greeter-send-message
                })
                .build();
    }

    static public class Greet {
        public Greet() {
        }
    }

    //#greeter-messages
    static public class WhoToGreet {
        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    //#greeter-messages
    static public Props props(String message, ActorRef printerActor) {
        return Props.create(GreeterActor.class, () -> new GreeterActor(message, printerActor));
    }
}
