package defpackage;

import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class rs0 extends id0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ rs0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.id0
    public final void a() {
        switch (this.a) {
            case 0:
                ViewPager2 viewPager2 = (ViewPager2) this.b;
                viewPager2.f = true;
                viewPager2.m.l = true;
                break;
            default:
                ((ba) this.b).h();
                break;
        }
    }
}
