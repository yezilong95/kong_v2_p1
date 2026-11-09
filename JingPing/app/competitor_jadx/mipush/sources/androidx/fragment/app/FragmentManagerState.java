package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a(2);
    public ArrayList E;
    public ArrayList F;
    public BackStackState[] G;
    public int H;
    public String I;
    public ArrayList J;
    public ArrayList K;
    public ArrayList L;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.E);
        parcel.writeStringList(this.F);
        parcel.writeTypedArray(this.G, i);
        parcel.writeInt(this.H);
        parcel.writeString(this.I);
        parcel.writeStringList(this.J);
        parcel.writeTypedList(this.K);
        parcel.writeTypedList(this.L);
    }
}
