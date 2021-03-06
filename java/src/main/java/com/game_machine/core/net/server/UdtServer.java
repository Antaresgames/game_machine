package com.game_machine.core.net.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.udt.nio.SelectorProviderUDT;
import com.game_machine.core.net.client.UtilThreadFactory;

public class UdtServer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(UdtServer.class);

	private static Thread serverThread;
	
	private static UdtServer udtServer;
	private final String hostname;
	private final int port;
	private NioEventLoopGroup acceptGroup;
	private NioEventLoopGroup connectGroup;
	private ServerBootstrap boot;
	
	private UdtServerHandler handler;

	public UdtServer(final String hostname, final int port) {
		this.port = port;
		this.hostname = hostname;
		handler = new UdtServerHandler();
	}

	public void run() {
		log.info("Starting UdtServer port=" + this.port + " hostname=" + this.hostname);
		Thread.currentThread().setName("udt-server");
		final DefaultEventExecutorGroup executor = new DefaultEventExecutorGroup(10);
		final ThreadFactory acceptFactory = new UtilThreadFactory("accept-udt");
		final ThreadFactory connectFactory = new UtilThreadFactory("connect-udt");
		SelectorProviderUDT provider = (SelectorProviderUDT) NioUdtProvider.MESSAGE_PROVIDER;
    	provider.setMaxSelectorSize(10000);
		acceptGroup = new NioEventLoopGroup(1, acceptFactory, provider);
		connectGroup = new NioEventLoopGroup(1, connectFactory, provider);
		
		// Configure the server.
		try {
			boot = new ServerBootstrap();
			handler.setServer(this);
			boot.group(acceptGroup, connectGroup).channelFactory(NioUdtProvider.MESSAGE_ACCEPTOR);
			boot.option(ChannelOption.SO_BACKLOG, 10);
			//boot.handler(new LoggingHandler(LogLevel.WARN));

			boot.childHandler(new ChannelInitializer<UdtChannel>() {
				@Override
				public void initChannel(final UdtChannel ch) throws Exception {
					//ch.pipeline().addLast(new LoggingHandler(LogLevel.WARN), handler);
					ch.pipeline().addLast(handler);
				}
			});
			
			// Start the server.
			ChannelFuture future;
			try {
				future = boot.bind(hostname,port).sync();
				future.sync();
				
				future.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
				shutdown();
			}
		} finally {
		}
	}

	public void sendToClient(byte[] bytes, ChannelHandlerContext ctx) {
		handler.send(bytes, ctx);
	}
	
	public static UdtServer getUdtServer() {
		return udtServer;
	}
	
	public static void start(String host, Integer port) {

		// Don't try to start an already running server
		if (udtServer != null) {
			return;
		}

		udtServer = new UdtServer(host, port);
		serverThread = new Thread(udtServer);
		serverThread.start();
	}
	
	public static void stop() {

		// Don't try to stop a server that's not running
		if (udtServer == null) {
			return;
		}

		udtServer.shutdown();
		udtServer = null;
	}
	
	public void shutdown() {
		acceptGroup.shutdownGracefully();
		connectGroup.shutdownGracefully();
		log.info("Udt Server stopped");
	}


}
