package com.example.demo.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public MyActor() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Integer.class,
                        i -> {
                            log.info("메시지 수신 : {} 번 작업 시작", i);
                            Thread.sleep(3000);
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
