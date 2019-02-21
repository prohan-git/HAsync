package com.example.hnettylibrary.tcp;

import com.example.hnettylibrary.netty.Connection;
import com.example.hnettylibrary.netty.resources.ConnectionProvider;
import com.example.hnettylibrary.netty.tcp.InetSocketAddressUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.util.NetUtil;

/**
 * author: HXJ_TJun
 * created on: 2019/2/20 15:39
 * description:
 */
public abstract class TcpClient {


    public static TcpClient create(ConnectionProvider provider) {
        return new TcpClientConnect(provider);
    }

    public abstract Connection connect(Bootstrap b);

    static final int DEFAULT_PORT = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 12012;

    static final Bootstrap DEFAULT_BOOTSTRAP = new Bootstrap().option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
            .option(ChannelOption.AUTO_READ, false)
            .option(ChannelOption.SO_RCVBUF, 1024 * 1024)
            .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
            .remoteAddress(InetSocketAddressUtil.createUnresolved(NetUtil.LOCALHOST.getHostAddress(), DEFAULT_PORT));
}
