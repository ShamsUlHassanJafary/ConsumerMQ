package com.example.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer implements MessageListener {

    @JmsListener(destination = "QUEUE1")
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Received message: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
