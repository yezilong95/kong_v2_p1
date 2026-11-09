package defpackage;

import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class dz0 implements x51 {
    public final WeakReference a;
    public int b;
    public int c;

    public dz0(TabLayout tabLayout) {
        this.a = new WeakReference(tabLayout);
    }

    @Override // defpackage.x51
    public final void a(int i) {
        this.b = this.c;
        this.c = i;
        TabLayout tabLayout = (TabLayout) this.a.get();
        if (tabLayout != null) {
            tabLayout.z0 = this.c;
        }
    }

    @Override // defpackage.x51
    public final void b(int i, float f) {
        boolean z;
        TabLayout tabLayout = (TabLayout) this.a.get();
        if (tabLayout != null) {
            int i2 = this.c;
            boolean z2 = true;
            if (i2 != 2 || this.b == 1) {
                z = true;
            } else {
                z = true;
                z2 = false;
            }
            if (i2 == 2 && this.b == 0) {
                z = false;
            }
            tabLayout.h(i, f, z2, z, false);
        }
    }
}
