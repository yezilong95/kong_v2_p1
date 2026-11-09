package io.nats.client.support;

import androidx.work.impl.WorkDatabaseVersions;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public class WebsocketFrameHeader {
    public static int MAX_FRAME_HEADER_SIZE = 14;
    private byte byte0;
    private boolean mask;
    private int maskingKey;
    private int maskingKeyOffset = 0;
    private long payloadLength;

    public enum OpCode {
        CONTINUATION(0),
        TEXT(1),
        BINARY(2),
        CLOSE(8),
        PING(9),
        PONG(10),
        UNKNOWN(16);

        private int code;

        OpCode(int i) {
            this.code = i;
        }

        public static OpCode of(int i) {
            if (i == 0) {
                return CONTINUATION;
            }
            if (i == 1) {
                return TEXT;
            }
            if (i == 2) {
                return BINARY;
            }
            switch (i) {
                case 8:
                    return CLOSE;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    return PING;
                case 10:
                    return PONG;
                default:
                    return UNKNOWN;
            }
        }

        public int getCode() {
            return this.code;
        }
    }

    public int filterPayload(byte[] bArr, int i, int i2) {
        long j = this.payloadLength;
        int iMin = Math.min(i2, j > 2147483647L ? Integer.MAX_VALUE : (int) j);
        this.payloadLength -= (long) iMin;
        if (this.mask) {
            for (int i3 = 0; i3 < iMin; i3++) {
                int i4 = this.maskingKey;
                int i5 = this.maskingKeyOffset;
                int i6 = i + i3;
                bArr[i6] = (byte) (((i4 >> ((7 - i5) * 8)) & 255) ^ bArr[i6]);
                this.maskingKeyOffset = (i5 + 1) % 8;
            }
        }
        return iMin;
    }

    public int getMaskingKey() {
        return this.maskingKey;
    }

    public OpCode getOpCode() {
        return OpCode.of(this.byte0 & 15);
    }

    public long getPayloadLength() {
        return this.payloadLength;
    }

    public boolean isFinal() {
        return (this.byte0 & 128) != 0;
    }

    public boolean isMasked() {
        return this.mask;
    }

    public boolean isPayloadEmpty() {
        return 0 == this.payloadLength;
    }

    public int read(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 < size()) {
            return 0;
        }
        int i4 = i + 1;
        bArr[i] = this.byte0;
        long j = this.payloadLength;
        if (j > 65535) {
            int i5 = i + 2;
            bArr[i4] = (byte) ((this.mask ? 128 : 0) | 127);
            bArr[i5] = (byte) ((j >> 56) & 255);
            bArr[i + 3] = (byte) ((j >> 48) & 255);
            bArr[i + 4] = (byte) ((j >> 40) & 255);
            bArr[i + 5] = (byte) ((j >> 32) & 255);
            bArr[i + 6] = (byte) ((j >> 24) & 255);
            bArr[i + 7] = (byte) ((j >> 16) & 255);
            bArr[i + 8] = (byte) ((j >> 8) & 255);
            i3 = i + 10;
            bArr[i + 9] = (byte) (j & 255);
        } else if (j > 125) {
            int i6 = i + 2;
            bArr[i4] = (byte) ((this.mask ? 128 : 0) | 126);
            bArr[i6] = (byte) (j >> 8);
            i3 = i + 4;
            bArr[i + 3] = (byte) (j & 255);
        } else {
            i3 = i + 2;
            bArr[i4] = (byte) (j | ((long) (this.mask ? 128 : 0)));
        }
        if (this.mask) {
            int i7 = this.maskingKey;
            bArr[i3] = (byte) ((i7 >> 24) & 255);
            bArr[i3 + 1] = (byte) ((i7 >> 16) & 255);
            int i8 = i3 + 3;
            bArr[i3 + 2] = (byte) ((i7 >> 8) & 255);
            i3 += 4;
            bArr[i8] = (byte) (i7 & 255);
        }
        return i3 - i;
    }

    public int size() {
        long j = this.payloadLength;
        int i = j > 65535 ? 10 : j > 125 ? 4 : 2;
        return this.mask ? i + 4 : i;
    }

    public WebsocketFrameHeader withMask(int i) {
        this.mask = true;
        this.maskingKey = i;
        this.maskingKeyOffset = 0;
        return this;
    }

    public WebsocketFrameHeader withNoMask() {
        this.mask = false;
        return this;
    }

    public WebsocketFrameHeader withOp(OpCode opCode, boolean z) {
        this.byte0 = (byte) (opCode.getCode() | (z ? Uuid.SIZE_BITS : 0));
        return this;
    }

    public WebsocketFrameHeader withPayloadLength(long j) {
        this.payloadLength = j;
        return this;
    }

    public int write(byte[] bArr, int i, int i2) {
        int size;
        int i3 = 0;
        if (i2 < 2 || (size = size(bArr, i)) > i2) {
            return 0;
        }
        this.byte0 = bArr[i];
        byte b = bArr[i + 1];
        this.mask = (b & 128) != 0;
        long j = b & 127;
        this.payloadLength = j;
        int i4 = i + 2;
        if (126 == j) {
            this.payloadLength = 0L;
            int i5 = 0;
            while (i5 < 2) {
                long j2 = this.payloadLength << 8;
                this.payloadLength = j2;
                this.payloadLength = j2 | ((long) (bArr[i4] & 255));
                i5++;
                i4++;
            }
        } else if (127 == j) {
            this.payloadLength = 0L;
            int i6 = 0;
            while (i6 < 8) {
                long j3 = this.payloadLength << 8;
                this.payloadLength = j3;
                this.payloadLength = j3 | ((long) (bArr[i4] & 255));
                i6++;
                i4++;
            }
        }
        if (this.mask) {
            this.maskingKey = 0;
            this.maskingKeyOffset = 0;
            while (i3 < 4) {
                int i7 = this.maskingKey << 8;
                this.maskingKey = i7;
                this.maskingKey = (bArr[i4] & 255) | i7;
                i3++;
                i4++;
            }
        }
        return size;
    }

    public static int size(byte[] bArr, int i) {
        byte b = bArr[i + 1];
        int i2 = (b & 128) != 0 ? 6 : 2;
        int i3 = b & 127;
        return i3 != 126 ? i3 != 127 ? i2 : i2 + 8 : i2 + 2;
    }
}
