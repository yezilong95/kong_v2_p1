package org.bouncycastle.jce.interfaces;

import defpackage.rs;
import java.math.BigInteger;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface ElGamalPublicKey extends DHKey, DHPublicKey {
    /* synthetic */ rs getParameters();

    BigInteger getY();
}
