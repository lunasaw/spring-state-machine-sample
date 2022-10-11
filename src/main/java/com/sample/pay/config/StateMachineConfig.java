package com.sample.pay.config;

import com.sample.pay.Application;
import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.*;

import java.util.EnumSet;

/**
 * @author weidian
 */
@Configuration
@EnableStateMachine
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvents> states) throws Exception {
        states.withStates()
            .initial(OrderStatus.WAIT_FOR_PAY, orderPreCreateAction()).end(OrderStatus.CLOSE).end(OrderStatus.FINISH)
            .states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvents> transitions) throws Exception {
        transitions
            .withExternal().source(OrderStatus.WAIT_FOR_PAY).target(OrderStatus.PAID).event(OrderEvents.PAY).action(paidAction())
            .and().withExternal().source(OrderStatus.WAIT_FOR_PAY).target(OrderStatus.CLOSE).event(OrderEvents.PAY_CANCEL).action(orderCloseAction())
            .and().withExternal().source(OrderStatus.WAIT_FOR_PAY).target(OrderStatus.CLOSE).event(OrderEvents.CLOSE_ORDER)
            .and().withExternal().source(OrderStatus.PAID).target(OrderStatus.DELIVERED).event(OrderEvents.DELIVERY)
            .and().withExternal().source(OrderStatus.DELIVERED).target(OrderStatus.FINISH).event(OrderEvents.RECEIVE);
    }

    @Bean
    public Action<OrderStatus, OrderEvents> orderPreCreateAction() {
        return new Application.OrderPreCreateAction();
    }

    @Bean
    public Action<OrderStatus, OrderEvents> waitPlayAction() {
        return new Application.WaitPayCreateAction();
    }

    @Bean
    public Action<OrderStatus, OrderEvents> orderCloseAction() {
        return new Application.OrderCloseCloseAction();
    }


    @Bean
    public Action<OrderStatus, OrderEvents> paidAction() {
        return new Application.PaidAction();
    }

}
