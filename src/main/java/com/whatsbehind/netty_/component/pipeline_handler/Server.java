package com.whatsbehind.netty_.component.pipeline_handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class Server {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(2);
        ChannelInboundHandlerAdapter inboundHandler1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                String message = buf.toString(StandardCharsets.UTF_8);
                log.debug("InboundHandler_1 receives msg: [{}]", message);
                super.channelRead(ctx, message);
            }
        };
        ChannelInboundHandlerAdapter inboundHandler2 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("InboundHandler_2 receives msg: [{}]", msg);
                ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes("Hello Client".getBytes()));
                super.channelRead(ctx, msg);
            }
        };
        ChannelOutboundHandlerAdapter outboundHandler1 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("OutboundHandler_1 writes msg");
                super.write(ctx, msg, promise);
            }
        };
        ChannelOutboundHandlerAdapter outboundHandler2 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("OutboundHandler_2 writes msg");
                super.write(ctx, msg, promise);
            }
        };
        new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel){
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        nioSocketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        pipeline.addLast(inboundHandler1);
                        pipeline.addLast(inboundHandler2);
                        pipeline.addLast(outboundHandler1);
                        pipeline.addLast(outboundHandler2);
                    }
                })
                .bind(9999);
    }
}
