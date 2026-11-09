package defpackage;

import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public final class hb0 extends DataSetObserver implements qs0, ps0 {
    public final /* synthetic */ PagerTitleStrip a;

    public hb0(PagerTitleStrip pagerTitleStrip) {
        this.a = pagerTitleStrip;
    }

    @Override // defpackage.qs0
    public final void a(int i, float f) {
        if (f > 0.5f) {
            i++;
        }
        this.a.c(f, i, false);
    }

    @Override // defpackage.ps0
    public final void c(ViewPager viewPager) {
        this.a.a();
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        PagerTitleStrip pagerTitleStrip = this.a;
        int currentItem = pagerTitleStrip.b.getCurrentItem();
        pagerTitleStrip.b.getAdapter();
        pagerTitleStrip.b(currentItem);
        float f = pagerTitleStrip.g;
        if (f < 0.0f) {
            f = 0.0f;
        }
        pagerTitleStrip.c(f, pagerTitleStrip.b.getCurrentItem(), true);
    }

    @Override // defpackage.qs0
    public final void b(int i) {
    }
}
