package org.bouncycastle.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface MLKEMPrivateKey extends PrivateKey, MLKEMKey {
    MLKEMPublicKey getPublicKey();
}
