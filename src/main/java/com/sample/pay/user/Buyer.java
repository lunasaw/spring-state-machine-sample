package com.sample.pay.user;

import com.sample.pay.Application;
import com.sample.pay.anno.StatesOnTransition;
import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;
import reactor.core.publisher.Mono;

/**
 * @author luna
 * 2022/10/10
 */
@Data
@WithStateMachine
@Slf4j
public class Buyer {

    @Autowired
    private StateMachine<OrderStatus, OrderEvents> stateMachine;

    private String                                 name;

    /**
     * 装载 触发事件
     *
     * @param
     * @param orderId
     */
    public void createOrder(String orderId) {
        stateMachine.startReactively().subscribe();
        stateMachine
            .sendEvent(Mono.just(MessageBuilder
                .withPayload(OrderEvents.SUB_ORDER).setHeader(Application.HEADER_NAME, orderId).build()))
            .subscribe();
    }

    /**
     * 支付
     * @param orderId
     */
    public void pay(String orderId) {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder
                        .withPayload(OrderEvents.PAY).setHeader(Application.HEADER_NAME, orderId).build()))
                .subscribe();
    }

    /**
     * 支付取消
     * @param orderId
     */
    public void payCancel(String orderId) {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder
                        .withPayload(OrderEvents.PAY_CANCEL).setHeader(Application.HEADER_NAME, orderId).build()))
                .subscribe();
    }


    /**
     * 收货
     * @param orderId
     */
    public void receive(String orderId) {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder
                        .withPayload(OrderEvents.RECEIVE).setHeader(Application.HEADER_NAME, orderId).build()))
                .subscribe();
    }


    /**
     * 到 WAIT_FOR_PAY 出发的会触发
     * @param extendedState
     */
    @StatesOnTransition(target = {OrderStatus.WAIT_FOR_PAY})
    public void payIng(ExtendedState extendedState) {
        // 支付中
        log.info("支付中 extendedState = {}", extendedState);
    }

    /**
     * 到 PAID 的会触发
     * @param extendedState
     */
    @StatesOnTransition(target = {OrderStatus.PAID})
    public void payed(ExtendedState extendedState) {
        // 支付完成
        String orderId = extendedState.get(Application.HEADER_NAME, String.class);
        log.info("支付完成 transition extendedState = {}, orderId = {}", extendedState, orderId);
    }


    /**
     * 当转换以目标状态 WAIT_FOR_PAY 发生时，我们使用@OnTransition 注释来挂接回调。
     *
     * @param extendedState
     */
    @StatesOnTransition(target = OrderStatus.WAIT_FOR_PAY)
    public void busy(ExtendedState extendedState) {
        Object orderId = extendedState.getVariables().get(Application.HEADER_NAME);
        if (orderId != null) {
            log.info("拿到订单号 orderId = {} 创建订单", orderId);
        }
    }
}
