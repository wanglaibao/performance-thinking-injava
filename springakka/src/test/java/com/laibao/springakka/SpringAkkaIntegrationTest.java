package com.laibao.springakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.laibao.springakka.confiruration.AppConfiguration;
import com.laibao.springakka.extension.SpringExtension;
import com.laibao.springakka.model.Greet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;


/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class SpringAkkaIntegrationTest {

    @Autowired
    private ActorSystem actorSystem;

    @Test
    public void whenCallingGreetingActor_thenActorGreetsTheCaller() throws Exception {

        //final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");


        ActorRef greeter = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("greetingActor"));


        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = ask(greeter, new Greet("John"), timeout);
        Assert.assertEquals("Hello, John", Await.result(result, duration));
    }

    @After
    public void tearDown() {
        actorSystem.settings().JvmShutdownHooks();
    }
}
