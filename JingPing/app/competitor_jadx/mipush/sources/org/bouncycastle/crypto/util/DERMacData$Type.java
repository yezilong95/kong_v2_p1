package org.bouncycastle.crypto.util;

import defpackage.jx0;

/* JADX INFO: loaded from: classes.dex */
public enum DERMacData$Type {
    UNILATERALU("KC_1_U"),
    UNILATERALV("KC_1_V"),
    BILATERALU("KC_2_U"),
    BILATERALV("KC_2_V");

    public final String E;

    DERMacData$Type(String str) {
        this.E = str;
    }

    public byte[] getHeader() {
        return jx0.b(this.E);
    }
}
