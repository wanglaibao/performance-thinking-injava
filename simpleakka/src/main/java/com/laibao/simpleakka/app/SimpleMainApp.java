package com.laibao.simpleakka.app;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.laibao.simpleakka.actor.SimpleActor;
import com.laibao.simpleakka.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static akka.actor.ActorRef.noSender;

/**
 * @author laibao wang
 * @date 2018-08-20
 * @version 1.0
 */
public class SimpleMainApp {
    public static final Logger logger = LoggerFactory.getLogger(SimpleMainApp.class);

    public static void main(String[] args) {
        //创建容器
        final ActorSystem actorSystem = ActorSystem.create("simple-actor-system");

        final ActorRef actorRef = actorSystem.actorOf(Props.create(SimpleActor.class), "simple-actor");

        actorRef.tell(new Command("CMD 1"), noSender());
        actorRef.tell(new Command("CMD 2"), noSender());
        actorRef.tell(new Command("CMD 3"), noSender());
        actorRef.tell(new Command("CMD 4"), noSender());
        actorRef.tell(new Command("CMD 5"), noSender());


        logger.debug("Actor System Shutdown Starting...");
        actorSystem.terminate();

    }
}
