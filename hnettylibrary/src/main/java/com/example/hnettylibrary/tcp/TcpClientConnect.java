package com.example.hnettylibrary.tcp;

import com.example.hnettylibrary.netty.resources.ConnectionProvider;
import com.example.hnettylibrary.utils.HLog;

import java.util.logging.Logger;

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
            HLog.e("ConnectionProvider can not be null");
        }
    }


}
