package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ASN1Exception extends IOException {
    public final RuntimeException E;

    public ASN1Exception(RuntimeException runtimeException, String str) {
        super(str);
        this.E = runtimeException;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.E;
    }
}
