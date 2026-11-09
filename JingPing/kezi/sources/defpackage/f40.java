package defpackage;

import android.os.Build;
import android.view.View;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class f40 {
    public int b;
    public int c;
    public int d;
    public Object e;

    public f40() {
        if (gq0.b == null) {
            gq0.b = new gq0();
        }
    }

    public int a(int i) {
        if (i < this.d) {
            return ((ByteBuffer) this.e).getShort(this.c + i);
        }
        return 0;
    }

    public void b() {
        if (((MapBuilder) this.e).i != this.d) {
            throw new ConcurrentModificationException();
        }
    }

    public abstract Object c(View view);

    public abstract void d(View view, Object obj);

    public void e() {
        while (true) {
            int i = this.b;
            MapBuilder mapBuilder = (MapBuilder) this.e;
            if (i >= mapBuilder.g) {
                return;
            }
            int[] iArr = mapBuilder.d;
            int i2 = this.b;
            if (iArr[i2] >= 0) {
                return;
            } else {
                this.b = i2 + 1;
            }
        }
    }

    public void f(View view, Object obj) {
        Object tag;
        if (Build.VERSION.SDK_INT >= this.c) {
            d(view, obj);
            return;
        }
        if (Build.VERSION.SDK_INT >= this.c) {
            tag = c(view);
        } else {
            tag = view.getTag(this.b);
            if (!((Class) this.e).isInstance(tag)) {
                tag = null;
            }
        }
        if (g(tag, obj)) {
            View.AccessibilityDelegate accessibilityDelegateD = xr0.d(view);
            b0 b0Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof a0 ? ((a0) accessibilityDelegateD).a : new b0(accessibilityDelegateD);
            if (b0Var == null) {
                b0Var = new b0();
            }
            xr0.p(view, b0Var);
            view.setTag(this.b, obj);
            xr0.i(view, this.d);
        }
    }

    public abstract boolean g(Object obj, Object obj2);

    public boolean hasNext() {
        return this.b < ((MapBuilder) this.e).g;
    }

    public void remove() {
        b();
        if (this.c == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        MapBuilder mapBuilder = (MapBuilder) this.e;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        mapBuilder.g(this.c);
        this.c = -1;
        this.d = mapBuilder.i;
    }

    public f40(MapBuilder mapBuilder) {
        this.e = mapBuilder;
        this.c = -1;
        this.d = mapBuilder.i;
        e();
    }
}
