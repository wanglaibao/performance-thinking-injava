package com.laibao.springintegration;

import akka.actor.ActorRef;
import com.alibaba.fastjson.JSON;
import com.laibao.springintegration.config.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class AkkaSpringIntegrationMain {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfiguration.class);
        //applicationContext.scan();
        applicationContext.refresh();

        System.out.println("AAAAAAAAAAA");

        ActorRef actorRef = (ActorRef) applicationContext.getBean("workerActorRef");
        System.out.println(JSON.toJSONString(actorRef));

        applicationContext.registerShutdownHook();
    }
}
