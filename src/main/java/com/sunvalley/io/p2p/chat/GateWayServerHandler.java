package com.sunvalley.io.p2p.chat;

import com.sunvalley.io.p2p.chat.entity.BaseMessage;
import com.sunvalley.io.p2p.chat.entity.ResultMessage;
import com.sunvalley.io.p2p.chat.enums.TypeEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <B>说明：</B><BR>
 *
 * @author zak.wu
 * @version 1.0.0
 * @date 2021/3/9 16:29
 */

public class GateWayServerHandler extends SimpleChannelInboundHandler<BaseMessage> {

    // 认证客户端
    private static final NettyClientPool authClientPool = new NettyClientPool("127.0.0.1", 6670);

    // 业务客户端
    private static final NettyClientPool businessClientPool = new NettyClientPool("127.0.0.1", 6670);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage message) throws Exception {
        System.out.println(message);
        if (message.getType().equals(TypeEnum.RESULT.getValue())) {
            ResultMessage resultMessage = (ResultMessage) message.getMessage();
            ChannelUtils.getChannel(resultMessage.getId()).writeAndFlush(resultMessage.getMessage());
        } else if (message.getType().equals(TypeEnum.BUSINESS.getValue())) {
            ChannelUtils.addChannel(message.getId(), ctx.channel());
            businessClientPool.sendMessage(message);
        } else {
            ChannelUtils.addChannel(message.getId(), ctx.channel());
            authClientPool.sendMessage(message);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //    UserManager.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //    UserManager.online(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //    UserManager.offline(ctx.channel());
    }
}
