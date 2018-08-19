package com.laibao.learningakka.chapter1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.alibaba.fastjson.JSON;
import com.laibao.learningakka.chapter1.akkademy.AkkademyDb;
import com.laibao.learningakka.chapter1.akkademy.messages.SetRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author laibao wang
 * @date 2018-08-19
 * @version 1.0
 */
public class AkkademyDbTest {
    final ActorSystem actorSystem = ActorSystem.create();

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(actorSystem, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("name", "金戈大大"), ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        System.out.println(JSON.toJSONString(akkademyDb));
        assertEquals(akkademyDb.getMap().get("name"), "金戈大大");

        System.out.println(akkademyDb.getMap().get("name"));
    }


}
