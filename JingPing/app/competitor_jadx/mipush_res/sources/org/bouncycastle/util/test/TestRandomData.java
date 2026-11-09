package org.bouncycastle.util.test;

import defpackage.c30;
import defpackage.kw;
import defpackage.nw;

/* JADX INFO: loaded from: classes.dex */
public class TestRandomData extends FixedSecureRandom {
    public TestRandomData(String str) {
        super(new nw[]{new kw(c30.a(str))});
    }

    public TestRandomData(byte[] bArr) {
        super(new nw[]{new kw(bArr)});
    }
}
