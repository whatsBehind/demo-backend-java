package com.whatsbehind.netty_.component.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import java.net.InetSocketAddress;

@Slf4j
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 9999));

//        log.debug("Start to connect...");
//        channelFuture.sync();
//        Channel channel = channelFuture.channel();
//        channel.writeAndFlush("Hello World");

//        channelFuture.addListener(future -> {
//            ((ChannelFuture) future).channel().writeAndFlush("Hello World");
//            log.debug("Write and Flush");
//        });
        Channel channel = channelFuture.sync().channel();
        ChannelFuture closeFuture = channel.closeFuture();
        channel.close();
        closeFuture.sync();

        group.shutdownGracefully();
//        closeFuture.addListener(future -> {
//            if (future.isSuccess()) {
//                log.debug("Close group");
//            } else {
//                log.debug("Failed to close");
//            }
//            group.shutdownGracefully();
//        });
    }
}
