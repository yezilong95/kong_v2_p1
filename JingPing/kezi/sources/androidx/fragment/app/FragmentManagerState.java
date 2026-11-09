package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a(2);
    public ArrayList b;
    public ArrayList c;
    public BackStackState[] d;
    public int e;
    public String f;
    public ArrayList g;
    public ArrayList h;
    public ArrayList i;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.b);
        parcel.writeStringList(this.c);
        parcel.writeTypedArray(this.d, i);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeStringList(this.g);
        parcel.writeTypedList(this.h);
        parcel.writeTypedList(this.i);
    }
}
