package defpackage;

import android.view.View;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public final class ak0 implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ PagerTabStrip F;

    public /* synthetic */ ak0(PagerTabStrip pagerTabStrip, int i) {
        this.E = i;
        this.F = pagerTabStrip;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.E) {
            case 0:
                this.F.E.setCurrentItem(r2.getCurrentItem() - 1);
                break;
            default:
                ViewPager viewPager = this.F.E;
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}
