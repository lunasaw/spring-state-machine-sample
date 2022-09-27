package com.sample.springstatemachine.appdeploy.service;

import com.sample.springstatemachine.appdeploy.models.Events;
import com.sample.springstatemachine.appdeploy.models.States;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StateMachineService {

    private StateMachine<States, Events> stateMachine;

    public void startState(Events event) {
        this.stateMachine.start();

        Message<Events> message = MessageBuilder
                .withPayload(event)
                .build();
        this.stateMachine.sendEvent(message);

        System.out.println("----> Test: "+this.stateMachine.getExtendedState().getVariables().get("customType"));

        Message<Events> message2 = MessageBuilder
                .withPayload(Events.IDENTIFY_TEMPLATE_FOLDER)
                .setHeader("customMessage", "This is a custom Message")
                .build();
        this.stateMachine.sendEvent(message2);

        this.stateMachine.stop();
    }
}