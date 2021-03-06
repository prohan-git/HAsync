/*
 * Copyright (c) 2011-2019 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hnettylibrary.netty.http.server;

import java.util.Objects;
import java.util.function.Function;

import io.netty.bootstrap.ServerBootstrap;
import reactor.netty.ConnectionObserver;
import reactor.netty.channel.BootstrapHandlers;
import reactor.netty.tcp.TcpServer;

/**
 * @author Stephane Maldini
 */
final class HttpServerObserve extends HttpServerOperator
		implements Function<ServerBootstrap, ServerBootstrap> {

	final ConnectionObserver observer;

	HttpServerObserve(HttpServer client, ConnectionObserver observer) {
		super(client);
		this.observer = Objects.requireNonNull(observer, "observer");
	}

	@Override
	protected TcpServer tcpConfiguration() {
		return source.tcpConfiguration().bootstrap(this);
	}

	@Override
	public ServerBootstrap apply(ServerBootstrap b) {
		ConnectionObserver observer = BootstrapHandlers.childConnectionObserver(b);
		BootstrapHandlers.childConnectionObserver(b, observer.then(this.observer));
		return b;
	}
}
