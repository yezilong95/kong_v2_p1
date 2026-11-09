package org.bouncycastle.util.encoders;

/* JADX INFO: loaded from: classes.dex */
public class DecoderException extends IllegalStateException {
    public final Exception E;

    public DecoderException(String str, Exception exc) {
        super(str);
        this.E = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
