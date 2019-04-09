package com.dawn.rabbitmqspringexample.tutorial2.event;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Tut2Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){

        String message = "Hello";

        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        message += IntStream.range(0, dots.get())
                .mapToObj(operand -> ".")
                .reduce("", String::concat);

        message += count.incrementAndGet();

        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println("Message: " + message + " was send");
    }
}
