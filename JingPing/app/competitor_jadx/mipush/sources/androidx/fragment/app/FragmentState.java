package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.vz;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a(3);
    public final String E;
    public final String F;
    public final boolean G;
    public final int H;
    public final int I;
    public final String J;
    public final boolean K;
    public final boolean L;
    public final boolean M;
    public final Bundle N;
    public final boolean O;
    public final int P;
    public Bundle Q;

    public FragmentState(vz vzVar) {
        this.E = vzVar.getClass().getName();
        this.F = vzVar.I;
        this.G = vzVar.Q;
        this.H = vzVar.Z;
        this.I = vzVar.a0;
        this.J = vzVar.b0;
        this.K = vzVar.e0;
        this.L = vzVar.P;
        this.M = vzVar.d0;
        this.N = vzVar.J;
        this.O = vzVar.c0;
        this.P = vzVar.o0.ordinal();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append("FragmentState{");
        sb.append(this.E);
        sb.append(" (");
        sb.append(this.F);
        sb.append(")}:");
        if (this.G) {
            sb.append(" fromLayout");
        }
        int i = this.I;
        if (i != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i));
        }
        String str = this.J;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(str);
        }
        if (this.K) {
            sb.append(" retainInstance");
        }
        if (this.L) {
            sb.append(" removing");
        }
        if (this.M) {
            sb.append(" detached");
        }
        if (this.O) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.E);
        parcel.writeString(this.F);
        parcel.writeInt(this.G ? 1 : 0);
        parcel.writeInt(this.H);
        parcel.writeInt(this.I);
        parcel.writeString(this.J);
        parcel.writeInt(this.K ? 1 : 0);
        parcel.writeInt(this.L ? 1 : 0);
        parcel.writeInt(this.M ? 1 : 0);
        parcel.writeBundle(this.N);
        parcel.writeInt(this.O ? 1 : 0);
        parcel.writeBundle(this.Q);
        parcel.writeInt(this.P);
    }

    public FragmentState(Parcel parcel) {
        this.E = parcel.readString();
        this.F = parcel.readString();
        this.G = parcel.readInt() != 0;
        this.H = parcel.readInt();
        this.I = parcel.readInt();
        this.J = parcel.readString();
        this.K = parcel.readInt() != 0;
        this.L = parcel.readInt() != 0;
        this.M = parcel.readInt() != 0;
        this.N = parcel.readBundle();
        this.O = parcel.readInt() != 0;
        this.Q = parcel.readBundle();
        this.P = parcel.readInt();
    }
}
