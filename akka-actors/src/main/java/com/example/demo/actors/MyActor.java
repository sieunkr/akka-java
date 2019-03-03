package com.example.demo.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef child = getContext().actorOf(Props.empty(), "target");

    public MyActor() {
        getContext().watch(child);
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("kill", s -> {
                    getContext().stop(child);
                })
                .match(
                        Integer.class,
                        i -> {
                            log.info("메시지 수신 : {} 번 작업 시작", i);
                            Thread.sleep(2000);
                            log.info("{} 번 작업 종료", i);
                            getSender().tell("OK", getSelf());
                        }
                )
                .build();
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("Actor preStart");
        super.preStart();
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("Actor postStop");
        super.postStop();
    }
}
