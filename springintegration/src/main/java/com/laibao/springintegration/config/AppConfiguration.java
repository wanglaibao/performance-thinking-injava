package com.laibao.springintegration.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.laibao.springintegration.springextension.AkkaSpringExtension.SpringExtProvider;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.laibao.springintegration")
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("akka-spring-integration");
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public ActorRef databaseActorRef() {
        return actorSystem().actorOf(SpringExtProvider.get(actorSystem()).props("databaseActor"), "database");
    }

    @Bean
    public ActorRef workerActorRef() {
        return actorSystem().actorOf(SpringExtProvider.get(actorSystem()).props("workerActor"), "worker");
    }
}
