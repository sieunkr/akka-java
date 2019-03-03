package com.example.demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import com.example.demo.actors.MyActor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DemoApplicationTests {

    static ActorSystem actorSystem;

    @BeforeClass
    public static void setup() {
        actorSystem = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(actorSystem);
        actorSystem = null;
    }

    @Test
    public void test(){

        ActorRef actorRef01
                = actorSystem.actorOf(Props.create(MyActor.class), "myActor01");

        new TestKit(actorSystem) {{
            actorRef01.tell( 5, getRef() );
            // await the correct response
            expectMsg(java.time.Duration.ofSeconds(5), "OK");
        }};
    }

}
