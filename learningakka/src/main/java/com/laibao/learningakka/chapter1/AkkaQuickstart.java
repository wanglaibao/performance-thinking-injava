package com.laibao.learningakka.chapter1;

import java.io.IOException;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.laibao.learningakka.chapter1.actor.GreeterActor;
import com.laibao.learningakka.chapter1.actor.PrinterActor;

public class AkkaQuickstart {
  public static void main(String[] args) {
    final ActorSystem actorSystem = ActorSystem.create("helloAkka");
    try {
      //#create-actors
      final ActorRef printerActor = actorSystem.actorOf(PrinterActor.props(), "printerActor");

      final ActorRef howdyGreeter = actorSystem.actorOf(GreeterActor.props("Howdy", printerActor), "howdyGreeter");

      final ActorRef helloGreeter = actorSystem.actorOf(GreeterActor.props("Hello", printerActor), "helloGreeter");

      final ActorRef goodDayGreeter = actorSystem.actorOf(GreeterActor.props("Good day", printerActor), "goodDayGreeter");
      //#create-actors

      //#main-send-messages
      howdyGreeter.tell(new GreeterActor.WhoToGreet("Akka"), ActorRef.noSender());
      howdyGreeter.tell(new GreeterActor.Greet(), ActorRef.noSender());

      howdyGreeter.tell(new GreeterActor.WhoToGreet("Lightbend"), ActorRef.noSender());
      howdyGreeter.tell(new GreeterActor.Greet(), ActorRef.noSender());

      helloGreeter.tell(new GreeterActor.WhoToGreet("Java"), ActorRef.noSender());
      helloGreeter.tell(new GreeterActor.Greet(), ActorRef.noSender());

      goodDayGreeter.tell(new GreeterActor.WhoToGreet("Play"), ActorRef.noSender());
      goodDayGreeter.tell(new GreeterActor.Greet(), ActorRef.noSender());

      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ioe) {
    } finally {
        actorSystem.terminate();
    }
  }
}
