package com.sample.springstatemachine.appdeploy.service;

import org.springframework.stereotype.Service;

import com.sample.springstatemachine.appdeploy.models.Events;
import com.sample.springstatemachine.appdeploy.models.States;

@Service
public class TestService {

    public void printComment(States source, Events event, States target) {
        System.out.println("---> Source: " + source + ", Event: " + event + ", Target: " + target);
    }

    public void doSomething(String value) {
        System.out.println("Output: customMessage " + value);
    }
}