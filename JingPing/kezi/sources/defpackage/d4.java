package defpackage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes.dex */
public final class d4 implements ra0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ FragmentActivity b;

    public /* synthetic */ d4(FragmentActivity fragmentActivity, int i) {
        this.a = i;
        this.b = fragmentActivity;
    }

    @Override // defpackage.ra0
    public final void a() {
        switch (this.a) {
            case 0:
                AppCompatActivity appCompatActivity = (AppCompatActivity) this.b;
                k4 k4VarJ = appCompatActivity.j();
                k4VarJ.a();
                ((e5) appCompatActivity.f.d).d("androidx:appcompat");
                k4VarJ.d();
                break;
            default:
                FragmentActivity fragmentActivity = this.b;
                o70 o70Var = fragmentActivity.v;
                kt ktVar = (kt) o70Var.c;
                ktVar.w.b(ktVar, ktVar, null);
                Bundle bundleD = ((e5) fragmentActivity.f.d).d("android:support:fragments");
                if (bundleD != null) {
                    ((kt) o70Var.c).w.M(bundleD.getParcelable("android:support:fragments"));
                }
                break;
        }
    }
}
