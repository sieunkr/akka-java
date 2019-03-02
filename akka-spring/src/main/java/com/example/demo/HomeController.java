package com.example.demo;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @RequestMapping("/")
    public Source<String, NotUsed> index() {
        return Source.repeat("Hello world!").intersperse("\n").take(10);
    }
}
