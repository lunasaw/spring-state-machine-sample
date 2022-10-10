package com.sample.pay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * @author luna
 * 2022/10/10
 */
@Component
public class UserCommands implements CommandMarker {

    @Autowired
    private User user;

    @CliCommand(value = "user name", help = "设置下单用户")
    public String setUser(@CliOption(key = {"", "name"}) String name) {
        user.setName(name);
        return "user name " + name;
    }

    @CliCommand(value = "user create", help = "用户创建订单")
    public String createOrder(@CliOption(key = {"", "orderId"})  String orderId) {
        user.createOrder(orderId);
        return "user orderId " + orderId;
    }
}
