package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        StaggeredGridLayoutManager.SavedState savedState = new StaggeredGridLayoutManager.SavedState();
        savedState.E = parcel.readInt();
        savedState.F = parcel.readInt();
        int i = parcel.readInt();
        savedState.G = i;
        if (i > 0) {
            int[] iArr = new int[i];
            savedState.H = iArr;
            parcel.readIntArray(iArr);
        }
        int i2 = parcel.readInt();
        savedState.I = i2;
        if (i2 > 0) {
            int[] iArr2 = new int[i2];
            savedState.J = iArr2;
            parcel.readIntArray(iArr2);
        }
        savedState.L = parcel.readInt() == 1;
        savedState.M = parcel.readInt() == 1;
        savedState.N = parcel.readInt() == 1;
        savedState.K = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new StaggeredGridLayoutManager.SavedState[i];
    }
}
