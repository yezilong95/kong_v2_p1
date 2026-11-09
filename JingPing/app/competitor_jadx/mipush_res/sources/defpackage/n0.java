package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import androidx.fragment.app.Fragment$SavedState;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.stateful.ExtendableSavedState;

/* JADX INFO: loaded from: classes.dex */
public final class n0 implements Parcelable.ClassLoaderCreator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.a) {
            case 0:
                if (parcel.readParcelable(classLoader) == null) {
                    return AbsSavedState.F;
                }
                throw new IllegalStateException("superState must be null");
            case 1:
                return new ExtendableSavedState(parcel, classLoader);
            case 2:
                return new Fragment$SavedState(parcel, classLoader);
            default:
                return new ParcelableSparseArray(parcel, classLoader);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new AbsSavedState[i];
            case 1:
                return new ExtendableSavedState[i];
            case 2:
                return new Fragment$SavedState[i];
            default:
                return new ParcelableSparseArray[i];
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                if (parcel.readParcelable(null) == null) {
                    return AbsSavedState.F;
                }
                throw new IllegalStateException("superState must be null");
            case 1:
                return new ExtendableSavedState(parcel, null);
            case 2:
                return new Fragment$SavedState(parcel, null);
            default:
                return new ParcelableSparseArray(parcel, null);
        }
    }
}
