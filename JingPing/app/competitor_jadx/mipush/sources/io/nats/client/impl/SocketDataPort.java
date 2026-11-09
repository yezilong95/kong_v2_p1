package io.nats.client.impl;

import io.nats.client.Options;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsUri;
import io.nats.client.support.WebSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public class SocketDataPort implements DataPort {
    protected NatsConnection connection;
    protected String host;
    protected InputStream in;
    protected boolean isSecure = false;
    protected OutputStream out;
    protected int port;
    protected int soLinger;
    protected Socket socket;

    public static boolean isWebsocketScheme(String str) {
        return NatsConstants.WEBSOCKET_PROTOCOL.equalsIgnoreCase(str) || NatsConstants.SECURE_WEBSOCKET_PROTOCOL.equalsIgnoreCase(str);
    }

    @Override // io.nats.client.impl.DataPort
    public void afterConstruct(Options options) {
        this.soLinger = options.getSocketSoLinger();
    }

    @Override // io.nats.client.impl.DataPort
    public void close() throws IOException {
        this.socket.close();
    }

    @Override // io.nats.client.impl.DataPort
    public void connect(String str, NatsConnection natsConnection, long j) throws IOException {
        try {
            connect(natsConnection, new NatsUri(str), j);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    @Override // io.nats.client.impl.DataPort
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // io.nats.client.impl.DataPort
    public void forceClose() throws IOException {
        try {
            this.socket.setSoLinger(true, 0);
        } catch (SocketException unused) {
        }
        close();
    }

    @Override // io.nats.client.impl.DataPort
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.in.read(bArr, i, i2);
    }

    @Override // io.nats.client.impl.DataPort
    public void shutdownInput() throws IOException {
        if (this.isSecure) {
            return;
        }
        this.socket.shutdownInput();
    }

    @Override // io.nats.client.impl.DataPort
    public void upgradeToSecure() throws IOException {
        Options options = this.connection.getOptions();
        SSLSocketFactory socketFactory = options.getSslContext().getSocketFactory();
        Duration connectionTimeout = options.getConnectionTimeout();
        SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(this.socket, this.host, this.port, true);
        sSLSocket.setUseClientMode(true);
        final CompletableFuture completableFuture = new CompletableFuture();
        sSLSocket.addHandshakeCompletedListener(new HandshakeCompletedListener() { // from class: ew0
            @Override // javax.net.ssl.HandshakeCompletedListener
            public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
                completableFuture.complete(null);
            }
        });
        sSLSocket.startHandshake();
        try {
            completableFuture.get(connectionTimeout.toNanos(), TimeUnit.NANOSECONDS);
            this.socket = sSLSocket;
            this.in = sSLSocket.getInputStream();
            this.out = sSLSocket.getOutputStream();
            this.isSecure = true;
        } catch (Exception e) {
            this.connection.handleCommunicationIssue(e);
        }
    }

    @Override // io.nats.client.impl.DataPort
    public void write(byte[] bArr, int i) throws IOException {
        this.out.write(bArr, 0, i);
    }

    @Override // io.nats.client.impl.DataPort
    public void connect(NatsConnection natsConnection, NatsUri natsUri, long j) throws IOException {
        this.connection = natsConnection;
        Options options = natsConnection.getOptions();
        long j2 = j / NatsConstants.NANOS_PER_MILLI;
        this.host = natsUri.getHost();
        this.port = natsUri.getPort();
        try {
            if (options.getProxy() != null) {
                this.socket = new Socket(options.getProxy());
            } else {
                this.socket = new Socket();
            }
            this.socket.setTcpNoDelay(true);
            this.socket.setReceiveBufferSize(2097152);
            this.socket.setSendBufferSize(2097152);
            this.socket.connect(new InetSocketAddress(this.host, this.port), (int) j2);
            int i = this.soLinger;
            if (i > -1) {
                this.socket.setSoLinger(true, i);
            }
            if (options.getSocketReadTimeoutMillis() > 0) {
                this.socket.setSoTimeout(options.getSocketReadTimeoutMillis());
            }
            if (isWebsocketScheme(natsUri.getScheme())) {
                if (NatsConstants.SECURE_WEBSOCKET_PROTOCOL.equalsIgnoreCase(natsUri.getScheme())) {
                    upgradeToSecure();
                }
                try {
                    this.socket = new WebSocket(this.socket, this.host, options.getHttpRequestInterceptors());
                } catch (Exception e) {
                    this.socket.close();
                    throw e;
                }
            }
            this.in = this.socket.getInputStream();
            this.out = this.socket.getOutputStream();
        } catch (Exception e2) {
            try {
                this.socket.close();
            } catch (Exception unused) {
            }
            this.socket = null;
            if (e2 instanceof IOException) {
                throw e2;
            }
            throw new IOException(e2);
        }
    }
}
