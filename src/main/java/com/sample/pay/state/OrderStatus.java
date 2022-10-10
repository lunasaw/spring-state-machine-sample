package com.sample.pay.state;

/**
 * @author luna
 * 2022/10/8
 */
public enum OrderStatus {

    /**
     * 待支付 已支付 已关闭 已发货 已完成
     */
    WAIT_FOR_PAY, PAID, CLOSE, DELIVERED, FINISH
}
