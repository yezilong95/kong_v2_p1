package defpackage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes.dex */
public final class h5 implements fj0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ FragmentActivity b;

    public /* synthetic */ h5(FragmentActivity fragmentActivity, int i) {
        this.a = i;
        this.b = fragmentActivity;
    }

    @Override // defpackage.fj0
    public final void a() {
        switch (this.a) {
            case 0:
                AppCompatActivity appCompatActivity = (AppCompatActivity) this.b;
                o5 o5VarJ = appCompatActivity.j();
                o5VarJ.a();
                ((i6) appCompatActivity.I.G).d("androidx:appcompat");
                o5VarJ.d();
                break;
            default:
                FragmentActivity fragmentActivity = this.b;
                z8 z8Var = fragmentActivity.W;
                wz wzVar = (wz) z8Var.F;
                wzVar.e0.b(wzVar, wzVar, null);
                Bundle bundleD = ((i6) fragmentActivity.I.G).d("android:support:fragments");
                if (bundleD != null) {
                    ((wz) z8Var.F).e0.L(bundleD.getParcelable("android:support:fragments"));
                }
                break;
        }
    }
}
