package defpackage;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class z51 extends c61 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ z51(ViewPager2 viewPager2, int i) {
        this.a = i;
        this.b = viewPager2;
    }

    @Override // defpackage.c61
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
                        ((c61) it.next()).a(i);
                        break;
                    }
                    return;
                } catch (ConcurrentModificationException e) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
                }
        }
    }

    @Override // defpackage.c61
    public void b(int i, float f, int i2) {
        switch (this.a) {
            case 2:
                try {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        ((c61) it.next()).b(i, f, i2);
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

    @Override // defpackage.c61
    public final void c(int i) {
        switch (this.a) {
            case 0:
                ViewPager2 viewPager2 = (ViewPager2) this.b;
                if (viewPager2.H != i) {
                    viewPager2.H = i;
                    viewPager2.a0.f();
                    return;
                }
                return;
            case 1:
                ViewPager2 viewPager22 = (ViewPager2) this.b;
                viewPager22.clearFocus();
                if (viewPager22.hasFocus()) {
                    viewPager22.N.requestFocus(2);
                    return;
                }
                return;
            default:
                try {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        ((c61) it.next()).c(i);
                        break;
                    }
                    return;
                } catch (ConcurrentModificationException e) {
                    throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", e);
                }
        }
    }

    public z51() {
        this.a = 2;
        this.b = new ArrayList(3);
    }
}
