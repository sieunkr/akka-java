package com.example.demo.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private Integer magicNumber = 5;

    public MyActor() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Integer.class,
                        i -> {
                            log.info("메시지 수신 : {}", i);
                            getSender().tell(magicNumber + i, getSelf());
                        }
                )
                .build();
    }

    






}
