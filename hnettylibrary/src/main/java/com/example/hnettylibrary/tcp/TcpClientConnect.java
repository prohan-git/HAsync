package com.example.hnettylibrary.tcp;

import com.example.hnettylibrary.netty.Connection;
import com.example.hnettylibrary.netty.resources.ConnectionProvider;
import com.example.hnettylibrary.utils.HLog;

import java.util.logging.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * author: HXJ_TJun
 * created on: 2019/2/20 15:41
 * description:
 */
class TcpClientConnect extends TcpClient {

    private ConnectionProvider provider;

    TcpClientConnect(ConnectionProvider provider) {
        if (provider != null) {
            this.provider = provider;
        } else {
            throw new IllegalArgumentException("provider not be null");
        }
    }


    @Override
    public Connection connect(Bootstrap b) {
        ChannelFuture channelFuture = b.connect();
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                future.
            }
        });


        return null;
    }
}
