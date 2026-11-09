package io.nats.client.impl;

import io.nats.client.AuthHandler;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class MemoryAuthHandler implements AuthHandler {
    private final StringAuthHandler sah;

    public MemoryAuthHandler(byte[] bArr) {
        CharBuffer charBufferDecode = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bArr));
        this.sah = new StringAuthHandler(extract(charBufferDecode, 1), extract(charBufferDecode, 2));
    }

    private char[] extract(CharBuffer charBuffer, int i) {
        CharBuffer charBufferAllocate = CharBuffer.allocate(charBuffer.length());
        boolean z = false;
        int i2 = 0;
        loop0: while (true) {
            int i3 = -1;
            while (charBuffer.length() > 0) {
                char c = charBuffer.get();
                i3++;
                if (c == '\n' || c == '\r') {
                    if (charBufferAllocate.position() > 0) {
                        break;
                    }
                    z = false;
                } else if (!z && !Character.isWhitespace(c)) {
                    if (c == '-' && i3 == 0) {
                        i2++;
                        z = true;
                    } else if (i2 == i) {
                        charBufferAllocate.put(c);
                    }
                }
            }
            break loop0;
        }
        if (charBufferAllocate.position() == 0 && i == 1) {
            charBuffer.position(0);
            while (charBuffer.length() > 0) {
                char c2 = charBuffer.get();
                if (c2 == '\n' || c2 == '\r' || Character.isWhitespace(c2)) {
                    if (charBufferAllocate.position() > 0) {
                        break;
                    }
                } else {
                    charBufferAllocate.put(c2);
                }
            }
            charBufferAllocate.flip();
        } else {
            charBufferAllocate.flip();
        }
        char[] cArr = new char[charBufferAllocate.length()];
        charBufferAllocate.get(cArr);
        charBufferAllocate.clear();
        for (int i4 = 0; i4 < charBufferAllocate.capacity(); i4++) {
            charBufferAllocate.put((char) 0);
        }
        return cArr;
    }

    @Override // io.nats.client.AuthHandler
    public char[] getID() {
        return this.sah.getID();
    }

    @Override // io.nats.client.AuthHandler
    public char[] getJWT() {
        return this.sah.getJWT();
    }

    @Override // io.nats.client.AuthHandler
    public byte[] sign(byte[] bArr) {
        return this.sah.sign(bArr);
    }
}
