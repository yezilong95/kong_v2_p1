package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.LinearLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        LinearLayoutManager.SavedState savedState = new LinearLayoutManager.SavedState();
        savedState.b = parcel.readInt();
        savedState.c = parcel.readInt();
        savedState.d = parcel.readInt() == 1;
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new LinearLayoutManager.SavedState[i];
    }
}
