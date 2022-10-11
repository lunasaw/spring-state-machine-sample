package com.sample.pay;

import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import com.sample.pay.user.Buyer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Bootstrap;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author luna
 * 2022/10/10
 */
@Slf4j
@Configuration
public class Application {
    public static final String                     HEADER_NAME = "orderId";

    /**
     * 订单创建
     */
    public static class OrderPreCreateAction implements Action<OrderStatus, OrderEvents> {

        @Override
        public void execute(StateContext<OrderStatus, OrderEvents> context) {
            log.info("订单创建");
        }
    }

    /**
     * 等待二次支付监听
     *
     */
    public static class WaitPayCreateAction implements Action<OrderStatus, OrderEvents> {

        @Override
        public void execute(StateContext<OrderStatus, OrderEvents> context) {
            log.info("支付监听是否是否成功。");
        }
    }
    public static class OrderCloseCloseAction implements Action<OrderStatus, OrderEvents> {

        @Override
        public void execute(StateContext<OrderStatus, OrderEvents> context) {
            log.info("订单关闭action。");
        }
    }

    public static class PaidAction implements Action<OrderStatus, OrderEvents> {

        @Override
        public void execute(StateContext<OrderStatus, OrderEvents> context) {
            log.info("支付完成 action context = {}", context.getMessage());
        }
    }


    @Bean
    public Buyer user() {
        return new Buyer();
    }

    public static void main(String[] args) throws Exception {
        Bootstrap.main(args);
    }
}
