package com.sample.pay.event;

/**
 * @author weidian
 */

public enum OrderEvents {
    /**
     * 下单 支付 取消支付 收货
     */
    SUB_ORDER, PAY, PAY_CANCEL, RECEIVE,

    /**
     * 发货 关闭订单
     */
    DELIVERY, CLOSE_ORDER
}
