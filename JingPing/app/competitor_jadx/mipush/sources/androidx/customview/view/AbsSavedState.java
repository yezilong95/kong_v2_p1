package androidx.customview.view;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.n0;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public abstract class AbsSavedState implements Parcelable {
    public final Parcelable E;
    public static final AbsSavedState F = new AnonymousClass1();
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new n0(0);

    /* JADX INFO: renamed from: androidx.customview.view.AbsSavedState$1, reason: invalid class name */
    public class AnonymousClass1 extends AbsSavedState {
    }

    public AbsSavedState() {
        this.E = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.E, i);
    }

    public AbsSavedState(Parcelable parcelable) {
        if (parcelable != null) {
            this.E = parcelable == F ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.E = parcelable == null ? F : parcelable;
    }
}
