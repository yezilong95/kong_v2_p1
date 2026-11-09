package defpackage;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class zy0 implements w51 {
    public final /* synthetic */ TabLayout a;

    public zy0(TabLayout tabLayout) {
        this.a = tabLayout;
    }

    @Override // defpackage.w51
    public final void c(ViewPager viewPager) {
        TabLayout tabLayout = this.a;
        if (tabLayout.v0 == viewPager) {
            tabLayout.f();
        }
    }
}
