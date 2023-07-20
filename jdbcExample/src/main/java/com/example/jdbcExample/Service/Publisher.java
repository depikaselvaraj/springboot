package com.example.jdbcExample.Service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class Publisher {

   /*   Connection connection;

    private Connection getConnection() throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        return connection;
    }

    public  String produceMessage(String str) throws JMSException{
        Connection connection = getConnection();
        Session session = connection.createSession(false,1);
        Destination destination = session.createQueue("demo");
        TextMessage message = session.createTextMessage(str);
        MessageProducer producer = session.createProducer(destination);
        producer.send(message);
        session.close();
        connection.close();
        return "Message is produced Successfully";
    }*/
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String queue, final String message){
        jmsTemplate.convertAndSend(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(message);
                Message message1 = session.createTextMessage(message);
                System.out.println(message1);
                return message1;
            }
        });
    }
}
