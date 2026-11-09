package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator<Fragment$SavedState> CREATOR = new defpackage.d(2);
    public final Bundle b;

    public Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle bundle = parcel.readBundle();
        this.b = bundle;
        if (classLoader == null || bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.b);
    }
}
