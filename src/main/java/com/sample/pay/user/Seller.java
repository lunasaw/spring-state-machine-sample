package com.sample.pay.user;

import com.sample.pay.Application;
import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;
import reactor.core.publisher.Mono;

/**
 * @author luna
 * 2022/10/11
 */
@Data
@WithStateMachine
@Slf4j
public class Seller {

    @Autowired
    private StateMachine<OrderStatus, OrderEvents> stateMachine;

    /**
     * 发货
     * @param orderId
     */
    public void delivery(String orderId) {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder
                        .withPayload(OrderEvents.DELIVERY).setHeader(Application.HEADER_NAME, orderId).build()))
                .subscribe();
    }

    /**
     * 关闭订单
     * @param orderId
     */
    public void close(String orderId) {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder
                        .withPayload(OrderEvents.CLOSE_ORDER).setHeader(Application.HEADER_NAME, orderId).build()))
                .subscribe();
    }
}
