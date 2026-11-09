package io.nats.client.support;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class RandomUtils {
    public static final Random PRAND;
    public static final SecureRandom SRAND;

    static {
        SecureRandom secureRandom = new SecureRandom();
        SRAND = secureRandom;
        PRAND = new Random(bytesToLong(secureRandom.generateSeed(8)));
    }

    private RandomUtils() {
    }

    public static long bytesToLong(byte[] bArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(64);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.flip();
        return byteBufferAllocate.getLong();
    }

    public static long nextLong(Random random, long j) {
        long jNextLong;
        long j2;
        do {
            jNextLong = (random.nextLong() << 1) >>> 1;
            j2 = jNextLong % j;
        } while ((j - 1) + (jNextLong - j2) < 0);
        return j2;
    }
}
