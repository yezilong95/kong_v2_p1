package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        StaggeredGridLayoutManager.SavedState savedState = new StaggeredGridLayoutManager.SavedState();
        savedState.b = parcel.readInt();
        savedState.c = parcel.readInt();
        int i = parcel.readInt();
        savedState.d = i;
        if (i > 0) {
            int[] iArr = new int[i];
            savedState.e = iArr;
            parcel.readIntArray(iArr);
        }
        int i2 = parcel.readInt();
        savedState.f = i2;
        if (i2 > 0) {
            int[] iArr2 = new int[i2];
            savedState.g = iArr2;
            parcel.readIntArray(iArr2);
        }
        savedState.i = parcel.readInt() == 1;
        savedState.j = parcel.readInt() == 1;
        savedState.k = parcel.readInt() == 1;
        savedState.h = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new StaggeredGridLayoutManager.SavedState[i];
    }
}
