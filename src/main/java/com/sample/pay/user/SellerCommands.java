package com.sample.pay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * @author luna
 * 2022/10/11
 */
@Component
public class SellerCommands implements CommandMarker {

    @Autowired
    private Seller seller;

    @CliCommand(value = "seller delivery", help = "卖家发货")
    public String setUser(@CliOption(key = {"", "orderId"}) String orderId) {
        seller.delivery(orderId);
        return "seller delivery " + orderId;
    }

    @CliCommand(value = "seller close", help = "卖家关闭订单")
    public String close(@CliOption(key = {"", "orderId"}) String orderId) {
        seller.close(orderId);
        return "seller close " + orderId;
    }
}
