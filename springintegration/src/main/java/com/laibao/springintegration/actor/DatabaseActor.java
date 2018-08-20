package com.laibao.springintegration.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.laibao.springintegration.domain.RegisterData;
import com.laibao.springintegration.domain.WorkerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class DatabaseActor extends AbstractActor{
    private final LoggingAdapter logger = Logging.getLogger(context().system(), this);

    @Autowired
    //@Qualifier(value = "workerActor")
    private ActorRef workerActorRef;

    @Override
    public Receive createReceive() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        long id = Thread.currentThread().getId();
        return receiveBuilder().match(WorkerResult.class,workerResult -> {
                                        if (workerResult.isSuccess()) {
                                            System.out.println(String.format("[DatabaseActor, %d, %s] - Getting result from worker for id %d. It was a SUCCESS!", id, time, workerResult.getId()));
                                        } else {
                                            System.out.println(String.format("[DatabaseActor, %d, %s] - Getting result from worker for id %d. It has FAILED!", id, time, workerResult.getId()));
                                        }
                                    })
                                    .matchEquals("Tick",str -> {
                                        Thread.sleep(2000);
                                        int rand = (int) (Math.random() * 5);
                                        System.out.println(String.format("[DatabaseActor, %d, %s] - Getting a tick: Reading database and get %d registrations.", id, time, rand));
                                        for (int i = 0; i < rand; i++) {
                                            workerActorRef.tell(RegisterData.of((int) (Math.random() * 1000), "Max_" + i, "Mainstreet" + i), this.getSelf());
                                        }
                                    })
                                    .matchAny(o -> logger.info("received unknown message: {}", o))
                                    .build();
    }
}
