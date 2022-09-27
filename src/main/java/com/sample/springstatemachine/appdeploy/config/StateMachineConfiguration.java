package com.sample.springstatemachine.appdeploy.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.sample.springstatemachine.appdeploy.action.DoSomethingAction;
import com.sample.springstatemachine.appdeploy.action.PostCommentAction;
import com.sample.springstatemachine.appdeploy.models.Events;
import com.sample.springstatemachine.appdeploy.models.States;

import lombok.AllArgsConstructor;

@Configuration
@EnableStateMachine
@AllArgsConstructor
public class StateMachineConfiguration extends EnumStateMachineConfigurerAdapter<States, Events> {

    private PostCommentAction postCommentAction;

    private DoSomethingAction doSomethingAction;

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.INITIAL)
                .states(EnumSet.allOf(States.class))
                .end(States.FINAL);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(States.INITIAL).target(States.MODEL_READ).event(Events.READ_MODEL)
                .action(this.postCommentAction)
                .and().withExternal()
                .source(States.MODEL_READ).target(States.TEMPLATE_FOLDER_IDENTIFIED)
                .event(Events.IDENTIFY_TEMPLATE_FOLDER).action(this.postCommentAction).action(this.doSomethingAction)
                .and().withExternal()
                .source(States.TEMPLATE_FOLDER_IDENTIFIED).target(States.APP_DOWNLOADED).event(Events.DOWNLOAD_APP)
                .and().withExternal()
                .source(States.APP_DOWNLOADED).target(States.APP_COPIED).event(Events.COPY_APP)
                .and().withExternal()
                .source(States.APP_COPIED).target(States.APP_ARCHIVED).event(Events.ARCHIVE_APP)
                .and().withExternal()
                .source(States.APP_ARCHIVED).target(States.APP_PUSHED).event(Events.PUSH_APP)
                .and().withExternal()
                .source(States.APP_PUSHED).target(States.APP_READY).event(Events.READY_APP)
                .and().withExternal()
                .source(States.APP_READY).target(States.APP_STARTED).event(Events.START_APP)
                .and().withExternal()
                .source(States.APP_STARTED).target(States.APP_LINKED).event(Events.LINK_APP)
                .and().withExternal()
                .source(States.APP_LINKED).target(States.APP_COMPLETED).event(Events.COMPLETE_APP)
                .and().withExternal()
                .source(States.APP_COMPLETED).target(States.FINAL).event(Events.SHOW_APP_RESULT);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }
}