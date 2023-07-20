package com.example.jdbcExample.Controller;

import com.example.jdbcExample.Model.*;
import com.example.jdbcExample.Service.Consumer;
import com.example.jdbcExample.Service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JmsController {

    @Autowired
    Publisher publisher;

    @Value ("${some.host}")
    String host;

    @Value("${spring.cloud.openfeign.client.config.absher.url}")
    String url;

    @Autowired
    Consumer consumer;

    @GetMapping("/get")
    public void getPublish() throws JMSException {
        publisher.sendMessage("demo","welcome to the world of jms");
    }

}
