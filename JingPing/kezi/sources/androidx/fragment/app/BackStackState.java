package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.au;
import defpackage.jt;
import defpackage.s7;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a(0);
    public final int[] b;
    public final ArrayList c;
    public final int[] d;
    public final int[] e;
    public final int f;
    public final String g;
    public final int h;
    public final int i;
    public final CharSequence j;
    public final int k;
    public final CharSequence l;
    public final ArrayList m;
    public final ArrayList n;
    public final boolean o;

    public BackStackState(s7 s7Var) {
        int size = s7Var.a.size();
        this.b = new int[size * 5];
        if (!s7Var.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.c = new ArrayList(size);
        this.d = new int[size];
        this.e = new int[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            au auVar = (au) s7Var.a.get(i2);
            int i3 = i + 1;
            this.b[i] = auVar.a;
            ArrayList arrayList = this.c;
            jt jtVar = auVar.b;
            arrayList.add(jtVar != null ? jtVar.f : null);
            int[] iArr = this.b;
            iArr[i3] = auVar.c;
            iArr[i + 2] = auVar.d;
            int i4 = i + 4;
            iArr[i + 3] = auVar.e;
            i += 5;
            iArr[i4] = auVar.f;
            this.d[i2] = auVar.g.ordinal();
            this.e[i2] = auVar.h.ordinal();
        }
        this.f = s7Var.f;
        this.g = s7Var.h;
        this.h = s7Var.r;
        this.i = s7Var.i;
        this.j = s7Var.j;
        this.k = s7Var.k;
        this.l = s7Var.l;
        this.m = s7Var.m;
        this.n = s7Var.n;
        this.o = s7Var.o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.b);
        parcel.writeStringList(this.c);
        parcel.writeIntArray(this.d);
        parcel.writeIntArray(this.e);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.b = parcel.createIntArray();
        this.c = parcel.createStringArrayList();
        this.d = parcel.createIntArray();
        this.e = parcel.createIntArray();
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.j = (CharSequence) creator.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) creator.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
