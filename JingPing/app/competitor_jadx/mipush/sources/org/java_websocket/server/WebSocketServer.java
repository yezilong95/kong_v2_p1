package org.java_websocket.server;

import defpackage.ua0;
import defpackage.wa0;
import java.io.IOException;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.SocketChannelIOHelper;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.exceptions.WrappedIOException;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;

/* JADX INFO: loaded from: classes.dex */
public abstract class WebSocketServer extends AbstractWebSocket implements Runnable {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    protected List<WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private final AtomicBoolean isclosed;
    private final ua0 log;
    private int maxPendingConnections;
    private int queueinvokes;
    private final AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServerFactory wsf;

    public class WebSocketWorker extends Thread {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();

        public WebSocketWorker() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: org.java_websocket.server.WebSocketServer.WebSocketWorker.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    WebSocketServer.this.log.error("Uncaught exception in thread {}: {}", thread.getName(), th);
                }
            });
        }

        private void doDecode(WebSocketImpl webSocketImpl, ByteBuffer byteBuffer) throws InterruptedException {
            try {
                webSocketImpl.decode(byteBuffer);
            } catch (Exception e) {
                WebSocketServer.this.log.error("Error while reading from remote connection", (Throwable) e);
            } finally {
                WebSocketServer.this.pushBuffer(byteBuffer);
            }
        }

        public void put(WebSocketImpl webSocketImpl) throws InterruptedException {
            this.iqueue.put(webSocketImpl);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WebSocketImpl webSocketImplTake;
            Throwable th;
            Throwable e;
            while (true) {
                try {
                    try {
                        webSocketImplTake = this.iqueue.take();
                        try {
                            doDecode(webSocketImplTake, webSocketImplTake.inQueue.poll());
                        } catch (LinkageError e2) {
                            e = e2;
                            WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                            WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                            return;
                        } catch (ThreadDeath e3) {
                            e = e3;
                            WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                            WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                            return;
                        } catch (VirtualMachineError e4) {
                            e = e4;
                            WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                            WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            WebSocketServer.this.log.error("Uncaught exception in thread {}: {}", getName(), th);
                            if (webSocketImplTake != null) {
                                WebSocketServer.this.onWebsocketError(webSocketImplTake, new Exception(th));
                                webSocketImplTake.close();
                                return;
                            }
                            return;
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                } catch (LinkageError e5) {
                    e = e5;
                    Throwable th3 = e;
                    webSocketImplTake = null;
                    e = th3;
                    WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                    WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                    return;
                } catch (ThreadDeath e6) {
                    e = e6;
                    Throwable th32 = e;
                    webSocketImplTake = null;
                    e = th32;
                    WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                    WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                    return;
                } catch (VirtualMachineError e7) {
                    e = e7;
                    Throwable th322 = e;
                    webSocketImplTake = null;
                    e = th322;
                    WebSocketServer.this.log.error("Got fatal error in worker thread {}", getName());
                    WebSocketServer.this.handleFatal(webSocketImplTake, new Exception(e));
                    return;
                } catch (Throwable th4) {
                    webSocketImplTake = null;
                    th = th4;
                }
            }
        }
    }

    public WebSocketServer() {
        this(new InetSocketAddress(80), AVAILABLE_PROCESSORS, null);
    }

    private void doAccept(SelectionKey selectionKey, Iterator<SelectionKey> it) throws InterruptedException, IOException {
        if (!onConnect(selectionKey)) {
            selectionKey.cancel();
            return;
        }
        SocketChannel socketChannelAccept = this.server.accept();
        if (socketChannelAccept == null) {
            return;
        }
        socketChannelAccept.configureBlocking(false);
        Socket socket = socketChannelAccept.socket();
        socket.setTcpNoDelay(isTcpNoDelay());
        socket.setKeepAlive(true);
        WebSocketImpl webSocketImplCreateWebSocket = this.wsf.createWebSocket((WebSocketAdapter) this, this.drafts);
        webSocketImplCreateWebSocket.setSelectionKey(socketChannelAccept.register(this.selector, 1, webSocketImplCreateWebSocket));
        try {
            webSocketImplCreateWebSocket.setChannel(this.wsf.wrapChannel(socketChannelAccept, webSocketImplCreateWebSocket.getSelectionKey()));
            it.remove();
            allocateBuffers(webSocketImplCreateWebSocket);
        } catch (IOException e) {
            if (webSocketImplCreateWebSocket.getSelectionKey() != null) {
                webSocketImplCreateWebSocket.getSelectionKey().cancel();
            }
            handleIOException(webSocketImplCreateWebSocket.getSelectionKey(), null, e);
        }
    }

    private void doAdditionalRead() throws InterruptedException, IOException {
        while (!this.iqueue.isEmpty()) {
            WebSocketImpl webSocketImplRemove = this.iqueue.remove(0);
            WrappedByteChannel wrappedByteChannel = (WrappedByteChannel) webSocketImplRemove.getChannel();
            ByteBuffer byteBufferTakeBuffer = takeBuffer();
            try {
                if (SocketChannelIOHelper.readMore(byteBufferTakeBuffer, webSocketImplRemove, wrappedByteChannel)) {
                    this.iqueue.add(webSocketImplRemove);
                }
                if (byteBufferTakeBuffer.hasRemaining()) {
                    webSocketImplRemove.inQueue.put(byteBufferTakeBuffer);
                    queue(webSocketImplRemove);
                } else {
                    pushBuffer(byteBufferTakeBuffer);
                }
            } catch (IOException e) {
                pushBuffer(byteBufferTakeBuffer);
                throw e;
            }
        }
    }

    private void doBroadcast(Object obj, Collection<WebSocket> collection) {
        ArrayList<WebSocket> arrayList;
        String str = obj instanceof String ? (String) obj : null;
        ByteBuffer byteBuffer = obj instanceof ByteBuffer ? (ByteBuffer) obj : null;
        if (str == null && byteBuffer == null) {
            return;
        }
        HashMap map = new HashMap();
        synchronized (collection) {
            arrayList = new ArrayList(collection);
        }
        for (WebSocket webSocket : arrayList) {
            if (webSocket != null) {
                Draft draft = webSocket.getDraft();
                fillFrames(draft, map, str, byteBuffer);
                try {
                    webSocket.sendFrame((Collection<Framedata>) map.get(draft));
                } catch (WebsocketNotConnectedException unused) {
                }
            }
        }
    }

    private boolean doEnsureSingleThread() {
        synchronized (this) {
            try {
                if (this.selectorthread != null) {
                    throw new IllegalStateException(getClass().getName().concat(" can only be started once."));
                }
                this.selectorthread = Thread.currentThread();
                return !this.isclosed.get();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean doRead(SelectionKey selectionKey, Iterator<SelectionKey> it) throws WrappedIOException, InterruptedException {
        WebSocketImpl webSocketImpl = (WebSocketImpl) selectionKey.attachment();
        ByteBuffer byteBufferTakeBuffer = takeBuffer();
        if (webSocketImpl.getChannel() == null) {
            selectionKey.cancel();
            handleIOException(selectionKey, webSocketImpl, new IOException());
            return false;
        }
        try {
            if (!SocketChannelIOHelper.read(byteBufferTakeBuffer, webSocketImpl, webSocketImpl.getChannel())) {
                pushBuffer(byteBufferTakeBuffer);
                return true;
            }
            if (!byteBufferTakeBuffer.hasRemaining()) {
                pushBuffer(byteBufferTakeBuffer);
                return true;
            }
            webSocketImpl.inQueue.put(byteBufferTakeBuffer);
            queue(webSocketImpl);
            it.remove();
            if (!(webSocketImpl.getChannel() instanceof WrappedByteChannel) || !((WrappedByteChannel) webSocketImpl.getChannel()).isNeedRead()) {
                return true;
            }
            this.iqueue.add(webSocketImpl);
            return true;
        } catch (IOException e) {
            pushBuffer(byteBufferTakeBuffer);
            throw new WrappedIOException(webSocketImpl, e);
        }
    }

    private void doServerShutdown() {
        stopConnectionLostTimer();
        List<WebSocketWorker> list = this.decoders;
        if (list != null) {
            Iterator<WebSocketWorker> it = list.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        Selector selector = this.selector;
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                this.log.error("IOException during selector.close", (Throwable) e);
                onError(null, e);
            }
        }
        ServerSocketChannel serverSocketChannel = this.server;
        if (serverSocketChannel != null) {
            try {
                serverSocketChannel.close();
            } catch (IOException e2) {
                this.log.error("IOException during server.close", (Throwable) e2);
                onError(null, e2);
            }
        }
    }

    private boolean doSetupSelectorAndServerThread() {
        this.selectorthread.setName("WebSocketSelector-" + this.selectorthread.getId());
        try {
            ServerSocketChannel serverSocketChannelOpen = ServerSocketChannel.open();
            this.server = serverSocketChannelOpen;
            serverSocketChannelOpen.configureBlocking(false);
            ServerSocket serverSocketSocket = this.server.socket();
            serverSocketSocket.setReceiveBufferSize(WebSocketImpl.RCVBUF);
            serverSocketSocket.setReuseAddress(isReuseAddr());
            serverSocketSocket.bind(this.address, getMaxPendingConnections());
            Selector selectorOpen = Selector.open();
            this.selector = selectorOpen;
            ServerSocketChannel serverSocketChannel = this.server;
            serverSocketChannel.register(selectorOpen, serverSocketChannel.validOps());
            startConnectionLostTimer();
            Iterator<WebSocketWorker> it = this.decoders.iterator();
            while (it.hasNext()) {
                it.next().start();
            }
            onStart();
            return true;
        } catch (IOException e) {
            handleFatal(null, e);
            return false;
        }
    }

    private void doWrite(SelectionKey selectionKey) throws WrappedIOException {
        WebSocketImpl webSocketImpl = (WebSocketImpl) selectionKey.attachment();
        try {
            if (SocketChannelIOHelper.batch(webSocketImpl, webSocketImpl.getChannel()) && selectionKey.isValid()) {
                selectionKey.interestOps(1);
            }
        } catch (IOException e) {
            throw new WrappedIOException(webSocketImpl, e);
        }
    }

    private void fillFrames(Draft draft, Map<Draft, List<Framedata>> map, String str, ByteBuffer byteBuffer) {
        if (map.containsKey(draft)) {
            return;
        }
        List<Framedata> listCreateFrames = str != null ? draft.createFrames(str, false) : null;
        if (byteBuffer != null) {
            listCreateFrames = draft.createFrames(byteBuffer, false);
        }
        if (listCreateFrames != null) {
            map.put(draft, listCreateFrames);
        }
    }

    private Socket getSocket(WebSocket webSocket) {
        return ((SocketChannel) ((WebSocketImpl) webSocket).getSelectionKey().channel()).socket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFatal(WebSocket webSocket, Exception exc) {
        this.log.error("Shutdown due to fatal error", (Throwable) exc);
        onError(webSocket, exc);
        try {
            stop(0, "Got error on server side: " + exc.getClass().getName() + (exc.getCause() != null ? " caused by ".concat(exc.getCause().getClass().getName()) : ""));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.log.error("Interrupt during stop", (Throwable) exc);
            onError(null, e);
        }
        List<WebSocketWorker> list = this.decoders;
        if (list != null) {
            Iterator<WebSocketWorker> it = list.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        Thread thread = this.selectorthread;
        if (thread != null) {
            thread.interrupt();
        }
    }

    private void handleIOException(SelectionKey selectionKey, WebSocket webSocket, IOException iOException) {
        SelectableChannel selectableChannelChannel;
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        if (webSocket != null) {
            webSocket.closeConnection(CloseFrame.ABNORMAL_CLOSE, iOException.getMessage());
        } else {
            if (selectionKey == null || (selectableChannelChannel = selectionKey.channel()) == null || !selectableChannelChannel.isOpen()) {
                return;
            }
            try {
                selectableChannelChannel.close();
            } catch (IOException unused) {
            }
            this.log.trace("Connection closed because of exception", (Throwable) iOException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushBuffer(ByteBuffer byteBuffer) throws InterruptedException {
        if (this.buffers.size() > this.queuesize.intValue()) {
            return;
        }
        this.buffers.put(byteBuffer);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return this.buffers.take();
    }

    public boolean addConnection(WebSocket webSocket) {
        boolean zAdd;
        if (this.isclosed.get()) {
            webSocket.close(CloseFrame.GOING_AWAY);
            return true;
        }
        synchronized (this.connections) {
            zAdd = this.connections.add(webSocket);
        }
        return zAdd;
    }

    public void allocateBuffers(WebSocket webSocket) throws InterruptedException {
        if (this.queuesize.get() >= (this.decoders.size() * 2) + 1) {
            return;
        }
        this.queuesize.incrementAndGet();
        this.buffers.put(createBuffer());
    }

    public void broadcast(String str) {
        broadcast(str, this.connections);
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(WebSocketImpl.RCVBUF);
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    @Override // org.java_websocket.AbstractWebSocket
    public Collection<WebSocket> getConnections() {
        Collection<WebSocket> collectionUnmodifiableCollection;
        synchronized (this.connections) {
            collectionUnmodifiableCollection = Collections.unmodifiableCollection(new ArrayList(this.connections));
        }
        return collectionUnmodifiableCollection;
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getLocalSocketAddress();
    }

    public int getMaxPendingConnections() {
        return this.maxPendingConnections;
    }

    public int getPort() {
        ServerSocketChannel serverSocketChannel;
        int port = getAddress().getPort();
        return (port != 0 || (serverSocketChannel = this.server) == null) ? port : serverSocketChannel.socket().getLocalPort();
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getRemoteSocketAddress();
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public void onCloseInitiated(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str, boolean z) {
    }

    public boolean onConnect(SelectionKey selectionKey) {
        return true;
    }

    public abstract void onError(WebSocket webSocket, Exception exc);

    public abstract void onMessage(WebSocket webSocket, String str);

    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    public abstract void onStart();

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.selector.wakeup();
        try {
            if (removeConnection(webSocket)) {
                onClose(webSocket, i, str, z);
            }
        } finally {
            try {
                releaseBuffers(webSocket);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(webSocket, i, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(webSocket, i, str, z);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(webSocket, exc);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(webSocket, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        if (addConnection(webSocket)) {
            onOpen(webSocket, (ClientHandshake) handshakedata);
        }
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWriteDemand(WebSocket webSocket) {
        WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
        try {
            webSocketImpl.getSelectionKey().interestOps(5);
        } catch (CancelledKeyException unused) {
            webSocketImpl.outQueue.clear();
        }
        this.selector.wakeup();
    }

    public void queue(WebSocketImpl webSocketImpl) throws InterruptedException {
        if (webSocketImpl.getWorkerThread() == null) {
            List<WebSocketWorker> list = this.decoders;
            webSocketImpl.setWorkerThread(list.get(this.queueinvokes % list.size()));
            this.queueinvokes++;
        }
        webSocketImpl.getWorkerThread().put(webSocketImpl);
    }

    public void releaseBuffers(WebSocket webSocket) throws InterruptedException {
    }

    public boolean removeConnection(WebSocket webSocket) {
        boolean zRemove;
        synchronized (this.connections) {
            try {
                if (this.connections.contains(webSocket)) {
                    zRemove = this.connections.remove(webSocket);
                } else {
                    this.log.trace("Removing connection which is not in the connections collection! Possible no handshake received! {}", webSocket);
                    zRemove = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.isclosed.get() && this.connections.isEmpty()) {
            this.selectorthread.interrupt();
        }
        return zRemove;
    }

    @Override // java.lang.Runnable
    public void run() {
        SelectionKey selectionKey;
        if (doEnsureSingleThread() && doSetupSelectorAndServerThread()) {
            int i = 0;
            int i2 = 5;
            while (!this.selectorthread.isInterrupted() && i2 != 0) {
                try {
                    try {
                        try {
                            try {
                                if (this.isclosed.get()) {
                                    i = 5;
                                }
                                if (this.selector.select(i) == 0 && this.isclosed.get()) {
                                    i2--;
                                }
                                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
                                selectionKey = null;
                                while (it.hasNext()) {
                                    try {
                                        SelectionKey next = it.next();
                                        try {
                                            if (next.isValid()) {
                                                if (next.isAcceptable()) {
                                                    doAccept(next, it);
                                                } else if ((!next.isReadable() || doRead(next, it)) && next.isWritable()) {
                                                    doWrite(next);
                                                }
                                            }
                                            selectionKey = next;
                                        } catch (IOException e) {
                                            e = e;
                                            selectionKey = next;
                                            handleIOException(selectionKey, null, e);
                                        } catch (WrappedIOException e2) {
                                            e = e2;
                                            selectionKey = next;
                                            handleIOException(selectionKey, e.getConnection(), e.getIOException());
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                    } catch (WrappedIOException e4) {
                                        e = e4;
                                    }
                                }
                                doAdditionalRead();
                            } catch (IOException e5) {
                                e = e5;
                                selectionKey = null;
                            } catch (WrappedIOException e6) {
                                e = e6;
                                selectionKey = null;
                            }
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        } catch (CancelledKeyException unused2) {
                        } catch (ClosedByInterruptException unused3) {
                            doServerShutdown();
                            return;
                        }
                    } catch (RuntimeException e7) {
                        handleFatal(null, e7);
                    }
                } catch (Throwable th) {
                    doServerShutdown();
                    throw th;
                }
            }
            doServerShutdown();
        }
    }

    public void setMaxPendingConnections(int i) {
        this.maxPendingConnections = i;
    }

    public final void setWebSocketFactory(WebSocketServerFactory webSocketServerFactory) {
        WebSocketServerFactory webSocketServerFactory2 = this.wsf;
        if (webSocketServerFactory2 != null) {
            webSocketServerFactory2.close();
        }
        this.wsf = webSocketServerFactory;
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(getClass().getName().concat(" can only be started once."));
        }
        new Thread(this).start();
    }

    public void stop(int i) throws InterruptedException {
        stop(i, "");
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, AVAILABLE_PROCESSORS, null);
    }

    public void broadcast(byte[] bArr) {
        broadcast(bArr, this.connections);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(webSocket, byteBuffer);
    }

    public void stop(int i, String str) throws InterruptedException {
        ArrayList arrayList;
        Selector selector;
        if (this.isclosed.compareAndSet(false, true)) {
            synchronized (this.connections) {
                arrayList = new ArrayList(this.connections);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((WebSocket) it.next()).close(CloseFrame.GOING_AWAY, str);
            }
            this.wsf.close();
            synchronized (this) {
                try {
                    if (this.selectorthread != null && (selector = this.selector) != null) {
                        selector.wakeup();
                        this.selectorthread.join(i);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, null);
    }

    public void broadcast(ByteBuffer byteBuffer) {
        broadcast(byteBuffer, this.connections);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, List<Draft> list) {
        this(inetSocketAddress, AVAILABLE_PROCESSORS, list);
    }

    public void broadcast(byte[] bArr, Collection<WebSocket> collection) {
        if (bArr != null && collection != null) {
            broadcast(ByteBuffer.wrap(bArr), collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list, Collection<WebSocket> collection) {
        this.log = wa0.b(WebSocketServer.class);
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        this.maxPendingConnections = -1;
        if (inetSocketAddress != null && i >= 1 && collection != null) {
            if (list == null) {
                this.drafts = Collections.EMPTY_LIST;
            } else {
                this.drafts = list;
            }
            this.address = inetSocketAddress;
            this.connections = collection;
            setTcpNoDelay(false);
            setReuseAddr(false);
            this.iqueue = new LinkedList();
            this.decoders = new ArrayList(i);
            this.buffers = new LinkedBlockingQueue();
            for (int i2 = 0; i2 < i; i2++) {
                this.decoders.add(new WebSocketWorker());
            }
            return;
        }
        throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
    }

    public void broadcast(ByteBuffer byteBuffer, Collection<WebSocket> collection) {
        if (byteBuffer != null && collection != null) {
            doBroadcast(byteBuffer, collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void broadcast(String str, Collection<WebSocket> collection) {
        if (str != null && collection != null) {
            doBroadcast(str, collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void stop() throws InterruptedException {
        stop(0);
    }
}
