package com.laibao.springakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.alibaba.fastjson.JSON;
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
import static com.laibao.springakka.extension.SpringExtension.SPRING_EXTENSION_PROVIDER;


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
        ActorRef greeter = actorSystem.actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("greetingActor"), "greeter");
        FiniteDuration duration = FiniteDuration.create(5, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = ask(greeter, new Greet("John"), timeout);
        Assert.assertEquals("Hello, John", Await.result(result, duration));
        Await.result(result, duration);
        System.out.println(JSON.toJSONString(Await.result(result, duration)));
    }

    @After
    public void tearDown() {
        actorSystem.terminate();
    }
}
