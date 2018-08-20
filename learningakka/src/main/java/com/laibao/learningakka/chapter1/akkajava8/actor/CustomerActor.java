package com.laibao.learningakka.chapter1.akkajava8.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.laibao.learningakka.chapter1.akkajava8.domain.Coffee;
import com.laibao.learningakka.chapter1.akkajava8.domain.Order;

import java.util.Random;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version
 */

public class CustomerActor extends AbstractLoggingActor {

    private final ActorRef actorRef;

    private final Order order;

    private static final Random orderRandomiser = new Random();

    public static Props props(final ActorRef actorRef) {
        return Props.create(CustomerActor.class, () -> new CustomerActor(actorRef));
    }

    @Override
    public void preStart() throws Exception {
        log().info("{}, please", order);
        orderCoffee();
    }

    private CustomerActor(final ActorRef actorRef) {
        this.actorRef = actorRef;
        this.order = decideOrder();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                        .match(Coffee.class, coffee -> {
                                log().info("Enjoying my {}", coffee);
                                goodbye();
                        })
                        .matchAny(this::unhandled)
                        .build();
    }

    private void orderCoffee() {
        log().info("{}, please", order);
        actorRef.tell(order, self());
    }

    private void goodbye() {
        log().info("Goodbye");
        context().stop(self());
    }

    private Order decideOrder() {
        // A considered decision...
        return Order.of(Coffee.coffeeTypes[orderRandomiser.nextInt(Coffee.coffeeTypes.length)]);
    }
}
