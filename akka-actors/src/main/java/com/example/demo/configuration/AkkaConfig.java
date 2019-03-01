package com.example.demo.configuration;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem getActorSystem() {
        return ActorSystem.create("EddyActorSystem");
    }




    //final Config settings = ConfigFactory.load();
    //return ActorSystem.create("EddyActorSystem", settings);
}
