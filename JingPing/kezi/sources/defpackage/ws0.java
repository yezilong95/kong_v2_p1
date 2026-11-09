package defpackage;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class ws0 implements y0 {
    public final /* synthetic */ int b;
    public final /* synthetic */ ba c;

    public /* synthetic */ ws0(ba baVar, int i) {
        this.b = i;
        this.c = baVar;
    }

    @Override // defpackage.y0
    public final boolean c(View view) {
        switch (this.b) {
            case 0:
                int currentItem = ((ViewPager2) view).getCurrentItem() + 1;
                ViewPager2 viewPager2 = (ViewPager2) this.c.d;
                if (viewPager2.s) {
                    viewPager2.c(currentItem);
                }
                break;
            default:
                int currentItem2 = ((ViewPager2) view).getCurrentItem() - 1;
                ViewPager2 viewPager22 = (ViewPager2) this.c.d;
                if (viewPager22.s) {
                    viewPager22.c(currentItem2);
                }
                break;
        }
        return true;
    }
}
