package com.sample.pay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author luna
 * 2022/10/10
 */
@Component
public class BuyerCommands implements CommandMarker {

    @Autowired
    private Buyer buyer;

    @CliCommand(value = "buyer name", help = "设置下单用户")
    public String setUser(@CliOption(key = {"", "name"}) String name) {
        buyer.setName(name);
        return "user name " + name;
    }

    @CliCommand(value = "buyer create", help = "用户创建订单")
    public String createOrder() {
        String orderId = UUID.randomUUID().toString();
        buyer.createOrder(orderId);
        return "user orderId " + orderId;
    }

    @CliCommand(value = "buyer cancel", help = "用户取消支付")
    public String payCancel(@CliOption(key = {"", "orderId"})  String orderId) {
        if (orderId == null){
            return "订单号为空";
        }
        buyer.payCancel(orderId);
        return "user payCancel " + orderId;
    }

    @CliCommand(value = "buyer pay", help = "用户支付")
    public String pay(@CliOption(key = {"", "orderId"})  String orderId) {
        if (orderId == null){
            return "订单号为空";
        }
        buyer.pay(orderId);
        return "user pay " + orderId;
    }

    @CliCommand(value = "buyer receive", help = "用户收货")
    public String receive(@CliOption(key = {"", "orderId"})  String orderId) {
        if (orderId == null){
            return "订单号为空";
        }
        buyer.receive(orderId);
        return "user receive " + orderId;
    }
}
