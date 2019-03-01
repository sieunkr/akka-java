package com.example.demo;

import akka.actor.Props;
import com.example.demo.actors.MyActor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.time.Duration;

import static akka.pattern.Patterns.ask;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //TODO:ActorSystem 은 싱글톤으로 구현
        ActorSystem system = ActorSystem.create("test-system");
        ActorRef readingActorRef
                = system.actorOf(Props.create(MyActor.class), "my-actor");

        ask(readingActorRef, 5, Duration.ofMillis(300));

        system.stop(readingActorRef);

    }
}
