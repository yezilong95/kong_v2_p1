package io.nats.client.support;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class ByteArrayPrimitiveBuilder extends BuilderBase {
    private byte[] buffer;
    private int position;

    public ByteArrayPrimitiveBuilder(int i, int i2, Charset charset) {
        super(charset, i2);
        this.buffer = new byte[BuilderBase.bufferAllocSize(i, this.allocationSize)];
        this.position = 0;
    }

    public ByteArrayPrimitiveBuilder append(int i) {
        append(Integer.toString(i).getBytes(StandardCharsets.US_ASCII));
        return this;
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte b) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
        return 1;
    }

    @Override // io.nats.client.support.BuilderBase
    public int capacity() {
        return this.buffer.length;
    }

    public ByteArrayPrimitiveBuilder clear() {
        this.position = 0;
        return this;
    }

    public int copyTo(byte[] bArr, int i) {
        System.arraycopy(this.buffer, 0, bArr, i, this.position);
        return this.position;
    }

    public void ensureCapacity(int i) {
        int length = this.buffer.length;
        int i2 = this.position;
        if (length - i2 < i) {
            byte[] bArr = new byte[BuilderBase.bufferAllocSize(i2 + i, this.allocationSize)];
            System.arraycopy(this.buffer, 0, bArr, 0, this.position);
            this.buffer = bArr;
        }
    }

    @Override // io.nats.client.support.BuilderBase
    public boolean equals(byte[] bArr) {
        if (bArr == null || this.position != bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (this.buffer[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // io.nats.client.support.BuilderBase
    public byte[] internalArray() {
        return this.buffer;
    }

    @Override // io.nats.client.support.BuilderBase
    public int length() {
        return this.position;
    }

    public void setAllocationSize(int i) {
        _setAllocationSize(i);
    }

    @Override // io.nats.client.support.BuilderBase
    public byte[] toByteArray() {
        int i = this.position;
        byte[] bArr = new byte[i];
        System.arraycopy(this.buffer, 0, bArr, 0, i);
        return bArr;
    }

    public String toString() {
        return new String(this.buffer, 0, this.position, this.defaultCharset);
    }

    public ByteArrayPrimitiveBuilder append(String str) {
        return append(str, this.defaultCharset);
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte[] bArr) {
        return appendUnchecked(bArr, 0, bArr.length);
    }

    public ByteArrayPrimitiveBuilder append(String str, Charset charset) {
        return str == null ? append(BuilderBase.NULL, 0, 4) : append(str.getBytes(charset));
    }

    @Override // io.nats.client.support.BuilderBase
    public int appendUnchecked(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.buffer, this.position, i2);
        this.position += i2;
        return i2;
    }

    public void copyTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.buffer, 0, this.position);
    }

    public ByteArrayPrimitiveBuilder() {
        this(-1, 32, StandardCharsets.US_ASCII);
    }

    public ByteArrayPrimitiveBuilder append(CharBuffer charBuffer) {
        return append(charBuffer, this.defaultCharset);
    }

    public ByteArrayPrimitiveBuilder(int i) {
        this(i, 32, StandardCharsets.US_ASCII);
    }

    public ByteArrayPrimitiveBuilder append(CharBuffer charBuffer, Charset charset) {
        if (charBuffer == null) {
            append(BuilderBase.NULL, 0, 4);
            return this;
        }
        append(charBuffer.toString().getBytes(charset));
        return this;
    }

    public ByteArrayPrimitiveBuilder(Charset charset) {
        this(-1, -1, charset);
    }

    public ByteArrayPrimitiveBuilder(int i, Charset charset) {
        this(i, -1, charset);
    }

    public ByteArrayPrimitiveBuilder append(byte b) {
        ensureCapacity(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
        return this;
    }

    public ByteArrayPrimitiveBuilder(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ByteArrayPrimitiveBuilder(byte[] bArr, int i) {
        super(StandardCharsets.US_ASCII, 32);
        this.position = i;
        byte[] bArr2 = new byte[BuilderBase.bufferAllocSize(i, this.allocationSize)];
        this.buffer = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i);
    }

    public ByteArrayPrimitiveBuilder append(byte[] bArr) {
        return append(bArr, 0, bArr.length);
    }

    public ByteArrayPrimitiveBuilder append(byte[] bArr, int i) {
        if (i > 0) {
            ensureCapacity(i);
            System.arraycopy(bArr, 0, this.buffer, this.position, i);
            this.position += i;
        }
        return this;
    }

    public ByteArrayPrimitiveBuilder append(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            ensureCapacity(i2);
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
        }
        return this;
    }

    public ByteArrayPrimitiveBuilder append(ByteArrayPrimitiveBuilder byteArrayPrimitiveBuilder) {
        if (byteArrayPrimitiveBuilder != null) {
            append(byteArrayPrimitiveBuilder.buffer, byteArrayPrimitiveBuilder.length());
        }
        return this;
    }
}
