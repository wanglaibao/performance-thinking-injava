package com.laibao.springakka.actor;

import akka.actor.AbstractActor;
import akka.actor.Status;
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
 */
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GreetingActor extends AbstractActor {

    @Autowired
    private GreetingService greetingService;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Greet.class,greet -> sender().tell(greetingService.greet(greet.getName()),self()))
                                .matchAny(x -> sender().tell(new Status.Failure(new Exception("unkown message")),self())).build();
    }
}
