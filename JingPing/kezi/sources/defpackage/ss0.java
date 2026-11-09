package defpackage;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ss0 extends vs0 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ ss0(ViewPager2 viewPager2, int i) {
        this.a = i;
        this.b = viewPager2;
    }

    @Override // defpackage.vs0
    public void a(int i) {
        switch (this.a) {
            case 0:
                if (i == 0) {
                    ((ViewPager2) this.b).d();
                    return;
                }
                return;
            case 1:
            default:
                return;
            case 2:
                try {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        ((vs0) it.next()).a(i);
                        break;
                    }
                    return;
                } catch (ConcurrentModificationException e) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
                }
        }
    }

    @Override // defpackage.vs0
    public void b(int i, float f, int i2) {
        switch (this.a) {
            case 2:
                try {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        ((vs0) it.next()).b(i, f, i2);
                        break;
                    }
                    return;
                } catch (ConcurrentModificationException e) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
                }
            default:
                return;
        }
    }

    @Override // defpackage.vs0
    public final void c(int i) {
        switch (this.a) {
            case 0:
                ViewPager2 viewPager2 = (ViewPager2) this.b;
                if (viewPager2.e != i) {
                    viewPager2.e = i;
                    viewPager2.u.h();
                    return;
                }
                return;
            case 1:
                ViewPager2 viewPager22 = (ViewPager2) this.b;
                viewPager22.clearFocus();
                if (viewPager22.hasFocus()) {
                    viewPager22.k.requestFocus(2);
                    return;
                }
                return;
            default:
                try {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        ((vs0) it.next()).c(i);
                        break;
                    }
                    return;
                } catch (ConcurrentModificationException e) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
                }
        }
    }

    public ss0() {
        this.a = 2;
        this.b = new ArrayList(3);
    }
}
