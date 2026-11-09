package androidx.swiperefreshlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new SwipeRefreshLayout.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new SwipeRefreshLayout.SavedState[i];
    }
}
