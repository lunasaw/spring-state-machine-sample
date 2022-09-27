package com.sample.springstatemachine.appdeploy.config;

import com.sample.springstatemachine.appdeploy.models.Events;
import com.sample.springstatemachine.appdeploy.models.States;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateMachineListener extends StateMachineListenerAdapter<States, Events> {

  @Override
  public void stateChanged(State<States, Events> from, State<States, Events> to) {
    log.info("State changed to {}", to.getId());
  }
}