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
package com.example.hnettylibrary.netty.tcp;

import java.util.Objects;

import io.netty.bootstrap.ServerBootstrap;
import reactor.netty.ConnectionObserver;
import reactor.netty.channel.BootstrapHandlers;

/**
 * @author Stephane Maldini
 */
final class TcpServerObserve extends TcpServerOperator {

	final ConnectionObserver observer;

	TcpServerObserve(TcpServer server, ConnectionObserver observer) {
		super(server);
		this.observer = Objects.requireNonNull(observer, "observer");
	}

	@Override
	public ServerBootstrap configure() {
		ServerBootstrap b = source.configure();
		ConnectionObserver observer = BootstrapHandlers.childConnectionObserver(b);
		BootstrapHandlers.childConnectionObserver(b, observer.then(this.observer));
		return b;
	}
}
