package com.james.demo1.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Zhan on 2018/5/13.
 */
public class Handler1 extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active!!!!!!!!!!!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String req=(String)msg;
        System.out.println("received:"+req);
        String res="Server has received "+req+"$%";
        ctx.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      //  super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
     //   super.exceptionCaught(ctx, cause);
    }
}
