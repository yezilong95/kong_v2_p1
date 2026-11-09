package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
class AppCompatDelegateImpl$PanelFeatureState$SavedState implements Parcelable {
    public static final Parcelable.Creator<AppCompatDelegateImpl$PanelFeatureState$SavedState> CREATOR = new a();
    public int b;
    public boolean c;
    public Bundle d;

    public static AppCompatDelegateImpl$PanelFeatureState$SavedState l(Parcel parcel, ClassLoader classLoader) {
        AppCompatDelegateImpl$PanelFeatureState$SavedState appCompatDelegateImpl$PanelFeatureState$SavedState = new AppCompatDelegateImpl$PanelFeatureState$SavedState();
        appCompatDelegateImpl$PanelFeatureState$SavedState.b = parcel.readInt();
        boolean z = parcel.readInt() == 1;
        appCompatDelegateImpl$PanelFeatureState$SavedState.c = z;
        if (z) {
            appCompatDelegateImpl$PanelFeatureState$SavedState.d = parcel.readBundle(classLoader);
        }
        return appCompatDelegateImpl$PanelFeatureState$SavedState;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        if (this.c) {
            parcel.writeBundle(this.d);
        }
    }
}
