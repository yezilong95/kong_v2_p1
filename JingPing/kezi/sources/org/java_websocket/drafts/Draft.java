package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.uuid.Uuid;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.extensions.ExtensionRequestData;
import org.java_websocket.framing.BinaryFrame;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.ContinuousFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: classes.dex */
public abstract class Draft {
    protected Role role = null;
    protected Opcode continuousFrameType = null;

    public static ByteBuffer readLine(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            byteBufferAllocate.put(b2);
            if (b == 13 && b2 == 10) {
                byteBufferAllocate.limit(byteBufferAllocate.position() - 2);
                byteBufferAllocate.position(0);
                return byteBufferAllocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - byteBufferAllocate.position());
        return null;
    }

    public static String readStringLine(ByteBuffer byteBuffer) {
        ByteBuffer line = readLine(byteBuffer);
        if (line == null) {
            return null;
        }
        return Charsetfunctions.stringAscii(line.array(), 0, line.limit());
    }

    public static HandshakeBuilder translateHandshakeHttp(ByteBuffer byteBuffer, Role role) throws InvalidHandshakeException {
        String stringLine = readStringLine(byteBuffer);
        if (stringLine == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + Uuid.SIZE_BITS);
        }
        String[] strArrSplit = stringLine.split(" ", 3);
        if (strArrSplit.length != 3) {
            throw new InvalidHandshakeException();
        }
        HandshakeBuilder handshakeBuilderTranslateHandshakeHttpClient = role == Role.CLIENT ? translateHandshakeHttpClient(strArrSplit, stringLine) : translateHandshakeHttpServer(strArrSplit, stringLine);
        String stringLine2 = readStringLine(byteBuffer);
        while (stringLine2 != null && stringLine2.length() > 0) {
            String[] strArrSplit2 = stringLine2.split(":", 2);
            if (strArrSplit2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            if (handshakeBuilderTranslateHandshakeHttpClient.hasFieldValue(strArrSplit2[0])) {
                handshakeBuilderTranslateHandshakeHttpClient.put(strArrSplit2[0], handshakeBuilderTranslateHandshakeHttpClient.getFieldValue(strArrSplit2[0]) + "; " + strArrSplit2[1].replaceFirst("^ +", ExtensionRequestData.EMPTY_VALUE));
            } else {
                handshakeBuilderTranslateHandshakeHttpClient.put(strArrSplit2[0], strArrSplit2[1].replaceFirst("^ +", ExtensionRequestData.EMPTY_VALUE));
            }
            stringLine2 = readStringLine(byteBuffer);
        }
        if (stringLine2 != null) {
            return handshakeBuilderTranslateHandshakeHttpClient;
        }
        throw new IncompleteHandshakeException();
    }

    private static HandshakeBuilder translateHandshakeHttpClient(String[] strArr, String str) throws InvalidHandshakeException {
        if (!"101".equals(strArr[1])) {
            throw new InvalidHandshakeException("Invalid status code received: " + strArr[1] + " Status line: " + str);
        }
        if ("HTTP/1.1".equalsIgnoreCase(strArr[0])) {
            HandshakeImpl1Server handshakeImpl1Server = new HandshakeImpl1Server();
            handshakeImpl1Server.setHttpStatus(Short.parseShort(strArr[1]));
            handshakeImpl1Server.setHttpStatusMessage(strArr[2]);
            return handshakeImpl1Server;
        }
        throw new InvalidHandshakeException("Invalid status line received: " + strArr[0] + " Status line: " + str);
    }

    private static HandshakeBuilder translateHandshakeHttpServer(String[] strArr, String str) throws InvalidHandshakeException {
        if (!"GET".equalsIgnoreCase(strArr[0])) {
            throw new InvalidHandshakeException("Invalid request method received: " + strArr[0] + " Status line: " + str);
        }
        if ("HTTP/1.1".equalsIgnoreCase(strArr[2])) {
            HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
            handshakeImpl1Client.setResourceDescriptor(strArr[1]);
            return handshakeImpl1Client;
        }
        throw new InvalidHandshakeException("Invalid status line received: " + strArr[2] + " Status line: " + str);
    }

    public abstract HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException;

    public abstract HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException;

    public boolean basicAccept(Handshakedata handshakedata) {
        return handshakedata.getFieldValue("Upgrade").equalsIgnoreCase("websocket") && handshakedata.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public int checkAlloc(int i) throws InvalidDataException {
        if (i >= 0) {
            return i;
        }
        throw new InvalidDataException(CloseFrame.PROTOCOL_ERROR, "Negative count");
    }

    public List<Framedata> continuousFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        FramedataImpl1 binaryFrame;
        Opcode opcode2 = Opcode.BINARY;
        if (opcode != opcode2 && opcode != Opcode.TEXT) {
            throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
        }
        if (this.continuousFrameType != null) {
            binaryFrame = new ContinuousFrame();
        } else {
            this.continuousFrameType = opcode;
            binaryFrame = opcode == opcode2 ? new BinaryFrame() : opcode == Opcode.TEXT ? new TextFrame() : null;
        }
        binaryFrame.setPayload(byteBuffer);
        binaryFrame.setFin(z);
        try {
            binaryFrame.isValid();
            if (z) {
                this.continuousFrameType = null;
            } else {
                this.continuousFrameType = opcode;
            }
            return Collections.singletonList(binaryFrame);
        } catch (InvalidDataException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract Draft copyInstance();

    public abstract ByteBuffer createBinaryFrame(Framedata framedata);

    public abstract List<Framedata> createFrames(String str, boolean z);

    public abstract List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z);

    @Deprecated
    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role role) {
        return createHandshake(handshakedata);
    }

    public abstract CloseHandshakeType getCloseHandshakeType();

    public Role getRole() {
        return this.role;
    }

    public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException;

    public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException;

    public abstract void processFrame(WebSocketImpl webSocketImpl, Framedata framedata) throws InvalidDataException;

    public int readVersion(Handshakedata handshakedata) {
        String fieldValue = handshakedata.getFieldValue("Sec-WebSocket-Version");
        if (fieldValue.length() > 0) {
            try {
                return Integer.parseInt(fieldValue.trim());
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public abstract void reset();

    public void setParseMode(Role role) {
        this.role = role;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public abstract List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException;

    public Handshakedata translateHandshake(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        return translateHandshakeHttp(byteBuffer, this.role);
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata) {
        return createHandshake(handshakedata, true);
    }

    @Deprecated
    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role role, boolean z) {
        return createHandshake(handshakedata, z);
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            sb.append("GET ");
            sb.append(((ClientHandshake) handshakedata).getResourceDescriptor());
            sb.append(" HTTP/1.1");
        } else if (handshakedata instanceof ServerHandshake) {
            sb.append("HTTP/1.1 101 ");
            sb.append(((ServerHandshake) handshakedata).getHttpStatusMessage());
        } else {
            throw new IllegalArgumentException("unknown role");
        }
        sb.append("\r\n");
        Iterator<String> itIterateHttpFields = handshakedata.iterateHttpFields();
        while (itIterateHttpFields.hasNext()) {
            String next = itIterateHttpFields.next();
            String fieldValue = handshakedata.getFieldValue(next);
            sb.append(next);
            sb.append(": ");
            sb.append(fieldValue);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] bArrAsciiBytes = Charsetfunctions.asciiBytes(sb.toString());
        byte[] content = z ? handshakedata.getContent() : null;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((content == null ? 0 : content.length) + bArrAsciiBytes.length);
        byteBufferAllocate.put(bArrAsciiBytes);
        if (content != null) {
            byteBufferAllocate.put(content);
        }
        byteBufferAllocate.flip();
        return Collections.singletonList(byteBufferAllocate);
    }
}
