package com.tvd12.ezyfoxserver.nio.builder.impl;

import static com.tvd12.ezyfoxserver.util.EzyProcessor.processWithLogException;

import javax.net.ssl.SSLContext;

import org.eclipse.jetty.server.Server;

import com.tvd12.ezyfoxserver.builder.EzyBuilder;
import com.tvd12.ezyfoxserver.context.EzyServerContext;
import com.tvd12.ezyfoxserver.nio.websocket.EzyWsWriter;
import com.tvd12.ezyfoxserver.nio.wrapper.EzyHandlerGroupManager;
import com.tvd12.ezyfoxserver.setting.EzySessionManagementSetting;
import com.tvd12.ezyfoxserver.setting.EzySettings;
import com.tvd12.ezyfoxserver.setting.EzyWebSocketSetting;
import com.tvd12.ezyfoxserver.socket.EzySessionTicketsQueue;
import com.tvd12.ezyfoxserver.util.EzyDestroyable;
import com.tvd12.ezyfoxserver.util.EzyStartable;

public class EzyWebSocketServerBootstrap 
		implements EzyStartable, EzyDestroyable {

	private Server server;
	private SSLContext sslContext;
	private EzyWsWriter socketWriter;
	
	private EzyServerContext serverContext;
	private EzyHandlerGroupManager handlerGroupManager;
	private EzySessionTicketsQueue sessionTicketsQueue;
	
	public EzyWebSocketServerBootstrap(Builder builder) {
		this.sslContext = builder.sslContext;
		this.serverContext = builder.serverContext;
		this.handlerGroupManager = builder.handlerGroupManager;
		this.sessionTicketsQueue = builder.sessionTicketsQueue;
	}
	
	@Override
	public void start() throws Exception {
		server = newSocketServer();
		server.start();
		socketWriter = newSocketWriter();
		socketWriter.start();
	}
	
	@Override
	public void destroy() {
		processWithLogException(socketWriter::destroy);
		processWithLogException(server::stop);
	}
	
	private Server newSocketServer() {
		return newSocketServerCreator()
				.settings(getWsSettings())
				.sessionSettings(getSessionSettings())
				.handlerGroupManager(handlerGroupManager)
				.create();
	}
	
	private EzyWsWriter newSocketWriter() {
		return EzyWsWriter.builder()
				.threadPoolSize(getWriterPoolSize())
				.handlerGroupManager(handlerGroupManager)
				.sessionTicketsQueue(sessionTicketsQueue)
				.build();
	}
	
	private EzyWebSocketServerCreator newSocketServerCreator() {
		if(isSslActive())
			return new EzyWebSocketSecureServerCreator(sslContext);
		return new EzyWebSocketServerCreator();
	}
	
	private int getWriterPoolSize() {
		return 3;
	}
	
	private boolean isSslActive() {
		return getWsSettings().isSslActive();
	}
	
	private EzyWebSocketSetting getWsSettings() {
		return getServerSettings().getWebsocket();
	}
	
	private EzySessionManagementSetting getSessionSettings() {
		return getServerSettings().getSessionManagement();
	}
	
	private EzySettings getServerSettings() {
		return serverContext.getServer().getSettings();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder implements EzyBuilder<EzyWebSocketServerBootstrap> {

		private SSLContext sslContext;
		private EzyServerContext serverContext;
		private EzyHandlerGroupManager handlerGroupManager;
		private EzySessionTicketsQueue sessionTicketsQueue;
		
		public Builder sslContext(SSLContext sslContext) {
			this.sslContext = sslContext;
			return this;
		}
		
		public Builder serverContext(EzyServerContext context) {
			this.serverContext = context;
			return this;
		}
		
		public Builder handlerGroupManager(EzyHandlerGroupManager manager) {
			this.handlerGroupManager = manager;
			return this;
		}
		
		public Builder sessionTicketsQueue(EzySessionTicketsQueue queue) {
			this.sessionTicketsQueue = queue;
			return this;
		}
		
		@Override
		public EzyWebSocketServerBootstrap build() {
			return new EzyWebSocketServerBootstrap(this);
		}
	}
	
}
