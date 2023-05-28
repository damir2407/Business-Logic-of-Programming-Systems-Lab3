package com.example.main_service.util;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class SendMessage {

    public void sendMessage(String message) throws Exception {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(5000);
        factory.setBrokerURI("tcp://0.0.0.0:61616");
        factory.setUsername("artemis");
        factory.setPassword("20021700sa");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("accept");
        System.out.println("Message :" + message);
        MessageProducer producer = session.createProducer(queue);
        producer.send(session.createTextMessage(message));
    }
}

