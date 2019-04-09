package com.dawn.rabbitmqspringexample.tutorial1.event;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        String message = "Hello world!";
        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println("Message: " + message + " was send");
    }
}
