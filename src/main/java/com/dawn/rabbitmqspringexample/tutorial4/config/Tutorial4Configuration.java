package com.dawn.rabbitmqspringexample.tutorial4.config;

import com.dawn.rabbitmqspringexample.tutorial4.event.Tut4Receiver;
import com.dawn.rabbitmqspringexample.tutorial4.event.Tut4Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut4","routing"})
@Configuration
public class Tutorial4Configuration {

    @Bean
    public DirectExchange fanout() {
        return new DirectExchange("tut.direct");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(DirectExchange direct,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("orange");
        }

        @Bean
        public Binding binding1b(DirectExchange direct,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("black");
        }

        @Bean
        public Binding binding2a(DirectExchange direct,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("green");
        }

        @Bean
        public Binding binding2b(DirectExchange direct,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("black");
        }

        @Bean
        public Tut4Receiver receiver() {
            return new Tut4Receiver();
        }
    }

    @Profile("sender")
    @Bean
    public Tut4Sender sender() {
        return new Tut4Sender();
    }
}