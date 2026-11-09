package defpackage;

import android.os.Build;
import android.view.View;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class ub0 {
    public int E;
    public int F;
    public int G;
    public Object H;

    public ub0() {
        if (er0.E == null) {
            er0.E = new er0();
        }
    }

    public int a(int i) {
        if (i < this.G) {
            return ((ByteBuffer) this.H).getShort(this.F + i);
        }
        return 0;
    }

    public void b() {
        if (((MapBuilder) this.H).L != this.G) {
            throw new ConcurrentModificationException();
        }
    }

    public abstract Object c(View view);

    public abstract void d(View view, Object obj);

    public void e() {
        while (true) {
            int i = this.E;
            MapBuilder mapBuilder = (MapBuilder) this.H;
            if (i >= mapBuilder.J) {
                return;
            }
            int[] iArr = mapBuilder.G;
            int i2 = this.E;
            if (iArr[i2] >= 0) {
                return;
            } else {
                this.E = i2 + 1;
            }
        }
    }

    public void f(View view, Object obj) {
        Object tag;
        if (Build.VERSION.SDK_INT >= this.F) {
            d(view, obj);
            return;
        }
        if (Build.VERSION.SDK_INT >= this.F) {
            tag = c(view);
        } else {
            tag = view.getTag(this.E);
            if (!((Class) this.H).isInstance(tag)) {
                tag = null;
            }
        }
        if (g(tag, obj)) {
            View.AccessibilityDelegate accessibilityDelegateD = f51.d(view);
            y1 y1Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof x1 ? ((x1) accessibilityDelegateD).a : new y1(accessibilityDelegateD);
            if (y1Var == null) {
                y1Var = new y1();
            }
            f51.o(view, y1Var);
            view.setTag(this.E, obj);
            f51.i(view, this.G);
        }
    }

    public abstract boolean g(Object obj, Object obj2);

    public boolean hasNext() {
        return this.E < ((MapBuilder) this.H).J;
    }

    public void remove() {
        b();
        if (this.F == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        MapBuilder mapBuilder = (MapBuilder) this.H;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        mapBuilder.g(this.F);
        this.F = -1;
        this.G = mapBuilder.L;
    }

    public ub0(MapBuilder mapBuilder) {
        this.H = mapBuilder;
        this.F = -1;
        this.G = mapBuilder.L;
        e();
    }
}
