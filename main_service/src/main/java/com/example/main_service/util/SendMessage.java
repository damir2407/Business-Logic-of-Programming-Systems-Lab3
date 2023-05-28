package com.example.main_service.util;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class SendMessage {

    public void sendMessage(String message, String queueMessage) throws JMSException {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(5000);
        factory.setBrokerURI("tcp://localhost:61616");
        factory.setUsername("artemis");
        factory.setPassword("2002170sa");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue1");
        MessageProducer producer = session.createProducer(queue);
        producer.send(session.createTextMessage(message));
    }
}


