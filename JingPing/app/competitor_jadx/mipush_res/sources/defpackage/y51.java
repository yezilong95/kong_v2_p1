package defpackage;

import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class y51 extends en0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ y51(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.en0
    public final void a() {
        switch (this.a) {
            case 0:
                ViewPager2 viewPager2 = (ViewPager2) this.b;
                viewPager2.I = true;
                viewPager2.P.l = true;
                break;
            default:
                ((tb) this.b).f();
                break;
        }
    }
}
