package org.bouncycastle.jce.interfaces;

import defpackage.t10;
import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface GOST3410PrivateKey extends PrivateKey {
    /* synthetic */ t10 getParameters();

    BigInteger getX();
}
