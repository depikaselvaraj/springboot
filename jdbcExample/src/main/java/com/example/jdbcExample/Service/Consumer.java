package com.example.jdbcExample.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Component
public class Consumer {

   /* Connection connection;

    private Connection getConnection() throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        return connection;
    }

    public String getConsumer() throws JMSException{
        Connection connection = getConnection();
        connection.start();
        Session session = connection.createSession(false,1);
        Destination destination = session.createQueue("demo");
        MessageConsumer consumer = session.createConsumer(destination);
        MessageListener messageListener = consumer.getMessageListener();
        TextMessage message = (TextMessage) messageListener;
        System.out.println(message.getText());
        connection.close();
        session.close();
        return "message consumed successfully";

    }*/
    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "demo")
    public void consumer(Message message) throws JMSException {
        if(message instanceof TextMessage){
            System.out.println(true);
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("Received: " + text);
        }
       /* ObjectMapper mapper = new ObjectMapper();
        String text= mapper.convertValue(message,String.class);
        System.out.println(text);*/

    }


}
