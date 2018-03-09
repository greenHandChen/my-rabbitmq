package com.ceh.myproducer.producerDemo2.controllers;

import com.ceh.myproducer.producerDemo2.component.ProducerDemo2;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by enHui.Chen on 2018/2/26 0026.
 */
@RestController
public class ProducerDemo2Controller {
    @Autowired
    private ProducerDemo2 producerDemo2;

    @RequestMapping(value = "/producerDemo2", method = RequestMethod.GET)
    public String producerDemo2() throws JsonProcessingException {
        producerDemo2.sendDirectMsg();
        producerDemo2.sendTopicMsg();
        producerDemo2.sendFanoutMsg();
        return "ok";
    }
}
