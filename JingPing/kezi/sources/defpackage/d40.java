package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class d40 extends f40 implements Iterator, wz {
    @Override // java.util.Iterator
    public final Object next() {
        b();
        int i = this.b;
        MapBuilder mapBuilder = (MapBuilder) this.e;
        if (i >= mapBuilder.g) {
            throw new NoSuchElementException();
        }
        int i2 = this.b;
        this.b = i2 + 1;
        this.c = i2;
        e40 e40Var = new e40(mapBuilder, i2);
        e();
        return e40Var;
    }
}
