package com.james.demo1.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup pGroup=new NioEventLoopGroup();
        EventLoopGroup cGroup=new NioEventLoopGroup();

        ServerBootstrap sbs=new ServerBootstrap();

        sbs.group(pGroup,cGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_SNDBUF,32*1024)
                .option(ChannelOption.SO_RCVBUF,32*1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ByteBuf bb= Unpooled.copiedBuffer("*%".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,bb));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new Handler1());
                        socketChannel.pipeline().addLast(new Handler2());
                    }
                });

        ChannelFuture cf=sbs.bind(3333).sync();

        cf.channel().closeFuture().sync();

        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
