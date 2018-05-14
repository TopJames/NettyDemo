package com.james.demo1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by Zhan on 2018/5/13.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();

        Bootstrap bs=new Bootstrap();
        bs.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf bb= Unpooled.copiedBuffer("$%".getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,bb));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ClientHandler1());
                    }
                });

       ChannelFuture cf= bs.connect("127.0.0.1",3333).sync();

       cf.channel().writeAndFlush(Unpooled.copiedBuffer("yoyo hahahaha*%".getBytes()));
       cf.channel().writeAndFlush(Unpooled.copiedBuffer("i wante to fuck julia~*%".getBytes()));

       cf.channel().closeFuture().sync();
       group.shutdownGracefully();
    }
}
