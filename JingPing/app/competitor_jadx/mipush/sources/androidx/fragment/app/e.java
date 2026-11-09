package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentTabHost;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        FragmentTabHost.SavedState savedState = new FragmentTabHost.SavedState(parcel);
        savedState.E = parcel.readString();
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new FragmentTabHost.SavedState[i];
    }
}
