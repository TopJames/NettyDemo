package com.james.demo1.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Zhan on 2018/5/13.
 */
public class ClientHandler1 extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active!!!!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String res=(String)msg;
        System.out.println("reveived:"+res);

 //       throw new RuntimeException("this is some exception.");

        ReferenceCountUtil.release(msg);

        ctx.fireChannelRead(msg);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("this is exception.cause:"+cause.getMessage());
    }
}
