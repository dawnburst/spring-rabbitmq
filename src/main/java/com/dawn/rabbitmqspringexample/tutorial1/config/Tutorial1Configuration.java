package com.dawn.rabbitmqspringexample.tutorial1.config;

import com.dawn.rabbitmqspringexample.tutorial1.event.Tut1Receiver;
import com.dawn.rabbitmqspringexample.tutorial1.event.Tut1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1", "hello-world"})
@Configuration
public class Tutorial1Configuration {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }
//
    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
