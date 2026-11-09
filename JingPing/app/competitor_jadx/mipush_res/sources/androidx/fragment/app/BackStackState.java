package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.m00;
import defpackage.m9;
import defpackage.vz;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a(0);
    public final int[] E;
    public final ArrayList F;
    public final int[] G;
    public final int[] H;
    public final int I;
    public final String J;
    public final int K;
    public final int L;
    public final CharSequence M;
    public final int N;
    public final CharSequence O;
    public final ArrayList P;
    public final ArrayList Q;
    public final boolean R;

    public BackStackState(m9 m9Var) {
        int size = m9Var.a.size();
        this.E = new int[size * 5];
        if (!m9Var.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.F = new ArrayList(size);
        this.G = new int[size];
        this.H = new int[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            m00 m00Var = (m00) m9Var.a.get(i2);
            int i3 = i + 1;
            this.E[i] = m00Var.a;
            ArrayList arrayList = this.F;
            vz vzVar = m00Var.b;
            arrayList.add(vzVar != null ? vzVar.I : null);
            int[] iArr = this.E;
            iArr[i3] = m00Var.c;
            iArr[i + 2] = m00Var.d;
            int i4 = i + 4;
            iArr[i + 3] = m00Var.e;
            i += 5;
            iArr[i4] = m00Var.f;
            this.G[i2] = m00Var.g.ordinal();
            this.H[i2] = m00Var.h.ordinal();
        }
        this.I = m9Var.f;
        this.J = m9Var.h;
        this.K = m9Var.r;
        this.L = m9Var.i;
        this.M = m9Var.j;
        this.N = m9Var.k;
        this.O = m9Var.l;
        this.P = m9Var.m;
        this.Q = m9Var.n;
        this.R = m9Var.o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.E);
        parcel.writeStringList(this.F);
        parcel.writeIntArray(this.G);
        parcel.writeIntArray(this.H);
        parcel.writeInt(this.I);
        parcel.writeString(this.J);
        parcel.writeInt(this.K);
        parcel.writeInt(this.L);
        TextUtils.writeToParcel(this.M, parcel, 0);
        parcel.writeInt(this.N);
        TextUtils.writeToParcel(this.O, parcel, 0);
        parcel.writeStringList(this.P);
        parcel.writeStringList(this.Q);
        parcel.writeInt(this.R ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.E = parcel.createIntArray();
        this.F = parcel.createStringArrayList();
        this.G = parcel.createIntArray();
        this.H = parcel.createIntArray();
        this.I = parcel.readInt();
        this.J = parcel.readString();
        this.K = parcel.readInt();
        this.L = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.M = (CharSequence) creator.createFromParcel(parcel);
        this.N = parcel.readInt();
        this.O = (CharSequence) creator.createFromParcel(parcel);
        this.P = parcel.createStringArrayList();
        this.Q = parcel.createStringArrayList();
        this.R = parcel.readInt() != 0;
    }
}
