package org.bouncycastle.util.encoders;

/* JADX INFO: loaded from: classes.dex */
public class EncoderException extends IllegalStateException {
    public Exception E;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
