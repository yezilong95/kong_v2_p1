package defpackage;

import android.view.View;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public final class gb0 implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ PagerTabStrip c;

    public /* synthetic */ gb0(PagerTabStrip pagerTabStrip, int i) {
        this.b = i;
        this.c = pagerTabStrip;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.b) {
            case 0:
                this.c.b.setCurrentItem(r2.getCurrentItem() - 1);
                break;
            default:
                ViewPager viewPager = this.c.b;
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}
