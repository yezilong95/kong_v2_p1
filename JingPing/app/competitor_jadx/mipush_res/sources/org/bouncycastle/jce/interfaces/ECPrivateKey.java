package org.bouncycastle.jce.interfaces;

import defpackage.kr;
import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface ECPrivateKey extends PrivateKey {
    BigInteger getD();

    /* synthetic */ kr getParameters();
}
