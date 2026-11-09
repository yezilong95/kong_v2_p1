package io.nats.client.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class WebSocket extends Socket {
    private static final int MAX_HTTP_HEADERS = 100;
    private static final int MAX_LINE_LEN = 8192;
    private static final String WEBSOCKET_RESPONSE_LINE = "HTTP/1.1 101 Switching Protocols";
    private final ReentrantLock closeLock = new ReentrantLock();
    private final WebsocketInputStream in;
    private final WebsocketOutputStream out;
    private final Socket wrappedSocket;

    public WebSocket(Socket socket, String str, List<Consumer<HttpRequest>> list) throws IOException {
        this.wrappedSocket = socket;
        handshake(socket, str, list);
        this.in = new WebsocketInputStream(socket.getInputStream());
        this.out = new WebsocketOutputStream(socket.getOutputStream(), true);
    }

    private static void handshake(Socket socket, String str, List<Consumer<HttpRequest>> list) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        HttpRequest httpRequest = new HttpRequest();
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        String strBase64BasicEncodeToString = Encoding.base64BasicEncodeToString(bArr);
        httpRequest.getHeaders().add("Host", str).add("Upgrade", "websocket").add("Connection", "Upgrade").add("Sec-WebSocket-Key", strBase64BasicEncodeToString).add("Sec-WebSocket-Protocol", "nats").add("Sec-WebSocket-Version", "13");
        Iterator<Consumer<HttpRequest>> it = list.iterator();
        while (it.hasNext()) {
            it.next().accept(httpRequest);
        }
        outputStream.write(httpRequest.toString().getBytes(StandardCharsets.UTF_8));
        byte[] bArr2 = new byte[MAX_LINE_LEN];
        String line = readLine(bArr2, inputStream);
        if (line == null) {
            throw new IllegalStateException("Expected HTTP response line not to exceed 8192");
        }
        if (!line.toLowerCase().startsWith(WEBSOCKET_RESPONSE_LINE.toLowerCase())) {
            throw new IllegalStateException("Expected HTTP/1.1 101 Switching Protocols, but got ".concat(line));
        }
        HashMap map = new HashMap();
        while (true) {
            String line2 = readLine(bArr2, inputStream);
            if (line2 == null) {
                throw new IllegalStateException("Expected HTTP header to not exceed 8192");
            }
            if ("".equals(line2)) {
                if (!"websocket".equalsIgnoreCase((String) map.get("upgrade"))) {
                    throw new IllegalStateException("Expected HTTP `Upgrade: websocket` header");
                }
                if (!"upgrade".equalsIgnoreCase((String) map.get("connection"))) {
                    throw new IllegalStateException("Expected HTTP `Connection: Upgrade` header");
                }
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                    Charset charset = StandardCharsets.UTF_8;
                    messageDigest.update(strBase64BasicEncodeToString.getBytes(charset));
                    messageDigest.update("258EAFA5-E914-47DA-95CA-C5AB0DC85B11".getBytes(charset));
                    String strBase64BasicEncodeToString2 = Encoding.base64BasicEncodeToString(messageDigest.digest());
                    String str2 = (String) map.get("sec-websocket-accept");
                    if (strBase64BasicEncodeToString2.equals(str2)) {
                        return;
                    }
                    throw new IllegalStateException("Expected HTTP `Sec-WebSocket-Accept: " + strBase64BasicEncodeToString2 + ", but got " + str2);
                } catch (NoSuchAlgorithmException e) {
                    throw new IllegalStateException(e);
                }
            }
            int iIndexOf = line2.indexOf(58);
            if (iIndexOf < 0) {
                throw new IllegalStateException("Expected HTTP header to contain ':', but got ".concat(line2));
            }
            if (map.size() >= 100) {
                throw new IllegalStateException("Exceeded max HTTP headers=100");
            }
            map.put(line2.substring(0, iIndexOf).trim().toLowerCase(), line2.substring(iIndexOf + 1).trim());
        }
    }

    private static String readLine(byte[] bArr, InputStream inputStream) throws IOException {
        int i = 0;
        int i2 = -1;
        while (true) {
            int i3 = inputStream.read();
            if (i3 == -1) {
                return new String(bArr, 0, i, StandardCharsets.ISO_8859_1);
            }
            if (i3 == 10) {
                if (13 == i2) {
                    i--;
                }
                return new String(bArr, 0, i, StandardCharsets.ISO_8859_1);
            }
            if (i >= bArr.length) {
                return null;
            }
            bArr[i] = (byte) i3;
            i++;
            i2 = i3;
        }
    }

    @Override // java.net.Socket
    public void bind(SocketAddress socketAddress) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closeLock.lock();
        try {
            this.wrappedSocket.close();
        } finally {
            this.closeLock.unlock();
        }
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.wrappedSocket.getInetAddress();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        return this.in;
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() throws SocketException {
        return this.wrappedSocket.getKeepAlive();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.wrappedSocket.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.wrappedSocket.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.wrappedSocket.getLocalSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getOOBInline() throws SocketException {
        return this.wrappedSocket.getOOBInline();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        return this.out;
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.wrappedSocket.getPort();
    }

    @Override // java.net.Socket
    public int getReceiveBufferSize() throws SocketException {
        return this.wrappedSocket.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.wrappedSocket.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() throws SocketException {
        return this.wrappedSocket.getReuseAddress();
    }

    @Override // java.net.Socket
    public int getSendBufferSize() throws SocketException {
        return this.wrappedSocket.getSendBufferSize();
    }

    @Override // java.net.Socket
    public int getSoLinger() throws SocketException {
        return this.wrappedSocket.getSoLinger();
    }

    @Override // java.net.Socket
    public int getSoTimeout() throws SocketException {
        return this.wrappedSocket.getSoTimeout();
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() throws SocketException {
        return this.wrappedSocket.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public int getTrafficClass() throws SocketException {
        return this.wrappedSocket.getTrafficClass();
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.wrappedSocket.isBound();
    }

    @Override // java.net.Socket
    public boolean isClosed() {
        return this.wrappedSocket.isClosed();
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.wrappedSocket.isConnected();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.wrappedSocket.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.wrappedSocket.isOutputShutdown();
    }

    @Override // java.net.Socket
    public void sendUrgentData(int i) throws IOException {
        this.wrappedSocket.sendUrgentData(i);
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) throws SocketException {
        this.wrappedSocket.setKeepAlive(z);
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean z) throws SocketException {
        this.wrappedSocket.setOOBInline(z);
    }

    @Override // java.net.Socket
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.wrappedSocket.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public void setReceiveBufferSize(int i) throws SocketException {
        this.wrappedSocket.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) throws SocketException {
        this.wrappedSocket.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public void setSendBufferSize(int i) throws SocketException {
        this.wrappedSocket.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i) throws SocketException {
        this.wrappedSocket.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public void setSoTimeout(int i) throws SocketException {
        this.wrappedSocket.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) throws SocketException {
        this.wrappedSocket.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i) throws SocketException {
        this.wrappedSocket.setTrafficClass(i);
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        this.wrappedSocket.shutdownInput();
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        this.wrappedSocket.shutdownOutput();
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        throw new UnsupportedOperationException();
    }
}
