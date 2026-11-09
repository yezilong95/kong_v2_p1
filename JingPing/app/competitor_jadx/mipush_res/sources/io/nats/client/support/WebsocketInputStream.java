package io.nats.client.support;

import io.nats.client.support.WebsocketFrameHeader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class WebsocketInputStream extends InputStream {
    private InputStream in;
    private byte[] buffer = new byte[WebsocketFrameHeader.MAX_FRAME_HEADER_SIZE];
    private WebsocketFrameHeader header = new WebsocketFrameHeader();
    private byte[] oneByte = new byte[1];

    public WebsocketInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    private boolean readHeader() throws IOException {
        int i = 0;
        while (i < 2) {
            int i2 = this.in.read(this.buffer, i, 2 - i);
            if (i2 < 0) {
                return false;
            }
            i += i2;
        }
        int size = WebsocketFrameHeader.size(this.buffer, 0);
        if (size > 2) {
            while (i < size) {
                int i3 = this.in.read(this.buffer, i, size - i);
                if (i3 < 0) {
                    return false;
                }
                i += i3;
            }
        }
        this.header.write(this.buffer, 0, size);
        return true;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        while (0 == this.header.getPayloadLength()) {
            if (!readHeader()) {
                return -1;
            }
        }
        if (this.header.getOpCode() == WebsocketFrameHeader.OpCode.CLOSE) {
            this.in.skip(this.header.getPayloadLength());
            return -1;
        }
        long payloadLength = this.header.getPayloadLength();
        int i3 = this.in.read(bArr, i, Math.min(i2, payloadLength > 2147483647L ? Integer.MAX_VALUE : (int) payloadLength));
        return -1 == i3 ? i3 : this.header.filterPayload(bArr, i, i3);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = read(this.oneByte, 0, 1);
        return -1 == i ? i : this.oneByte[0];
    }
}
