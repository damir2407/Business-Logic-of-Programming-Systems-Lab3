package com.example.main_service.util;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class SendMessage {

    public void sendMessage(String message, String queueMessage) throws JMSException {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(5000);
        factory.setBrokerURI("tcp://artemis:61616");
        factory.setUsername("artemis");
        factory.setPassword("2002170sa");
        Connection connection = factory.createConnection();
        System.out.println(message);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueMessage);
        MessageProducer producer = session.createProducer(destination);
        producer.send(session.createTextMessage(message));
        session.close();
        connection.close();
    }
}


