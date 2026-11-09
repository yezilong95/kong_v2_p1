package org.java_websocket.client;

import defpackage.hk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.extensions.ExtensionRequestData;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.protocols.IProtocol;

/* JADX INFO: loaded from: classes.dex */
public abstract class WebSocketClient extends AbstractWebSocket implements Runnable, WebSocket {
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private Thread connectReadThread;
    private int connectTimeout;
    private DnsResolver dnsResolver;
    private Draft draft;
    private WebSocketImpl engine;
    private Map<String, String> headers;
    private OutputStream ostream;
    private Proxy proxy;
    private Socket socket;
    private SocketFactory socketFactory;
    protected URI uri;
    private Thread writeThread;

    public class WebsocketWriteThread implements Runnable {
        private final WebSocketClient webSocketClient;

        public WebsocketWriteThread(WebSocketClient webSocketClient) {
            this.webSocketClient = webSocketClient;
        }

        private void closeSocket() {
            try {
                if (WebSocketClient.this.socket != null) {
                    WebSocketClient.this.socket.close();
                }
            } catch (IOException e) {
                WebSocketClient.this.onWebsocketError(this.webSocketClient, e);
            }
        }

        private void runWriteData() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer byteBufferTake = WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(byteBufferTake.array(), 0, byteBufferTake.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : WebSocketClient.this.engine.outQueue) {
                        WebSocketClient.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                        WebSocketClient.this.ostream.flush();
                    }
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                runWriteData();
            } catch (IOException e) {
                WebSocketClient.this.handleIOException(e);
            } finally {
                closeSocket();
                WebSocketClient.this.writeThread = null;
            }
        }
    }

    public WebSocketClient(URI uri) {
        this(uri, new Draft_6455());
    }

    private int getPort() {
        int port = this.uri.getPort();
        String scheme = this.uri.getScheme();
        if ("wss".equals(scheme)) {
            return port == -1 ? WebSocketImpl.DEFAULT_WSS_PORT : port;
        }
        if (!"ws".equals(scheme)) {
            throw new IllegalArgumentException(hk.n("unknown scheme: ", scheme));
        }
        if (port == -1) {
            return 80;
        }
        return port;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIOException(IOException iOException) {
        if (iOException instanceof SSLException) {
            onError(iOException);
        }
        this.engine.eot();
    }

    private boolean prepareSocket() throws IOException {
        if (this.proxy != Proxy.NO_PROXY) {
            this.socket = new Socket(this.proxy);
            return true;
        }
        SocketFactory socketFactory = this.socketFactory;
        if (socketFactory != null) {
            this.socket = socketFactory.createSocket();
            return false;
        }
        Socket socket = this.socket;
        if (socket == null) {
            this.socket = new Socket(this.proxy);
            return true;
        }
        if (socket.isClosed()) {
            throw new IOException();
        }
        return false;
    }

    private void reset() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread == this.writeThread || threadCurrentThread == this.connectReadThread) {
            throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to ensure a successful cleanup.");
        }
        try {
            closeBlocking();
            Thread thread = this.writeThread;
            if (thread != null) {
                thread.interrupt();
                this.writeThread = null;
            }
            Thread thread2 = this.connectReadThread;
            if (thread2 != null) {
                thread2.interrupt();
                this.connectReadThread = null;
            }
            this.draft.reset();
            Socket socket = this.socket;
            if (socket != null) {
                socket.close();
                this.socket = null;
            }
            this.connectLatch = new CountDownLatch(1);
            this.closeLatch = new CountDownLatch(1);
            this.engine = new WebSocketImpl(this, this.draft);
        } catch (Exception e) {
            onError(e);
            this.engine.closeConnection(CloseFrame.ABNORMAL_CLOSE, e.getMessage());
        }
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String rawPath = this.uri.getRawPath();
        String rawQuery = this.uri.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        sb.append((port == 80 || port == 443) ? ExtensionRequestData.EMPTY_VALUE : hk.g(port, ":"));
        String string = sb.toString();
        HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(rawPath);
        handshakeImpl1Client.put("Host", string);
        Map<String, String> map = this.headers;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                handshakeImpl1Client.put(entry.getKey(), entry.getValue());
            }
        }
        this.engine.startHandshake(handshakeImpl1Client);
    }

    private void upgradeSocketToSSL() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        SSLSocketFactory socketFactory;
        SocketFactory socketFactory2 = this.socketFactory;
        if (socketFactory2 instanceof SSLSocketFactory) {
            socketFactory = (SSLSocketFactory) socketFactory2;
        } else {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            sSLContext.init(null, null, null);
            socketFactory = sSLContext.getSocketFactory();
        }
        this.socket = socketFactory.createSocket(this.socket, this.uri.getHost(), getPort(), true);
    }

    public void addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        }
        this.headers.put(str, str2);
    }

    public void clearHeaders() {
        this.headers = null;
    }

    @Override // org.java_websocket.WebSocket
    public void close() {
        if (this.writeThread != null) {
            this.engine.close(CloseFrame.NORMAL);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    @Override // org.java_websocket.WebSocket
    public void closeConnection(int i, String str) {
        this.engine.closeConnection(i, str);
    }

    public void connect() {
        if (this.connectReadThread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.connectReadThread = thread;
        thread.setName("WebSocketConnectReadThread-" + this.connectReadThread.getId());
        this.connectReadThread.start();
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    @Override // org.java_websocket.WebSocket
    public <T> T getAttachment() {
        return (T) this.engine.getAttachment();
    }

    public WebSocket getConnection() {
        return this.engine;
    }

    @Override // org.java_websocket.AbstractWebSocket
    public Collection<WebSocket> getConnections() {
        return Collections.singletonList(this.engine);
    }

    @Override // org.java_websocket.WebSocket
    public Draft getDraft() {
        return this.draft;
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        Socket socket = this.socket;
        if (socket != null) {
            return (InetSocketAddress) socket.getLocalSocketAddress();
        }
        return null;
    }

    @Override // org.java_websocket.WebSocket
    public IProtocol getProtocol() {
        return this.engine.getProtocol();
    }

    @Override // org.java_websocket.WebSocket
    public ReadyState getReadyState() {
        return this.engine.getReadyState();
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        Socket socket = this.socket;
        if (socket != null) {
            return (InetSocketAddress) socket.getRemoteSocketAddress();
        }
        return null;
    }

    @Override // org.java_websocket.WebSocket
    public String getResourceDescriptor() {
        return this.uri.getPath();
    }

    @Override // org.java_websocket.WebSocket
    public SSLSession getSSLSession() {
        if (hasSSLSupport()) {
            return ((SSLSocket) this.socket).getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    public Socket getSocket() {
        return this.socket;
    }

    public URI getURI() {
        return this.uri;
    }

    @Override // org.java_websocket.WebSocket
    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    @Override // org.java_websocket.WebSocket
    public boolean hasSSLSupport() {
        return this.socket instanceof SSLSocket;
    }

    @Override // org.java_websocket.WebSocket
    public boolean isClosed() {
        return this.engine.isClosed();
    }

    @Override // org.java_websocket.WebSocket
    public boolean isClosing() {
        return this.engine.isClosing();
    }

    @Override // org.java_websocket.WebSocket
    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    @Override // org.java_websocket.WebSocket
    public boolean isOpen() {
        return this.engine.isOpen();
    }

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    public void onSetSSLParameters(SSLParameters sSLParameters) {
        sSLParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        stopConnectionLostTimer();
        Thread thread = this.writeThread;
        if (thread != null) {
            thread.interrupt();
        }
        onClose(i, str, z);
        this.connectLatch.countDown();
        this.closeLatch.countDown();
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(exc);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        startConnectionLostTimer();
        onOpen((ServerHandshake) handshakedata);
        this.connectLatch.countDown();
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWriteDemand(WebSocket webSocket) {
    }

    public void reconnect() {
        reset();
        connect();
    }

    public boolean reconnectBlocking() throws InterruptedException {
        reset();
        return connectBlocking();
    }

    public String removeHeader(String str) {
        Map<String, String> map = this.headers;
        if (map == null) {
            return null;
        }
        return map.remove(str);
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        int i;
        try {
            boolean zPrepareSocket = prepareSocket();
            this.socket.setTcpNoDelay(isTcpNoDelay());
            this.socket.setReuseAddress(isReuseAddr());
            if (!this.socket.isConnected()) {
                this.socket.connect(this.dnsResolver == null ? InetSocketAddress.createUnresolved(this.uri.getHost(), getPort()) : new InetSocketAddress(this.dnsResolver.resolve(this.uri), getPort()), this.connectTimeout);
            }
            if (zPrepareSocket && "wss".equals(this.uri.getScheme())) {
                upgradeSocketToSSL();
            }
            Socket socket = this.socket;
            if (socket instanceof SSLSocket) {
                SSLSocket sSLSocket = (SSLSocket) socket;
                SSLParameters sSLParameters = sSLSocket.getSSLParameters();
                onSetSSLParameters(sSLParameters);
                sSLSocket.setSSLParameters(sSLParameters);
            }
            InputStream inputStream = this.socket.getInputStream();
            this.ostream = this.socket.getOutputStream();
            sendHandshake();
            Thread thread = new Thread(new WebsocketWriteThread(this));
            this.writeThread = thread;
            thread.start();
            byte[] bArr = new byte[WebSocketImpl.RCVBUF];
            while (!isClosing() && !isClosed() && (i = inputStream.read(bArr)) != -1) {
                try {
                    this.engine.decode(ByteBuffer.wrap(bArr, 0, i));
                } catch (IOException e) {
                    handleIOException(e);
                } catch (RuntimeException e2) {
                    onError(e2);
                    this.engine.closeConnection(CloseFrame.ABNORMAL_CLOSE, e2.getMessage());
                }
            }
            this.engine.eot();
            this.connectReadThread = null;
        } catch (Exception e3) {
            onWebsocketError(this.engine, e3);
            this.engine.closeConnection(-1, e3.getMessage());
        } catch (InternalError e4) {
            if (!(e4.getCause() instanceof InvocationTargetException) || !(e4.getCause().getCause() instanceof IOException)) {
                throw e4;
            }
            IOException iOException = (IOException) e4.getCause().getCause();
            onWebsocketError(this.engine, iOException);
            this.engine.closeConnection(-1, iOException.getMessage());
        }
    }

    @Override // org.java_websocket.WebSocket
    public void send(String str) {
        this.engine.send(str);
    }

    @Override // org.java_websocket.WebSocket
    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        this.engine.sendFragmentedFrame(opcode, byteBuffer, z);
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    @Override // org.java_websocket.WebSocket
    public void sendPing() {
        this.engine.sendPing();
    }

    @Override // org.java_websocket.WebSocket
    public <T> void setAttachment(T t) {
        this.engine.setAttachment(t);
    }

    public void setDnsResolver(DnsResolver dnsResolver) {
        this.dnsResolver = dnsResolver;
    }

    public void setProxy(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException();
        }
        this.proxy = proxy;
    }

    @Deprecated
    public void setSocket(Socket socket) {
        if (this.socket != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.socket = socket;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public WebSocketClient(URI uri, Draft draft) {
        this(uri, draft, null, 0);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    @Override // org.java_websocket.WebSocket
    public void send(byte[] bArr) {
        this.engine.send(bArr);
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Collection<Framedata> collection) {
        this.engine.sendFrame(collection);
    }

    public WebSocketClient(URI uri, Map<String, String> map) {
        this(uri, new Draft_6455(), map);
    }

    @Override // org.java_websocket.WebSocket
    public void close(int i) {
        this.engine.close(i);
    }

    @Override // org.java_websocket.WebSocket
    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    @Override // org.java_websocket.WebSocket
    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    @Override // org.java_websocket.WebSocket
    public void send(ByteBuffer byteBuffer) {
        this.engine.send(byteBuffer);
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map) {
        this(uri, draft, map, 0);
    }

    @Override // org.java_websocket.WebSocket
    public void close(int i, String str) {
        this.engine.close(i, str);
    }

    public boolean connectBlocking(long j, TimeUnit timeUnit) throws InterruptedException {
        connect();
        return this.connectLatch.await(j, timeUnit) && this.engine.isOpen();
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.socketFactory = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        this.dnsResolver = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (draft != null) {
            this.uri = uri;
            this.draft = draft;
            this.dnsResolver = new DnsResolver() { // from class: org.java_websocket.client.WebSocketClient.1
                @Override // org.java_websocket.client.DnsResolver
                public InetAddress resolve(URI uri2) throws UnknownHostException {
                    return InetAddress.getByName(uri2.getHost());
                }
            };
            if (map != null) {
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                this.headers = treeMap;
                treeMap.putAll(map);
            }
            this.connectTimeout = i;
            setTcpNoDelay(false);
            setReuseAddr(false);
            this.engine = new WebSocketImpl(this, draft);
            return;
        }
        throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
    }
}
