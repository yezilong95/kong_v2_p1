package io.nats.client;

import io.nats.client.support.RandomUtils;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class NUID {
    static final int base = 62;
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final NUID globalNUID = new NUID();
    static final long maxInc = 333;
    static final long maxSeq = 839299365868340224L;
    static final long minInc = 33;
    static final int preLen = 12;
    static final int seqLen = 10;
    static final int totalLen = 22;
    private long inc;
    private final ReentrantLock nextLock = new ReentrantLock();
    char[] pre;
    private long seq;

    public NUID() {
        Random random = RandomUtils.PRAND;
        this.seq = RandomUtils.nextLong(random, maxSeq);
        this.inc = RandomUtils.nextLong(random, 300L) + minInc;
        this.pre = new char[12];
        for (int i = 0; i < 12; i++) {
            this.pre[i] = '0';
        }
        randomizePrefix();
    }

    public static NUID getInstance() {
        return globalNUID;
    }

    public static String nextGlobal() {
        return globalNUID.next();
    }

    public static String nextGlobalSequence() {
        return globalNUID.nextSequence();
    }

    public char[] getPre() {
        return this.pre;
    }

    public long getSeq() {
        return this.seq;
    }

    public String next() {
        this.nextLock.lock();
        try {
            long j = this.seq + this.inc;
            this.seq = j;
            if (j >= maxSeq) {
                randomizePrefix();
                resetSequential();
            }
            int i = 22;
            char[] cArr = new char[22];
            System.arraycopy(this.pre, 0, cArr, 0, 12);
            long j2 = this.seq;
            while (i > 12) {
                i--;
                cArr[i] = digits[(int) (j2 % 62)];
                j2 /= 62;
            }
            String str = new String(cArr);
            this.nextLock.unlock();
            return str;
        } catch (Throwable th) {
            this.nextLock.unlock();
            throw th;
        }
    }

    public String nextSequence() {
        this.nextLock.lock();
        try {
            long j = this.seq + this.inc;
            this.seq = j;
            if (j >= maxSeq) {
                randomizePrefix();
                resetSequential();
            }
            int i = 10;
            char[] cArr = new char[10];
            long j2 = this.seq;
            while (i > 0) {
                i--;
                cArr[i] = digits[(int) (j2 % 62)];
                j2 /= 62;
            }
            String str = new String(cArr);
            this.nextLock.unlock();
            return str;
        } catch (Throwable th) {
            this.nextLock.unlock();
            throw th;
        }
    }

    public final void randomizePrefix() {
        byte[] bArr = new byte[12];
        RandomUtils.SRAND.nextBytes(bArr);
        for (int i = 0; i < 12; i++) {
            this.pre[i] = digits[(bArr[i] & 255) % base];
        }
    }

    public void resetSequential() {
        Random random = RandomUtils.PRAND;
        this.seq = RandomUtils.nextLong(random, maxSeq);
        this.inc = RandomUtils.nextLong(random, 300L) + minInc;
    }

    public void setSeq(long j) {
        this.seq = j;
    }
}
