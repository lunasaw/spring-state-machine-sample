package com.sample.springstatemachine.appdeploy.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.sample.springstatemachine.appdeploy.models.Events;
import com.sample.springstatemachine.appdeploy.models.States;
import com.sample.springstatemachine.appdeploy.service.TestService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DoSomethingAction implements Action<States, Events> {

    private TestService testService;

    @Override
    public void execute(StateContext<States, Events> context) {
        this.testService.doSomething(context.getMessageHeader("customMessage").toString());
        System.out.println("--> Retrieve CustomType: "+context.getExtendedState().getVariables().get("customType"));
    }
}