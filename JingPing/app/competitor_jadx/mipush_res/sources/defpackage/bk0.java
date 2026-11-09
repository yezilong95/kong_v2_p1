package defpackage;

import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public final class bk0 extends DataSetObserver implements x51, w51 {
    public final /* synthetic */ PagerTitleStrip a;

    public bk0(PagerTitleStrip pagerTitleStrip) {
        this.a = pagerTitleStrip;
    }

    @Override // defpackage.x51
    public final void b(int i, float f) {
        if (f > 0.5f) {
            i++;
        }
        this.a.c(f, i, false);
    }

    @Override // defpackage.w51
    public final void c(ViewPager viewPager) {
        this.a.a();
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        PagerTitleStrip pagerTitleStrip = this.a;
        int currentItem = pagerTitleStrip.E.getCurrentItem();
        pagerTitleStrip.E.getAdapter();
        pagerTitleStrip.b(currentItem);
        float f = pagerTitleStrip.J;
        if (f < 0.0f) {
            f = 0.0f;
        }
        pagerTitleStrip.c(f, pagerTitleStrip.E.getCurrentItem(), true);
    }

    @Override // defpackage.x51
    public final void a(int i) {
    }
}
