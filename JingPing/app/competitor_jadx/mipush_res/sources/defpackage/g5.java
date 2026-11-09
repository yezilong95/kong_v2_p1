package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class g5 implements ir0 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ g5(FragmentActivity fragmentActivity, int i) {
        this.a = i;
        this.b = fragmentActivity;
    }

    @Override // defpackage.ir0
    public final Bundle a() {
        z8 z8Var;
        switch (this.a) {
            case 0:
                Bundle bundle = new Bundle();
                ((AppCompatActivity) this.b).j().getClass();
                return bundle;
            case 1:
                Bundle bundle2 = new Bundle();
                FragmentActivity fragmentActivity = (FragmentActivity) this.b;
                do {
                    z8Var = fragmentActivity.W;
                } while (FragmentActivity.i(((wz) z8Var.F).e0, Lifecycle$State.CREATED));
                fragmentActivity.X.e(Lifecycle$Event.ON_STOP);
                Parcelable parcelableM = ((wz) z8Var.F).e0.M();
                if (parcelableM != null) {
                    bundle2.putParcelable("android:support:fragments", parcelableM);
                }
                return bundle2;
            default:
                Bundle bundleH = j31.h((Pair[]) Arrays.copyOf(new Pair[0], 0));
                List listM1 = vf.m1((LinkedHashSet) this.b);
                t40.n(listM1, "value");
                bundleH.putStringArrayList("classes_to_restore", listM1 instanceof ArrayList ? (ArrayList) listM1 : new ArrayList<>(listM1));
                return bundleH;
        }
    }

    public g5(i6 i6Var) {
        this.a = 2;
        t40.n(i6Var, "registry");
        this.b = new LinkedHashSet();
        i6Var.L("androidx.savedstate.Restarter", this);
    }
}
