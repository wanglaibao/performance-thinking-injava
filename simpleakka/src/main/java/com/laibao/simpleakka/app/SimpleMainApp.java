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

    public static void main(String[] args) throws Exception {
        //创建容器
        final ActorSystem actorSystem = ActorSystem.create("simple-actor-system");
        logger.info("Actor System Starting...  Actor System Starting...  Actor System Starting...  Actor System Starting...");
        final ActorRef actorRef = actorSystem.actorOf(Props.create(SimpleActor.class), "simple-actor");
        actorRef.tell(new Command("jinge 1"), noSender());
        actorRef.tell(new Command("jinge 2"), noSender());
        actorRef.tell(new Command("jinge 3"), noSender());
        actorRef.tell(new Command("jinge 4"), noSender());
        actorRef.tell(new Command("jinge 5"), noSender());

        Thread.sleep(3000);
        logger.info("Actor System Shutdown Starting... Actor System Shutdown Starting... Actor System Shutdown Starting...");
        actorSystem.terminate();

    }
}
