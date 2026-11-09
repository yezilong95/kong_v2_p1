package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new BackStackState(parcel);
            case 1:
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = new FragmentManager$LaunchedFragmentInfo();
                fragmentManager$LaunchedFragmentInfo.b = parcel.readString();
                fragmentManager$LaunchedFragmentInfo.c = parcel.readInt();
                return fragmentManager$LaunchedFragmentInfo;
            case 2:
                FragmentManagerState fragmentManagerState = new FragmentManagerState();
                fragmentManagerState.f = null;
                fragmentManagerState.g = new ArrayList();
                fragmentManagerState.h = new ArrayList();
                fragmentManagerState.b = parcel.createTypedArrayList(FragmentState.CREATOR);
                fragmentManagerState.c = parcel.createStringArrayList();
                fragmentManagerState.d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
                fragmentManagerState.e = parcel.readInt();
                fragmentManagerState.f = parcel.readString();
                fragmentManagerState.g = parcel.createStringArrayList();
                fragmentManagerState.h = parcel.createTypedArrayList(Bundle.CREATOR);
                fragmentManagerState.i = parcel.createTypedArrayList(FragmentManager$LaunchedFragmentInfo.CREATOR);
                return fragmentManagerState;
            default:
                return new FragmentState(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new BackStackState[i];
            case 1:
                return new FragmentManager$LaunchedFragmentInfo[i];
            case 2:
                return new FragmentManagerState[i];
            default:
                return new FragmentState[i];
        }
    }
}
