package com.sample.springstatemachine.turnstile.service;

import com.sample.springstatemachine.turnstile.events.TurnstileEvents;
import com.sample.springstatemachine.turnstile.states.TurnstileStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * 2022/9/27
 */
@Service
public class TurnstileService {

    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

    public void run(String... strings) throws Exception {
        stateMachine.start();
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachine.stop();
    }

}
