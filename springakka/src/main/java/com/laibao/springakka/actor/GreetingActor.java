package com.laibao.springakka.actor;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.laibao.springakka.model.Greet;
import com.laibao.springakka.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 *  Also notice the Spring annotations @Component and @Scope
 *  these define the class as a Spring-managed bean with the prototype scope.
 *  The scope is very important, because every bean retrieval request should result in a newly created instance,
 *  as this behavior matches Akka’s actor lifecycle.
 *  If you implement this bean with some other scope,
 *  the typical case of restarting actors in Akka will most likely function incorrectly.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends AbstractActor {
    private final LoggingAdapter logger = Logging.getLogger(context().system(), this);

    @Autowired
    private GreetingService greetingService;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Greet.class,greet -> sender().tell(greetingService.greet(greet.getName()),self()))
                                //.matchAny(x -> sender().tell(new Status.Failure(new Exception("unkown message")),self()))
                                .matchAny(o -> logger.info("received unknown message: {}", o))
                                .build();
    }
}
