package io.nats.client.impl;

import io.nats.client.BaseConsumeOptions;
import io.nats.client.ReadListener;
import io.nats.client.support.IncomingHeadersProcessor;
import io.nats.client.support.NatsConstants;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
class NatsConnectionReader implements Runnable {
    private static final int[] TENS = {1, 10, 100, 1000, 10000, 100000, BaseConsumeOptions.DEFAULT_MESSAGE_COUNT_WHEN_BYTES, 10000000, 100000000, 1000000000};
    private final byte[] buffer;
    private int bufferPosition;
    private final NatsConnection connection;
    private DataPort dataPort;
    private Future<DataPort> dataPortFuture;
    private boolean gotCR;
    private IncomingMessageFactory incoming;
    private Mode mode;
    private byte[] msgData;
    private int msgDataPosition;
    private byte[] msgHeaders;
    private int msgHeadersPosition;
    private final char[] msgLineChars;
    private int msgLinePosition;
    private String op;
    private final char[] opArray;
    private int opPos;
    private ByteBuffer protocolBuffer;
    private final ReadListener readListener;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Future<Boolean> stopped;
    private final boolean utf8Mode;

    public enum Mode {
        GATHER_OP,
        GATHER_PROTO,
        GATHER_MSG_HMSG_PROTO,
        PARSE_PROTO,
        GATHER_HEADERS,
        GATHER_DATA
    }

    public NatsConnectionReader(NatsConnection natsConnection) {
        this.connection = natsConnection;
        CompletableFuture completableFuture = new CompletableFuture();
        this.stopped = completableFuture;
        completableFuture.complete(Boolean.TRUE);
        this.protocolBuffer = ByteBuffer.allocate(natsConnection.getOptions().getMaxControlLine());
        this.msgLineChars = new char[natsConnection.getOptions().getMaxControlLine()];
        this.opArray = new char[4];
        this.buffer = new byte[natsConnection.getOptions().getBufferSize()];
        this.bufferPosition = 0;
        this.utf8Mode = natsConnection.getOptions().supportUTF8Subjects();
        this.readListener = natsConnection.getOptions().getReadListener();
    }

    public static String opFor(char[] cArr, int i) {
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        char c11;
        char c12;
        char c13;
        char c14;
        char c15;
        char c16;
        if (i == 3) {
            char c17 = cArr[0];
            return ((c17 == 'M' || c17 == 'm') && ((c13 = cArr[1]) == 'S' || c13 == 's') && ((c14 = cArr[2]) == 'G' || c14 == 'g')) ? NatsConstants.OP_MSG : (c17 == '+' && ((c15 = cArr[1]) == 'O' || c15 == 'o') && ((c16 = cArr[2]) == 'K' || c16 == 'k')) ? NatsConstants.OP_OK : NatsConstants.UNKNOWN_OP;
        }
        if (i != 4) {
            return NatsConstants.UNKNOWN_OP;
        }
        char c18 = cArr[1];
        if ((c18 == 'I' || c18 == 'i') && (((c = cArr[0]) == 'P' || c == 'p') && (((c2 = cArr[2]) == 'N' || c2 == 'n') && ((c3 = cArr[3]) == 'G' || c3 == 'g')))) {
            return "PING";
        }
        if ((c18 == 'O' || c18 == 'o') && (((c4 = cArr[0]) == 'P' || c4 == 'p') && (((c5 = cArr[2]) == 'N' || c5 == 'n') && ((c6 = cArr[3]) == 'G' || c6 == 'g')))) {
            return NatsConstants.OP_PONG;
        }
        char c19 = cArr[0];
        return (c19 == '-' && (c18 == 'E' || c18 == 'e') && (((c11 = cArr[2]) == 'R' || c11 == 'r') && ((c12 = cArr[3]) == 'R' || c12 == 'r'))) ? NatsConstants.OP_ERR : ((c19 == 'I' || c19 == 'i') && (c18 == 'N' || c18 == 'n') && (((c7 = cArr[2]) == 'F' || c7 == 'f') && ((c8 = cArr[3]) == 'O' || c8 == 'o'))) ? "INFO" : ((c19 == 'H' || c19 == 'h') && (c18 == 'M' || c18 == 'm') && (((c9 = cArr[2]) == 'S' || c9 == 's') && ((c10 = cArr[3]) == 'G' || c10 == 'g'))) ? NatsConstants.OP_HMSG : NatsConstants.UNKNOWN_OP;
    }

    public static int parseLength(String str) throws NumberFormatException {
        int length = str.length();
        int[] iArr = TENS;
        if (length > iArr.length) {
            throw new NumberFormatException("Long in message length \"" + str + "\" " + length + " > " + iArr.length);
        }
        int i = 0;
        for (int i2 = length - 1; i2 >= 0; i2--) {
            char cCharAt = str.charAt(i2);
            int i3 = cCharAt - '0';
            if (i3 > 9) {
                throw new NumberFormatException("Invalid char in message length '" + cCharAt + "'");
            }
            i += i3 * TENS[(length - i2) - 1];
        }
        return i;
    }

    public String currentOp() {
        return this.op;
    }

    public void encounteredProtocolError(Exception exc) throws IOException {
        throw new IOException(exc);
    }

    public void fakeReadForTest(byte[] bArr) {
        System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
        this.bufferPosition = 0;
        this.op = NatsConstants.UNKNOWN_OP;
        this.mode = Mode.GATHER_OP;
    }

    public void gatherHeaders(int i) throws IOException {
        while (true) {
            try {
                int i2 = this.bufferPosition;
                if (i2 >= i) {
                    return;
                }
                int i3 = i - i2;
                byte[] bArr = this.msgHeaders;
                int length = bArr.length;
                int i4 = this.msgHeadersPosition;
                int i5 = length - i4;
                if (i5 > 0 && i5 <= i3) {
                    System.arraycopy(this.buffer, i2, bArr, i4, i5);
                    this.msgHeadersPosition += i5;
                    this.bufferPosition += i5;
                } else {
                    if (i5 <= 0) {
                        if (i4 != bArr.length) {
                            throw new IllegalStateException("Bad socket data, headers do not match expected length");
                        }
                        this.incoming.setHeaders(new IncomingHeadersProcessor(bArr));
                        this.msgHeaders = null;
                        this.msgHeadersPosition = -1;
                        this.mode = Mode.GATHER_DATA;
                        return;
                    }
                    System.arraycopy(this.buffer, i2, bArr, i4, i3);
                    this.msgHeadersPosition += i3;
                    this.bufferPosition += i3;
                }
            } catch (IllegalStateException e) {
                e = e;
                encounteredProtocolError(e);
                return;
            } catch (NullPointerException e2) {
                e = e2;
                encounteredProtocolError(e);
                return;
            }
        }
    }

    public void gatherMessageData(int i) throws IOException {
        while (true) {
            try {
                int i2 = this.bufferPosition;
                if (i2 >= i) {
                    return;
                }
                int i3 = i - i2;
                byte[] bArr = this.msgData;
                int length = bArr.length;
                int i4 = this.msgDataPosition;
                int i5 = length - i4;
                if (i5 > 0 && i5 <= i3) {
                    System.arraycopy(this.buffer, i2, bArr, i4, i5);
                    this.msgDataPosition += i5;
                    this.bufferPosition += i5;
                } else if (i5 > 0) {
                    System.arraycopy(this.buffer, i2, bArr, i4, i3);
                    this.msgDataPosition += i3;
                    this.bufferPosition += i3;
                } else {
                    byte b = this.buffer[i2];
                    this.bufferPosition = i2 + 1;
                    if (this.gotCR) {
                        if (b != 10) {
                            throw new IllegalStateException("Bad socket data, no LF after CR");
                        }
                        this.incoming.setData(bArr);
                        NatsMessage message = this.incoming.getMessage();
                        this.connection.deliverMessage(message);
                        ReadListener readListener = this.readListener;
                        if (readListener != null) {
                            readListener.message(this.op, message);
                        }
                        this.msgData = null;
                        this.msgDataPosition = 0;
                        this.incoming = null;
                        this.gotCR = false;
                        this.op = NatsConstants.UNKNOWN_OP;
                        this.mode = Mode.GATHER_OP;
                        return;
                    }
                    if (b != 13) {
                        throw new IllegalStateException("Bad socket data, no CRLF after data");
                    }
                    this.gotCR = true;
                }
            } catch (IllegalStateException e) {
                e = e;
                encounteredProtocolError(e);
                return;
            } catch (NullPointerException e2) {
                e = e2;
                encounteredProtocolError(e);
                return;
            }
        }
    }

    public void gatherMessageProtocol(int i) throws IOException {
        while (true) {
            try {
                int i2 = this.bufferPosition;
                if (i2 >= i) {
                    return;
                }
                byte b = this.buffer[i2];
                this.bufferPosition = i2 + 1;
                if (this.gotCR) {
                    if (b != 10) {
                        throw new IllegalStateException("Bad socket data, no LF after CR");
                    }
                    this.mode = Mode.PARSE_PROTO;
                    this.gotCR = false;
                    return;
                }
                if (b == 13) {
                    this.gotCR = true;
                } else {
                    int i3 = this.msgLinePosition;
                    char[] cArr = this.msgLineChars;
                    if (i3 >= cArr.length) {
                        throw new IllegalStateException("Protocol line is too long");
                    }
                    cArr[i3] = (char) b;
                    this.msgLinePosition = i3 + 1;
                }
            } catch (IllegalStateException e) {
                e = e;
                encounteredProtocolError(e);
                return;
            } catch (NullPointerException e2) {
                e = e2;
                encounteredProtocolError(e);
                return;
            } catch (NumberFormatException e3) {
                e = e3;
                encounteredProtocolError(e);
                return;
            }
        }
    }

    public void gatherOp(int i) throws IOException {
        while (true) {
            try {
                int i2 = this.bufferPosition;
                if (i2 >= i) {
                    return;
                }
                byte b = this.buffer[i2];
                this.bufferPosition = i2 + 1;
                if (!this.gotCR) {
                    if (b == 32 || b == 9) {
                        break;
                    }
                    if (b == 13) {
                        this.gotCR = true;
                    } else {
                        char[] cArr = this.opArray;
                        int i3 = this.opPos;
                        cArr[i3] = (char) b;
                        this.opPos = i3 + 1;
                    }
                } else {
                    if (b != 10) {
                        throw new IllegalStateException("Bad socket data, no LF after CR");
                    }
                    this.op = opFor(this.opArray, this.opPos);
                    this.gotCR = false;
                    this.opPos = 0;
                    this.mode = Mode.PARSE_PROTO;
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e = e;
                encounteredProtocolError(e);
                return;
            } catch (IllegalStateException e2) {
                e = e2;
                encounteredProtocolError(e);
                return;
            } catch (NullPointerException e3) {
                e = e3;
                encounteredProtocolError(e);
                return;
            } catch (NumberFormatException e4) {
                e = e4;
                encounteredProtocolError(e);
                return;
            }
        }
        String strOpFor = opFor(this.opArray, this.opPos);
        this.op = strOpFor;
        this.opPos = 0;
        if (!strOpFor.equals(NatsConstants.OP_MSG) && !this.op.equals(NatsConstants.OP_HMSG)) {
            this.mode = Mode.GATHER_PROTO;
            return;
        }
        this.msgLinePosition = 0;
        this.mode = Mode.GATHER_MSG_HMSG_PROTO;
    }

    public void gatherProtocol(int i) throws IOException {
        while (true) {
            try {
                int i2 = this.bufferPosition;
                if (i2 >= i) {
                    return;
                }
                byte b = this.buffer[i2];
                this.bufferPosition = i2 + 1;
                if (this.gotCR) {
                    if (b != 10) {
                        throw new IllegalStateException("Bad socket data, no LF after CR");
                    }
                    this.protocolBuffer.flip();
                    this.mode = Mode.PARSE_PROTO;
                    this.gotCR = false;
                    return;
                }
                if (b == 13) {
                    this.gotCR = true;
                } else {
                    if (!this.protocolBuffer.hasRemaining()) {
                        this.protocolBuffer = this.connection.enlargeBuffer(this.protocolBuffer);
                    }
                    this.protocolBuffer.put(b);
                }
            } catch (IllegalStateException e) {
                e = e;
                encounteredProtocolError(e);
                return;
            } catch (NullPointerException e2) {
                e = e2;
                encounteredProtocolError(e);
                return;
            } catch (NumberFormatException e3) {
                e = e3;
                encounteredProtocolError(e);
                return;
            }
        }
    }

    public String grabNextMessageLineElement(int i) {
        char[] cArr;
        char c;
        int i2 = this.msgLinePosition;
        if (i2 >= i) {
            return null;
        }
        do {
            int i3 = this.msgLinePosition;
            if (i3 >= i) {
                return new String(this.msgLineChars, i2, i3 - i2);
            }
            cArr = this.msgLineChars;
            c = cArr[i3];
            this.msgLinePosition = i3 + 1;
            if (c == ' ') {
                break;
            }
        } while (c != '\t');
        return new String(cArr, i2, (r1 - i2) - 1);
    }

    public boolean isRunning() {
        return this.running.get();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseProtocolMessage() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnectionReader.parseProtocolMessage():void");
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    this.dataPort = this.dataPortFuture.get();
                    this.mode = Mode.GATHER_OP;
                    this.gotCR = false;
                    this.opPos = 0;
                    while (this.running.get() && !Thread.interrupted()) {
                        this.bufferPosition = 0;
                        DataPort dataPort = this.dataPort;
                        byte[] bArr = this.buffer;
                        int i = dataPort.read(bArr, 0, bArr.length);
                        if (i > 0) {
                            this.connection.getNatsStatistics().registerRead(i);
                            while (this.bufferPosition < i) {
                                Mode mode = this.mode;
                                if (mode == Mode.GATHER_OP) {
                                    gatherOp(i);
                                } else if (mode == Mode.GATHER_MSG_HMSG_PROTO) {
                                    if (this.utf8Mode) {
                                        gatherProtocol(i);
                                    } else {
                                        gatherMessageProtocol(i);
                                    }
                                } else if (mode == Mode.GATHER_PROTO) {
                                    gatherProtocol(i);
                                } else if (mode == Mode.GATHER_HEADERS) {
                                    gatherHeaders(i);
                                } else {
                                    gatherMessageData(i);
                                }
                                if (this.mode == Mode.PARSE_PROTO) {
                                    parseProtocolMessage();
                                    this.protocolBuffer.clear();
                                }
                            }
                        } else {
                            if (i < 0) {
                                throw new IOException("Read channel closed.");
                            }
                            this.connection.getNatsStatistics().registerRead(i);
                        }
                    }
                    this.running.set(false);
                    this.protocolBuffer.clear();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    this.running.set(false);
                    this.protocolBuffer.clear();
                }
            } catch (IOException e) {
                if (this.running.get()) {
                    this.connection.handleCommunicationIssue(e);
                }
                this.running.set(false);
                this.protocolBuffer.clear();
            } catch (CancellationException | ExecutionException unused2) {
                this.running.set(false);
                this.protocolBuffer.clear();
            }
        } catch (Throwable th) {
            this.running.set(false);
            this.protocolBuffer.clear();
            throw th;
        }
    }

    public void start(Future<DataPort> future) {
        this.dataPortFuture = future;
        this.running.set(true);
        this.stopped = this.connection.getExecutor().submit(this, Boolean.TRUE);
    }

    public Future<Boolean> stop() {
        return stop(true);
    }

    public Future<Boolean> stop(boolean z) {
        DataPort dataPort;
        if (this.running.get()) {
            this.running.set(false);
            if (z && (dataPort = this.dataPort) != null) {
                try {
                    dataPort.shutdownInput();
                } catch (IOException unused) {
                }
            }
        }
        return this.stopped;
    }
}
