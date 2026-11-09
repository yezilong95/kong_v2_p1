package com.google.android.material.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import defpackage.n0;
import defpackage.vv0;
import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public class ExtendableSavedState extends AbsSavedState {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR = new n0(1);
    public final vv0 G;

    public ExtendableSavedState(Parcelable parcelable) {
        super(parcelable);
        this.G = new vv0();
    }

    public final String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.G + JsonUtils.CLOSE;
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        vv0 vv0Var = this.G;
        int i2 = vv0Var.G;
        parcel.writeInt(i2);
        String[] strArr = new String[i2];
        Bundle[] bundleArr = new Bundle[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = (String) vv0Var.h(i3);
            bundleArr[i3] = (Bundle) vv0Var.j(i3);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }

    public ExtendableSavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i = parcel.readInt();
        String[] strArr = new String[i];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[i];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.G = new vv0(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.G.put(strArr[i2], bundleArr[i2]);
        }
    }
}
