package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = new ActionMenuPresenter$SavedState();
        actionMenuPresenter$SavedState.E = parcel.readInt();
        return actionMenuPresenter$SavedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ActionMenuPresenter$SavedState[i];
    }
}
