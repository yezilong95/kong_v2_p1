package defpackage;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class km0 implements ps0 {
    public final /* synthetic */ TabLayout a;

    public km0(TabLayout tabLayout) {
        this.a = tabLayout;
    }

    @Override // defpackage.ps0
    public final void c(ViewPager viewPager) {
        TabLayout tabLayout = this.a;
        if (tabLayout.P == viewPager) {
            tabLayout.f();
        }
    }
}
