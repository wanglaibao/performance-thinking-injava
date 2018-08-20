package com.laibao.springintegration.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.laibao.springintegration.domain.RegisterData;
import com.laibao.springintegration.domain.WorkerResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkerActor extends AbstractActor{
    private final LoggingAdapter logger = Logging.getLogger(context().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                    .match(RegisterData.class,registerData -> {
                                               String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                                               long id = Thread.currentThread().getId();
                                               System.out.println(String.format("[WorkerActor, %d, %s] - Munching some data andget some work done: id: %d, name: %s, ekp: %s", id, time, registerData.getId(), registerData.getName(), registerData.getStreet()));
                                               Thread.sleep(3000);

                                                sender().tell(WorkerResult.of(registerData.getId(), Math.random() < 0.7), getSelf());
                    })
                    .matchAny(o -> logger.info("received unknown message: {}", o))
                    .build();
    }
}
