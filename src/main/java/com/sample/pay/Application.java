package com.sample.pay;

import com.sample.pay.event.OrderEvents;
import com.sample.pay.state.OrderStatus;
import com.sample.pay.user.User;
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
    /**
     * 订单创建
     */
    public static class OrderPreCreateAction implements Action<OrderStatus, OrderEvents> {

        @Override
        public void execute(StateContext<OrderStatus, OrderEvents> context) {
            log.info("系统初始化");
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
    @Bean
    public User user() {
        return new User();
    }

    public static void main(String[] args) throws Exception {
        Bootstrap.main(args);
    }
}
