package com.sunvalley.io.p2p.chat;

import com.sunvalley.io.p2p.chat.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <B>说明：</B><BR>
 *
 * @author zak.wu
 * @version 1.0.0
 * @date 2021/3/9 16:29
 */

public class GateWayClientInboundHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        System.out.println("xxxxxxxxxxxxxx:" + message);
    }
}
