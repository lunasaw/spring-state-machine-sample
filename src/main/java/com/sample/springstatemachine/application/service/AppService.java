package com.sample.springstatemachine.application.service;

import com.sample.springstatemachine.application.config.SimpleStateMachineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * 2022/9/27
 */
@Service
public class AppService {

    @Autowired
    private SimpleStateMachineConfiguration simpleStateMachineConfiguration;


}
