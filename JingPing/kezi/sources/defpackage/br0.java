package defpackage;

import android.os.Parcel;
import android.util.SparseIntArray;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class br0 extends ar0 {
    public final SparseIntArray d;
    public final Parcel e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public br0(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), ExtensionRequestData.EMPTY_VALUE, new c7(), new c7(), new c7());
    }

    @Override // defpackage.ar0
    public final br0 a() {
        Parcel parcel = this.e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new br0(parcel, iDataPosition, i, hk.k(new StringBuilder(), this.h, "  "), this.a, this.b, this.c);
    }

    @Override // defpackage.ar0
    public final boolean e(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            int i3 = this.j;
            Parcel parcel = this.e;
            parcel.setDataPosition(i3);
            int i4 = parcel.readInt();
            this.k = parcel.readInt();
            this.j += i4;
        }
        return this.k == i;
    }

    @Override // defpackage.ar0
    public final void h(int i) {
        int i2 = this.i;
        SparseIntArray sparseIntArray = this.d;
        Parcel parcel = this.e;
        if (i2 >= 0) {
            int i3 = sparseIntArray.get(i2);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i3);
            parcel.writeInt(iDataPosition - i3);
            parcel.setDataPosition(iDataPosition);
        }
        this.i = i;
        sparseIntArray.put(i, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i);
    }

    public br0(Parcel parcel, int i, int i2, String str, c7 c7Var, c7 c7Var2, c7 c7Var3) {
        super(c7Var, c7Var2, c7Var3);
        this.d = new SparseIntArray();
        this.i = -1;
        this.k = -1;
        this.e = parcel;
        this.f = i;
        this.g = i2;
        this.j = i;
        this.h = str;
    }
}
