package com.example.demo;

import akka.actor.Props;
import akka.util.Timeout;
import com.example.demo.actors.MyActor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public DemoApplication(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    private final ActorSystem actorSystem;

    @Override
    public void run(String... args) throws Exception {

        ActorRef actorRef01
                = actorSystem.actorOf(Props.create(MyActor.class), "myActor01");

        ActorRef actorRef02
                = actorSystem.actorOf(Props.create(MyActor.class), "myActor02");

        ActorRef actorRef03
                = actorSystem.actorOf(Props.create(MyActor.class), "myActor03");

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        ask(actorRef01, 1, timeout);
        ask(actorRef02, 2, Duration.ofMillis(5000));
        ask(actorRef03, 3, Duration.ofMillis(5000));

        actorSystem.stop(actorRef01);
        actorSystem.stop(actorRef02);
        actorSystem.stop(actorRef03);

    }
}
