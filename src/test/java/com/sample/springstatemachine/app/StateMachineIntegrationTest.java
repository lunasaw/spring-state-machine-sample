package com.sample.springstatemachine.app;

import com.sample.springstatemachine.application.config.SimpleStateMachineConfiguration;
import com.sample.springstatemachine.turnstile.events.SimpleEvents;
import com.sample.springstatemachine.turnstile.states.SimpleStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SimpleStateMachineConfiguration.class)
@TestMethodOrder(OrderAnnotation.class) 
public class StateMachineIntegrationTest {

    @Autowired
    private StateMachine<String, String> stateMachine;

    @BeforeEach
    public void setUp() {
        stateMachine.start();
    }

    @Test
    @Order(1)  
    public void whenSimpleStringStateMachineEvents_thenEndState() {
        assertEquals("SI", stateMachine.getState().getId());

        Mono<Message<SimpleEvents>> messageMono = Mono.just(new Message<SimpleEvents>() {
            @Override
            public SimpleEvents getPayload() {
                return SimpleEvents.E1;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        });
        Message<SimpleEvents> message = MessageBuilder.createMessage(SimpleEvents.E1, new MessageHeaders(new HashMap<>()));
        stateMachine.sendEvent(new Mono<Message<String>>() {
            @Override
            public void subscribe(CoreSubscriber<? super Message<String>> actual) {

            }
        });

        assertEquals("S1", stateMachine.getState().getId());

        stateMachine.sendEvent("E2");
        assertEquals("S2", stateMachine.getState().getId());
    }

    @Test
    @Order(2) 
    public void whenSimpleStringMachineActionState_thenActionExecuted() {

        stateMachine.sendEvent("E3");
        assertEquals("S3", stateMachine.getState().getId());

        boolean acceptedE4 = stateMachine.sendEvent("E4");

        assertTrue(acceptedE4);
        assertEquals("S4", stateMachine.getState().getId());

        stateMachine.sendEvent("E5");
        assertEquals("S5", stateMachine.getState().getId());

        stateMachine.sendEvent("end");
        assertEquals("SF", stateMachine.getState().getId());

        assertEquals(2, stateMachine.getExtendedState().getVariables().get("approvalCount"));
    }

    @AfterEach
    public void tearDown() {
        stateMachine.stop();
    }
}
