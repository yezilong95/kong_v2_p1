package org.bouncycastle.jce.interfaces;

import defpackage.t10;
import java.math.BigInteger;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface GOST3410PublicKey extends PublicKey {
    /* synthetic */ t10 getParameters();

    BigInteger getY();
}
