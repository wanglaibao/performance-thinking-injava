package com.laibao.springakka.confiruration;

import akka.actor.ActorSystem;
import com.laibao.springakka.actor.GreetingActor;
import com.laibao.springakka.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.laibao.springakka.extension.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.laibao.springakka")
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("spring-akka");
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

}
