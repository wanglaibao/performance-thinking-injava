package com.laibao.springintegration.springextension;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class SpringExt implements Extension {

    private volatile ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        // Some workaround to produce a valid scala Seq.
        // Produces a 'List(actorBeanName, applicationContext)'
        List<Object> scalaSeqConstructorParams = List$.MODULE$.empty();
        scalaSeqConstructorParams = new $colon$colon(actorBeanName, scalaSeqConstructorParams);
        scalaSeqConstructorParams = new $colon$colon(applicationContext, scalaSeqConstructorParams);

        // This also works as
        // Props.create(SpringActorProducer.class, actorBeanName, applicationContext);
        // but in some IDEs it's marked as compile error
        return Props.create(SpringActorProducer.class, scalaSeqConstructorParams);
    }
}
