package com.sample.pay.user;

import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import reactor.core.publisher.Mono;

/**
 * @author luna
 * 2022/10/10
 */
@Data
@WithStateMachine
@Slf4j
public class User {

    @Autowired
    private StateMachine<OrderStatus, OrderEvents> stateMachine;

    private String                                 name;
    public static final String                     HEADER_NAME = "orderId";

    public final static String                     ORDER_ID    = "10086";

    /**
     * 装载 触发事件
     *
     * @param
     * @param orderId
     */
    public void createOrder(String orderId) {
        stateMachine
            .sendEvent(Mono.just(MessageBuilder
                .withPayload(OrderEvents.SUB_ORDER).setHeader(HEADER_NAME, orderId).build()))
            .subscribe();
    }

    /**
     * 当转换以目标状态 CREATED 发生时，我们使用@OnTransition 注释来挂接回调。
     *
     * @param extendedState
     */
    @OnTransition(target = "CREATED")
    public void busy(ExtendedState extendedState) {
        Object orderId = extendedState.getVariables().get(HEADER_NAME);
        if (orderId != null) {
            log.info("拿到订单号 orderId = {} 创建订单", orderId);
        }
    }
}
