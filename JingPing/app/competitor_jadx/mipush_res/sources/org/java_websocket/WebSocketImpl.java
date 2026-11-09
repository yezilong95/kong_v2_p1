package org.java_websocket;

import defpackage.ua0;
import defpackage.wa0;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.Status;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.SSLSession;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExceededException;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: classes.dex */
public class WebSocketImpl implements WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_WSS_PORT = 443;
    public static final int RCVBUF = 16384;
    private Object attachment;
    private ByteChannel channel;
    private Integer closecode;
    private Boolean closedremotely;
    private String closemessage;
    private Draft draft;
    private boolean flushandclosestate;
    private ClientHandshake handshakerequest;
    public final BlockingQueue<ByteBuffer> inQueue;
    private SelectionKey key;
    private List<Draft> knownDrafts;
    private long lastPong;
    private final ua0 log;
    public final BlockingQueue<ByteBuffer> outQueue;
    private volatile ReadyState readyState;
    private String resourceDescriptor;
    private Role role;
    private final Object synchronizeWriteObject;
    private ByteBuffer tmpHandshakeBytes;
    private WebSocketServer.WebSocketWorker workerThread;
    private final WebSocketListener wsl;

    public WebSocketImpl(WebSocketListener webSocketListener, List<Draft> list) {
        this(webSocketListener, (Draft) null);
        this.role = Role.SERVER;
        if (list != null && !list.isEmpty()) {
            this.knownDrafts = list;
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.knownDrafts = arrayList;
        arrayList.add(new Draft_6455());
    }

    private void closeConnectionDueToInternalServerError(RuntimeException runtimeException) {
        write(generateHttpResponseDueToError(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT));
        flushAndClose(-1, runtimeException.getMessage(), false);
    }

    private void closeConnectionDueToWrongHandshake(InvalidDataException invalidDataException) {
        write(generateHttpResponseDueToError(Status.NOT_FOUND_CODE));
        flushAndClose(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    private void decodeFrames(ByteBuffer byteBuffer) throws Throwable {
        try {
            for (Framedata framedata : this.draft.translateFrame(byteBuffer)) {
                this.log.trace("matched frame: {}", framedata);
                this.draft.processFrame(this, framedata);
            }
        } catch (LinkageError e) {
            e = e;
            this.log.error("Got fatal error during frame processing");
            throw e;
        } catch (ThreadDeath e2) {
            e = e2;
            this.log.error("Got fatal error during frame processing");
            throw e;
        } catch (VirtualMachineError e3) {
            e = e3;
            this.log.error("Got fatal error during frame processing");
            throw e;
        } catch (Error e4) {
            this.log.error("Closing web socket due to an error during frame processing");
            this.wsl.onWebsocketError(this, new Exception(e4));
            close(CloseFrame.UNEXPECTED_CONDITION, "Got error ".concat(e4.getClass().getName()));
        } catch (LimitExceededException e5) {
            if (e5.getLimit() == Integer.MAX_VALUE) {
                this.log.error("Closing due to invalid size of frame", (Throwable) e5);
                this.wsl.onWebsocketError(this, e5);
            }
            close(e5);
        } catch (InvalidDataException e6) {
            this.log.error("Closing due to invalid data in frame", (Throwable) e6);
            this.wsl.onWebsocketError(this, e6);
            close(e6);
        }
    }

    private boolean decodeHandshake(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        Role role;
        Handshakedata handshakedataTranslateHandshake;
        if (this.tmpHandshakeBytes.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.tmpHandshakeBytes.remaining() < byteBuffer.remaining()) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining() + this.tmpHandshakeBytes.capacity());
                this.tmpHandshakeBytes.flip();
                byteBufferAllocate.put(this.tmpHandshakeBytes);
                this.tmpHandshakeBytes = byteBufferAllocate;
            }
            this.tmpHandshakeBytes.put(byteBuffer);
            this.tmpHandshakeBytes.flip();
            byteBuffer2 = this.tmpHandshakeBytes;
        }
        byteBuffer2.mark();
        try {
            try {
                role = this.role;
            } catch (InvalidHandshakeException e) {
                this.log.trace("Closing due to invalid handshake", (Throwable) e);
                close(e);
            }
        } catch (IncompleteHandshakeException e2) {
            if (this.tmpHandshakeBytes.capacity() == 0) {
                byteBuffer2.reset();
                int preferredSize = e2.getPreferredSize();
                if (preferredSize == 0) {
                    preferredSize = byteBuffer2.capacity() + 16;
                }
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(preferredSize);
                this.tmpHandshakeBytes = byteBufferAllocate2;
                byteBufferAllocate2.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.tmpHandshakeBytes;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.tmpHandshakeBytes;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
        if (role != Role.SERVER) {
            if (role == Role.CLIENT) {
                this.draft.setParseMode(role);
                Handshakedata handshakedataTranslateHandshake2 = this.draft.translateHandshake(byteBuffer2);
                if (!(handshakedataTranslateHandshake2 instanceof ServerHandshake)) {
                    this.log.trace("Closing due to protocol error: wrong http function");
                    flushAndClose(CloseFrame.PROTOCOL_ERROR, "wrong http function", false);
                    return false;
                }
                ServerHandshake serverHandshake = (ServerHandshake) handshakedataTranslateHandshake2;
                if (this.draft.acceptHandshakeAsClient(this.handshakerequest, serverHandshake) == HandshakeState.MATCHED) {
                    try {
                        this.wsl.onWebsocketHandshakeReceivedAsClient(this, this.handshakerequest, serverHandshake);
                        open(serverHandshake);
                        return true;
                    } catch (RuntimeException e3) {
                        this.log.error("Closing since client was never connected", (Throwable) e3);
                        this.wsl.onWebsocketError(this, e3);
                        flushAndClose(-1, e3.getMessage(), false);
                        return false;
                    } catch (InvalidDataException e4) {
                        this.log.trace("Closing due to invalid data exception. Possible handshake rejection", (Throwable) e4);
                        flushAndClose(e4.getCloseCode(), e4.getMessage(), false);
                        return false;
                    }
                }
                this.log.trace("Closing due to protocol error: draft {} refuses handshake", this.draft);
                close(CloseFrame.PROTOCOL_ERROR, "draft " + this.draft + " refuses handshake");
            }
            return false;
        }
        Draft draft = this.draft;
        if (draft != null) {
            Handshakedata handshakedataTranslateHandshake3 = draft.translateHandshake(byteBuffer2);
            if (!(handshakedataTranslateHandshake3 instanceof ClientHandshake)) {
                this.log.trace("Closing due to protocol error: wrong http function");
                flushAndClose(CloseFrame.PROTOCOL_ERROR, "wrong http function", false);
                return false;
            }
            ClientHandshake clientHandshake = (ClientHandshake) handshakedataTranslateHandshake3;
            if (this.draft.acceptHandshakeAsServer(clientHandshake) == HandshakeState.MATCHED) {
                open(clientHandshake);
                return true;
            }
            this.log.trace("Closing due to protocol error: the handshake did finally not match");
            close(CloseFrame.PROTOCOL_ERROR, "the handshake did finally not match");
            return false;
        }
        Iterator<Draft> it = this.knownDrafts.iterator();
        while (it.hasNext()) {
            Draft draftCopyInstance = it.next().copyInstance();
            try {
                draftCopyInstance.setParseMode(this.role);
                byteBuffer2.reset();
                handshakedataTranslateHandshake = draftCopyInstance.translateHandshake(byteBuffer2);
            } catch (InvalidHandshakeException unused) {
            }
            if (!(handshakedataTranslateHandshake instanceof ClientHandshake)) {
                this.log.trace("Closing due to wrong handshake");
                closeConnectionDueToWrongHandshake(new InvalidDataException(CloseFrame.PROTOCOL_ERROR, "wrong http function"));
                return false;
            }
            ClientHandshake clientHandshake2 = (ClientHandshake) handshakedataTranslateHandshake;
            if (draftCopyInstance.acceptHandshakeAsServer(clientHandshake2) == HandshakeState.MATCHED) {
                this.resourceDescriptor = clientHandshake2.getResourceDescriptor();
                try {
                    write(draftCopyInstance.createHandshake(draftCopyInstance.postProcessHandshakeResponseAsServer(clientHandshake2, this.wsl.onWebsocketHandshakeReceivedAsServer(this, draftCopyInstance, clientHandshake2))));
                    this.draft = draftCopyInstance;
                    open(clientHandshake2);
                    return true;
                } catch (RuntimeException e5) {
                    this.log.error("Closing due to internal server error", (Throwable) e5);
                    this.wsl.onWebsocketError(this, e5);
                    closeConnectionDueToInternalServerError(e5);
                    return false;
                } catch (InvalidDataException e6) {
                    this.log.trace("Closing due to wrong handshake. Possible handshake rejection", (Throwable) e6);
                    closeConnectionDueToWrongHandshake(e6);
                    return false;
                }
            }
        }
        if (this.draft == null) {
            this.log.trace("Closing due to protocol error: no draft matches");
            closeConnectionDueToWrongHandshake(new InvalidDataException(CloseFrame.PROTOCOL_ERROR, "no draft matches"));
        }
        return false;
    }

    private ByteBuffer generateHttpResponseDueToError(int i) {
        String str = i != 404 ? "500 Internal Server Error" : "404 WebSocket Upgrade Failure";
        return ByteBuffer.wrap(Charsetfunctions.asciiBytes("HTTP/1.1 " + str + "\r\nContent-Type: text/html\r\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    private void open(Handshakedata handshakedata) {
        this.log.trace("open using draft: {}", this.draft);
        this.readyState = ReadyState.OPEN;
        updateLastPong();
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        } catch (RuntimeException e) {
            this.wsl.onWebsocketError(this, e);
        }
    }

    private void write(ByteBuffer byteBuffer) {
        this.log.trace("write({}): {}", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0041 A[Catch: all -> 0x001f, InvalidDataException -> 0x0033, TRY_LEAVE, TryCatch #1 {InvalidDataException -> 0x0033, blocks: (B:19:0x002d, B:25:0x003b, B:27:0x0041, B:24:0x0036), top: B:45:0x002d, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void close(int r6, java.lang.String r7, boolean r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.enums.ReadyState r1 = org.java_websocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L1f
            if (r0 == r1) goto L85
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.enums.ReadyState r2 = org.java_websocket.enums.ReadyState.CLOSED     // Catch: java.lang.Throwable -> L1f
            if (r0 == r2) goto L85
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.enums.ReadyState r2 = org.java_websocket.enums.ReadyState.OPEN     // Catch: java.lang.Throwable -> L1f
            r3 = 0
            if (r0 != r2) goto L68
            r0 = 1006(0x3ee, float:1.41E-42)
            if (r6 != r0) goto L21
            r5.readyState = r1     // Catch: java.lang.Throwable -> L1f
            r5.flushAndClose(r6, r7, r3)     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r5)
            return
        L1f:
            r6 = move-exception
            goto L87
        L21:
            org.java_websocket.drafts.Draft r1 = r5.draft     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.enums.CloseHandshakeType r1 = r1.getCloseHandshakeType()     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.enums.CloseHandshakeType r2 = org.java_websocket.enums.CloseHandshakeType.NONE     // Catch: java.lang.Throwable -> L1f
            if (r1 == r2) goto L64
            if (r8 != 0) goto L3b
            org.java_websocket.WebSocketListener r1 = r5.wsl     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33 java.lang.RuntimeException -> L35
            r1.onWebsocketCloseInitiated(r5, r6, r7)     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33 java.lang.RuntimeException -> L35
            goto L3b
        L33:
            r1 = move-exception
            goto L53
        L35:
            r1 = move-exception
            org.java_websocket.WebSocketListener r2 = r5.wsl     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r2.onWebsocketError(r5, r1)     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
        L3b:
            boolean r1 = r5.isOpen()     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            if (r1 == 0) goto L64
            org.java_websocket.framing.CloseFrame r1 = new org.java_websocket.framing.CloseFrame     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r1.<init>()     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r1.setReason(r7)     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r1.setCode(r6)     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r1.isValid()     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            r5.sendFrame(r1)     // Catch: java.lang.Throwable -> L1f org.java_websocket.exceptions.InvalidDataException -> L33
            goto L64
        L53:
            ua0 r2 = r5.log     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = "generated frame is invalid"
            r2.error(r4, r1)     // Catch: java.lang.Throwable -> L1f
            org.java_websocket.WebSocketListener r2 = r5.wsl     // Catch: java.lang.Throwable -> L1f
            r2.onWebsocketError(r5, r1)     // Catch: java.lang.Throwable -> L1f
            java.lang.String r1 = "generated frame is invalid"
            r5.flushAndClose(r0, r1, r3)     // Catch: java.lang.Throwable -> L1f
        L64:
            r5.flushAndClose(r6, r7, r8)     // Catch: java.lang.Throwable -> L1f
            goto L7c
        L68:
            r0 = -3
            if (r6 != r0) goto L70
            r6 = 1
            r5.flushAndClose(r0, r7, r6)     // Catch: java.lang.Throwable -> L1f
            goto L7c
        L70:
            r0 = 1002(0x3ea, float:1.404E-42)
            if (r6 != r0) goto L78
            r5.flushAndClose(r6, r7, r8)     // Catch: java.lang.Throwable -> L1f
            goto L7c
        L78:
            r6 = -1
            r5.flushAndClose(r6, r7, r3)     // Catch: java.lang.Throwable -> L1f
        L7c:
            org.java_websocket.enums.ReadyState r6 = org.java_websocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L1f
            r5.readyState = r6     // Catch: java.lang.Throwable -> L1f
            r6 = 0
            r5.tmpHandshakeBytes = r6     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r5)
            return
        L85:
            monitor-exit(r5)
            return
        L87:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L1f
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.WebSocketImpl.close(int, java.lang.String, boolean):void");
    }

    public synchronized void closeConnection(int i, String str, boolean z) {
        try {
            if (this.readyState == ReadyState.CLOSED) {
                return;
            }
            if (this.readyState == ReadyState.OPEN && i == 1006) {
                this.readyState = ReadyState.CLOSING;
            }
            SelectionKey selectionKey = this.key;
            if (selectionKey != null) {
                selectionKey.cancel();
            }
            ByteChannel byteChannel = this.channel;
            if (byteChannel != null) {
                try {
                    byteChannel.close();
                } catch (IOException e) {
                    if (e.getMessage() == null || !e.getMessage().equals("Broken pipe")) {
                        this.log.error("Exception during channel.close()", (Throwable) e);
                        this.wsl.onWebsocketError(this, e);
                    } else {
                        this.log.trace("Caught IOException: Broken pipe during closeConnection()", (Throwable) e);
                    }
                }
            }
            try {
                this.wsl.onWebsocketClose(this, i, str, z);
            } catch (RuntimeException e2) {
                this.wsl.onWebsocketError(this, e2);
            }
            Draft draft = this.draft;
            if (draft != null) {
                draft.reset();
            }
            this.handshakerequest = null;
            this.readyState = ReadyState.CLOSED;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void decode(ByteBuffer byteBuffer) throws Throwable {
        if (this.log.isTraceEnabled()) {
            this.log.trace("process({}): ({})", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
        }
        if (this.readyState != ReadyState.NOT_YET_CONNECTED) {
            if (this.readyState == ReadyState.OPEN) {
                decodeFrames(byteBuffer);
            }
        } else {
            if (!decodeHandshake(byteBuffer) || isClosing() || isClosed()) {
                return;
            }
            if (byteBuffer.hasRemaining()) {
                decodeFrames(byteBuffer);
            } else if (this.tmpHandshakeBytes.hasRemaining()) {
                decodeFrames(this.tmpHandshakeBytes);
            }
        }
    }

    public void eot() {
        if (this.readyState == ReadyState.NOT_YET_CONNECTED) {
            closeConnection(-1, true);
            return;
        }
        if (this.flushandclosestate) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
            return;
        }
        if (this.draft.getCloseHandshakeType() == CloseHandshakeType.NONE) {
            closeConnection(1000, true);
            return;
        }
        if (this.draft.getCloseHandshakeType() != CloseHandshakeType.ONEWAY) {
            closeConnection(CloseFrame.ABNORMAL_CLOSE, true);
        } else if (this.role == Role.SERVER) {
            closeConnection(CloseFrame.ABNORMAL_CLOSE, true);
        } else {
            closeConnection(1000, true);
        }
    }

    public synchronized void flushAndClose(int i, String str, boolean z) {
        if (this.flushandclosestate) {
            return;
        }
        this.closecode = Integer.valueOf(i);
        this.closemessage = str;
        this.closedremotely = Boolean.valueOf(z);
        this.flushandclosestate = true;
        this.wsl.onWriteDemand(this);
        try {
            this.wsl.onWebsocketClosing(this, i, str, z);
        } catch (RuntimeException e) {
            this.log.error("Exception in onWebsocketClosing", (Throwable) e);
            this.wsl.onWebsocketError(this, e);
        }
        Draft draft = this.draft;
        if (draft != null) {
            draft.reset();
        }
        this.handshakerequest = null;
    }

    @Override // org.java_websocket.WebSocket
    public <T> T getAttachment() {
        return (T) this.attachment;
    }

    public ByteChannel getChannel() {
        return this.channel;
    }

    @Override // org.java_websocket.WebSocket
    public Draft getDraft() {
        return this.draft;
    }

    public long getLastPong() {
        return this.lastPong;
    }

    @Override // org.java_websocket.WebSocket
    public InetSocketAddress getLocalSocketAddress() {
        return this.wsl.getLocalSocketAddress(this);
    }

    @Override // org.java_websocket.WebSocket
    public IProtocol getProtocol() {
        Draft draft = this.draft;
        if (draft == null) {
            return null;
        }
        if (draft instanceof Draft_6455) {
            return ((Draft_6455) draft).getProtocol();
        }
        throw new IllegalArgumentException("This draft does not support Sec-WebSocket-Protocol");
    }

    @Override // org.java_websocket.WebSocket
    public ReadyState getReadyState() {
        return this.readyState;
    }

    @Override // org.java_websocket.WebSocket
    public InetSocketAddress getRemoteSocketAddress() {
        return this.wsl.getRemoteSocketAddress(this);
    }

    @Override // org.java_websocket.WebSocket
    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }

    @Override // org.java_websocket.WebSocket
    public SSLSession getSSLSession() {
        if (hasSSLSupport()) {
            return ((ISSLChannel) this.channel).getSSLEngine().getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    public SelectionKey getSelectionKey() {
        return this.key;
    }

    public WebSocketListener getWebSocketListener() {
        return this.wsl;
    }

    public WebSocketServer.WebSocketWorker getWorkerThread() {
        return this.workerThread;
    }

    @Override // org.java_websocket.WebSocket
    public boolean hasBufferedData() {
        return !this.outQueue.isEmpty();
    }

    @Override // org.java_websocket.WebSocket
    public boolean hasSSLSupport() {
        return this.channel instanceof ISSLChannel;
    }

    @Override // org.java_websocket.WebSocket
    public boolean isClosed() {
        return this.readyState == ReadyState.CLOSED;
    }

    @Override // org.java_websocket.WebSocket
    public boolean isClosing() {
        return this.readyState == ReadyState.CLOSING;
    }

    @Override // org.java_websocket.WebSocket
    public boolean isFlushAndClose() {
        return this.flushandclosestate;
    }

    @Override // org.java_websocket.WebSocket
    public boolean isOpen() {
        return this.readyState == ReadyState.OPEN;
    }

    @Override // org.java_websocket.WebSocket
    public void send(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(str, this.role == Role.CLIENT));
    }

    @Override // org.java_websocket.WebSocket
    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        send(this.draft.continuousFrame(opcode, byteBuffer, z));
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Collection<Framedata> collection) {
        send(collection);
    }

    @Override // org.java_websocket.WebSocket
    public void sendPing() throws NullPointerException {
        PingFrame pingFrameOnPreparePing = this.wsl.onPreparePing(this);
        if (pingFrameOnPreparePing == null) {
            throw new NullPointerException("onPreparePing(WebSocket) returned null. PingFrame to sent can't be null.");
        }
        sendFrame(pingFrameOnPreparePing);
    }

    @Override // org.java_websocket.WebSocket
    public <T> void setAttachment(T t) {
        this.attachment = t;
    }

    public void setChannel(ByteChannel byteChannel) {
        this.channel = byteChannel;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.key = selectionKey;
    }

    public void setWorkerThread(WebSocketServer.WebSocketWorker webSocketWorker) {
        this.workerThread = webSocketWorker;
    }

    public void startHandshake(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        this.resourceDescriptor = clientHandshakeBuilder.getResourceDescriptor();
        try {
            this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
            write(this.draft.createHandshake(this.handshakerequest));
        } catch (RuntimeException e) {
            this.log.error("Exception in startHandshake", (Throwable) e);
            this.wsl.onWebsocketError(this, e);
            throw new InvalidHandshakeException("rejected because of " + e);
        } catch (InvalidDataException unused) {
            throw new InvalidHandshakeException("Handshake data rejected by client.");
        }
    }

    public String toString() {
        return super.toString();
    }

    public void updateLastPong() {
        this.lastPong = System.nanoTime();
    }

    @Override // org.java_websocket.WebSocket
    public void sendFrame(Framedata framedata) {
        send(Collections.singletonList(framedata));
    }

    @Override // org.java_websocket.WebSocket
    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(byteBuffer, this.role == Role.CLIENT));
    }

    @Override // org.java_websocket.WebSocket
    public void send(byte[] bArr) {
        send(ByteBuffer.wrap(bArr));
    }

    private void send(Collection<Framedata> collection) {
        if (!isOpen()) {
            throw new WebsocketNotConnectedException();
        }
        if (collection != null) {
            ArrayList arrayList = new ArrayList();
            for (Framedata framedata : collection) {
                this.log.trace("send frame: {}", framedata);
                arrayList.add(this.draft.createBinaryFrame(framedata));
            }
            write(arrayList);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void write(List<ByteBuffer> list) {
        synchronized (this.synchronizeWriteObject) {
            try {
                Iterator<ByteBuffer> it = list.iterator();
                while (it.hasNext()) {
                    write(it.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft) {
        this.log = wa0.b(WebSocketImpl.class);
        this.flushandclosestate = false;
        this.readyState = ReadyState.NOT_YET_CONNECTED;
        this.draft = null;
        this.tmpHandshakeBytes = ByteBuffer.allocate(0);
        this.handshakerequest = null;
        this.closemessage = null;
        this.closecode = null;
        this.closedremotely = null;
        this.resourceDescriptor = null;
        this.lastPong = System.nanoTime();
        this.synchronizeWriteObject = new Object();
        if (webSocketListener != null && (draft != null || this.role != Role.SERVER)) {
            this.outQueue = new LinkedBlockingQueue();
            this.inQueue = new LinkedBlockingQueue();
            this.wsl = webSocketListener;
            this.role = Role.CLIENT;
            if (draft != null) {
                this.draft = draft.copyInstance();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("parameters must not be null");
    }

    public void closeConnection(int i, boolean z) {
        closeConnection(i, "", z);
    }

    public void closeConnection() {
        if (this.closedremotely != null) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
            return;
        }
        throw new IllegalStateException("this method must be used in conjunction with flushAndClose");
    }

    @Override // org.java_websocket.WebSocket
    public void closeConnection(int i, String str) {
        closeConnection(i, str, false);
    }

    @Override // org.java_websocket.WebSocket
    public void close(int i, String str) {
        close(i, str, false);
    }

    @Override // org.java_websocket.WebSocket
    public void close(int i) {
        close(i, "", false);
    }

    public void close(InvalidDataException invalidDataException) {
        close(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    @Override // org.java_websocket.WebSocket
    public void close() {
        close(1000);
    }
}
