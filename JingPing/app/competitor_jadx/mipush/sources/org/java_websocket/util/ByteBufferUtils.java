package org.java_websocket.util;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class ByteBufferUtils {
    private ByteBufferUtils() {
    }

    public static ByteBuffer getEmptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    public static int transferByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer == null || byteBuffer2 == null) {
            throw new IllegalArgumentException();
        }
        int iRemaining = byteBuffer.remaining();
        int iRemaining2 = byteBuffer2.remaining();
        if (iRemaining <= iRemaining2) {
            byteBuffer2.put(byteBuffer);
            return iRemaining;
        }
        int iMin = Math.min(iRemaining, iRemaining2);
        byteBuffer.limit(iMin);
        byteBuffer2.put(byteBuffer);
        return iMin;
    }
}
