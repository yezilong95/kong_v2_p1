package org.bouncycastle.crypto;

import io.nats.client.support.NatsConstants;

/* JADX INFO: loaded from: classes.dex */
public class PacketCipherException extends Exception {
    public final Reason E;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Reason {
        public static final Reason DATA_LENGTH;
        public static final /* synthetic */ Reason[] E;
        public static final Reason INVALID_CIPHERTEXT;
        public static final Reason OTHER;
        public static final Reason OUTPUT_LENGTH;

        static {
            Reason reason = new Reason("INVALID_CIPHERTEXT", 0);
            INVALID_CIPHERTEXT = reason;
            Reason reason2 = new Reason("OUTPUT_LENGTH", 1);
            OUTPUT_LENGTH = reason2;
            Reason reason3 = new Reason("DATA_LENGTH", 2);
            DATA_LENGTH = reason3;
            Reason reason4 = new Reason("OTHER", 3);
            OTHER = reason4;
            E = new Reason[]{reason, reason2, reason3, reason4};
        }

        public static Reason valueOf(String str) {
            return (Reason) Enum.valueOf(Reason.class, str);
        }

        public static Reason[] values() {
            return (Reason[]) E.clone();
        }
    }

    public PacketCipherException(Reason reason, String str, Throwable th) {
        super(str, th);
        this.E = reason;
    }

    public static PacketCipherException from(Throwable th) {
        return th instanceof PacketCipherException ? (PacketCipherException) th : th instanceof InvalidCipherTextException ? new PacketCipherException(Reason.INVALID_CIPHERTEXT, th.getMessage(), th) : th instanceof OutputLengthException ? new PacketCipherException(Reason.OUTPUT_LENGTH, th.getMessage(), th) : th instanceof DataLengthException ? new PacketCipherException(Reason.DATA_LENGTH, th.getMessage(), th) : new PacketCipherException(Reason.OTHER, th.getMessage(), th);
    }

    public Reason getReason() {
        return this.E;
    }

    public void throwCauseAsRuntimeException() {
        Throwable cause = getCause();
        if (!(cause instanceof RuntimeException)) {
            throw new RuntimeException(cause.getMessage(), cause);
        }
        throw ((RuntimeException) cause);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return this.E.toString() + NatsConstants.SPACE + super.toString();
    }
}
