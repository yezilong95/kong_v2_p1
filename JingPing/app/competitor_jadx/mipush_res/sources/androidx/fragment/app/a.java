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
                fragmentManager$LaunchedFragmentInfo.E = parcel.readString();
                fragmentManager$LaunchedFragmentInfo.F = parcel.readInt();
                return fragmentManager$LaunchedFragmentInfo;
            case 2:
                FragmentManagerState fragmentManagerState = new FragmentManagerState();
                fragmentManagerState.I = null;
                fragmentManagerState.J = new ArrayList();
                fragmentManagerState.K = new ArrayList();
                fragmentManagerState.E = parcel.createTypedArrayList(FragmentState.CREATOR);
                fragmentManagerState.F = parcel.createStringArrayList();
                fragmentManagerState.G = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
                fragmentManagerState.H = parcel.readInt();
                fragmentManagerState.I = parcel.readString();
                fragmentManagerState.J = parcel.createStringArrayList();
                fragmentManagerState.K = parcel.createTypedArrayList(Bundle.CREATOR);
                fragmentManagerState.L = parcel.createTypedArrayList(FragmentManager$LaunchedFragmentInfo.CREATOR);
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
