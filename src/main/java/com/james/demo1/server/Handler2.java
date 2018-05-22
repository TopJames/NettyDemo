package com.james.demo1.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.RecyclableArrayList;

/**
 * Created by Zhan on 2018/5/13.
 */
public class Handler2 extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active2!!!!!!!!!!!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
   //     RecyclableArrayList out = RecyclableArrayList.newInstance();
        System.out.println("this is handler2");
        String req=(String)msg;
        System.out.println("received2:"+req);
        String res="Server has received2 "+req+"$%";
        ctx.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));
      //  System.out.println(out.recycle());
        ctx.fireChannelRead(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
