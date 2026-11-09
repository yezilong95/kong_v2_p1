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
public final class c4 implements sg0 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ c4(FragmentActivity fragmentActivity, int i) {
        this.a = i;
        this.b = fragmentActivity;
    }

    @Override // defpackage.sg0
    public final Bundle a() {
        o70 o70Var;
        switch (this.a) {
            case 0:
                Bundle bundle = new Bundle();
                ((AppCompatActivity) this.b).j().getClass();
                return bundle;
            case 1:
                Bundle bundle2 = new Bundle();
                FragmentActivity fragmentActivity = (FragmentActivity) this.b;
                do {
                    o70Var = fragmentActivity.v;
                } while (FragmentActivity.i(((kt) o70Var.c).w, Lifecycle$State.CREATED));
                fragmentActivity.w.e(Lifecycle$Event.ON_STOP);
                Parcelable parcelableN = ((kt) o70Var.c).w.N();
                if (parcelableN != null) {
                    bundle2.putParcelable("android:support:fragments", parcelableN);
                }
                return bundle2;
            default:
                Bundle bundleG = rp0.g((Pair[]) Arrays.copyOf(new Pair[0], 0));
                List listF0 = ae.f0((LinkedHashSet) this.b);
                oy.j(listF0, "value");
                bundleG.putStringArrayList("classes_to_restore", listF0 instanceof ArrayList ? (ArrayList) listF0 : new ArrayList<>(listF0));
                return bundleG;
        }
    }

    public c4(e5 e5Var) {
        this.a = 2;
        oy.j(e5Var, "registry");
        this.b = new LinkedHashSet();
        e5Var.M("androidx.savedstate.Restarter", this);
    }
}
