package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class vb0 extends ub0 implements Iterator {
    @Override // java.util.Iterator
    public final Object next() {
        b();
        int i = this.E;
        MapBuilder mapBuilder = (MapBuilder) this.H;
        if (i >= mapBuilder.J) {
            throw new NoSuchElementException();
        }
        int i2 = this.E;
        this.E = i2 + 1;
        this.F = i2;
        Object obj = mapBuilder.E[this.F];
        e();
        return obj;
    }
}
