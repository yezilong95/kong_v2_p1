package io.nats.client.support;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ByteArrayBuilder extends BuilderBase {
    private ByteBuffer buffer;

    public ByteArrayBuilder(int i, int i2, Charset charset) {
        super(charset, i2);
        this.buffer = ByteBuffer.allocate(BuilderBase.bufferAllocSize(i, this.allocationSize));
    }

    public ByteArrayBuilder append(int i) {
        append(Integer.toString(i).getBytes(StandardCharsets.US_ASCII));
        return this;
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte b) {
        this.buffer.put(b);
        return 1;
    }

    @Override // io.nats.client.support.BuilderBase
    public int capacity() {
        return this.buffer.capacity();
    }

    public ByteArrayBuilder clear() {
        this.buffer.clear();
        return this;
    }

    public int copyTo(byte[] bArr, int i) {
        int length = length();
        System.arraycopy(this.buffer.array(), 0, bArr, i, length);
        return length;
    }

    public ByteArrayBuilder ensureCapacity(int i) {
        if (this.buffer.capacity() - this.buffer.position() < i) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(BuilderBase.bufferAllocSize(this.buffer.position() + i, this.allocationSize));
            byteBufferAllocate.put(this.buffer.array(), 0, this.buffer.position());
            this.buffer = byteBufferAllocate;
        }
        return this;
    }

    @Override // io.nats.client.support.BuilderBase
    public boolean equals(byte[] bArr) {
        if (bArr == null || this.buffer.position() != bArr.length) {
            return false;
        }
        byte[] bArrArray = this.buffer.array();
        for (int i = 0; i < bArr.length; i++) {
            if (bArrArray[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // io.nats.client.support.BuilderBase
    public byte[] internalArray() {
        return this.buffer.array();
    }

    @Override // io.nats.client.support.BuilderBase
    public int length() {
        return this.buffer.position();
    }

    public ByteArrayBuilder setAllocationSize(int i) {
        _setAllocationSize(i);
        return this;
    }

    @Override // io.nats.client.support.BuilderBase
    public byte[] toByteArray() {
        return Arrays.copyOf(this.buffer.array(), this.buffer.position());
    }

    public String toString() {
        return new String(this.buffer.array(), 0, this.buffer.position(), this.defaultCharset);
    }

    public ByteArrayBuilder append(String str) {
        return append(str, this.defaultCharset);
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte[] bArr) {
        this.buffer.put(bArr, 0, bArr.length);
        return bArr.length;
    }

    public ByteArrayBuilder() {
        this(-1, 32, StandardCharsets.US_ASCII);
    }

    public ByteArrayBuilder append(String str, Charset charset) {
        return str == null ? append(BuilderBase.NULL, 0, 4) : append(str.getBytes(charset));
    }

    public ByteArrayBuilder(int i) {
        this(i, 32, StandardCharsets.US_ASCII);
    }

    public ByteArrayBuilder append(CharBuffer charBuffer) {
        return append(charBuffer, this.defaultCharset);
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte[] bArr, int i, int i2) {
        this.buffer.put(bArr, i, i2);
        return i2;
    }

    public void copyTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.buffer.array(), 0, this.buffer.position());
    }

    public ByteArrayBuilder(Charset charset) {
        this(-1, -1, charset);
    }

    public ByteArrayBuilder append(CharBuffer charBuffer, Charset charset) {
        if (charBuffer == null) {
            append(BuilderBase.NULL, 0, 4);
            return this;
        }
        append(charBuffer.toString().getBytes(charset));
        return this;
    }

    public ByteArrayBuilder(int i, Charset charset) {
        this(i, -1, charset);
    }

    public ByteArrayBuilder(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ByteArrayBuilder append(byte b) {
        ensureCapacity(1);
        this.buffer.put(b);
        return this;
    }

    public ByteArrayBuilder(byte[] bArr, int i) {
        super(StandardCharsets.US_ASCII, 32);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(BuilderBase.bufferAllocSize(i, this.allocationSize));
        this.buffer = byteBufferAllocate;
        byteBufferAllocate.put(bArr, 0, bArr.length);
    }

    public ByteArrayBuilder append(byte[] bArr) {
        if (bArr.length > 0) {
            ensureCapacity(bArr.length);
            this.buffer.put(bArr, 0, bArr.length);
        }
        return this;
    }

    public ByteArrayBuilder append(byte[] bArr, int i) {
        if (i > 0) {
            ensureCapacity(i);
            this.buffer.put(bArr, 0, i);
        }
        return this;
    }

    public ByteArrayBuilder append(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            ensureCapacity(i2);
            this.buffer.put(bArr, i, i2);
        }
        return this;
    }

    public ByteArrayBuilder append(ByteArrayBuilder byteArrayBuilder) {
        if (byteArrayBuilder != null && byteArrayBuilder.length() > 0) {
            append(byteArrayBuilder.buffer.array(), 0, byteArrayBuilder.length());
        }
        return this;
    }
}
