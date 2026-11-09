package org.bouncycastle.jce.interfaces;

import defpackage.rs;
import java.math.BigInteger;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface ElGamalPrivateKey extends DHKey, DHPrivateKey {
    /* synthetic */ rs getParameters();

    BigInteger getX();
}
