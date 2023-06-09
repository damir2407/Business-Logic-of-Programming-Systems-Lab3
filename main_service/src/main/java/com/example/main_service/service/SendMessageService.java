package com.example.main_service.service;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;


@Service
public class SendMessageService {

    @Value("${recipe.accept.queue}")
    private String acceptQueueName;

    @Value("${recipe.decline.queue}")
    private String declineQueueName;

    private final StompJmsConnectionFactory factory;

    public SendMessageService(StompJmsConnectionFactory factory) {
        this.factory = factory;
    }

    public void sendAcceptMessage(Long recipeId, String admin,
                                  String email ) throws JMSException {
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(acceptQueueName);
        MessageProducer producer = session.createProducer(destination);

        Message toSendMessage = session.createMessage();
        toSendMessage.setLongProperty("recipeId", recipeId);
        toSendMessage.setStringProperty("admin", admin);
        toSendMessage.setStringProperty("email", email);


        producer.send(toSendMessage);
        session.close();
        connection.close();
    }


    public void sendDeclineMessage(Long recipeId, String admin,
                                   String declineReason, String email ) throws JMSException {
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(declineQueueName);
        MessageProducer producer = session.createProducer(destination);

        Message toSendMessage = session.createMessage();
        toSendMessage.setLongProperty("recipeId", recipeId);
        toSendMessage.setStringProperty("admin", admin);
        toSendMessage.setStringProperty("declineReason", declineReason);
        toSendMessage.setStringProperty("email", email);

        producer.send(toSendMessage);
        session.close();
        connection.close();
    }


}

