package com.dawn.rabbitmqspringexample.tutorial2.event;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;

    public Tut2Receiver(int i) {
        instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Instance " + instance + " received message " + in);
        doWork(in);
        stopWatch.stop();
        System.out.println("Instance " + instance + " done in " +
                stopWatch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {

        for (Character character: in.toCharArray()) {
            if (character == '.') {
                TimeUnit.SECONDS.sleep(1);
            }
        }



    }
}
