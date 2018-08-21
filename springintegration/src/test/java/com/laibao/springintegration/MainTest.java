package com.laibao.springintegration;

import com.laibao.springintegration.actor.WorkerActor;
import org.junit.Test;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class MainTest {

    /*
        Exception in thread "main" akka.actor.ActorInitializationException: You cannot create an instance of [com.laibao.springintegration.actor.WorkerActor] explicitly using the constructor (new). You have to use one of the 'actorOf' factory methods to create a new actor. See the documentation.
	    at akka.actor.ActorInitializationException$.apply(Actor.scala:194)
	    at akka.actor.Actor.$init$(Actor.scala:472)
	    at akka.actor.AbstractActor.<init>(AbstractActor.scala:180)
	    at com.laibao.springintegration.actor.WorkerActor.<init>(WorkerActor.java:23)
	    at com.laibao.springintegration.MainTest.main(MainTest.java:11)
     */
    @Test
    public void workActorTest() {
        WorkerActor actor = new WorkerActor();
        actor.createReceive();
    }
}
