package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class dk implements Iterator, wz {
    public int b = -1;
    public int c;
    public int d;
    public my e;
    public final /* synthetic */ ek f;

    public dk(ek ekVar) {
        this.f = ekVar;
        ekVar.getClass();
        int length = ((String) ekVar.b).length();
        if (length < 0) {
            throw new IllegalArgumentException(hk.h(length, "Cannot coerce value to an empty range: maximum ", " is less than minimum 0."));
        }
        length = length >= 0 ? 0 : length;
        this.c = length;
        this.d = length;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.Lambda, ru] */
    public final void a() {
        Pair pair;
        int i = this.d;
        if (i < 0) {
            this.b = 0;
            this.e = null;
            return;
        }
        ek ekVar = this.f;
        ekVar.getClass();
        String str = (String) ekVar.b;
        if (i <= str.length() && (pair = (Pair) ((Lambda) ekVar.c).invoke(str, Integer.valueOf(this.d))) != null) {
            int iIntValue = ((Number) pair.component1()).intValue();
            int iIntValue2 = ((Number) pair.component2()).intValue();
            this.e = za0.Q(this.c, iIntValue);
            int i2 = iIntValue + iIntValue2;
            this.c = i2;
            this.d = i2 + (iIntValue2 == 0 ? 1 : 0);
        } else {
            this.e = new my(this.c, vk0.m0(str), 1);
            this.d = -1;
        }
        this.b = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b == -1) {
            a();
        }
        return this.b == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b == -1) {
            a();
        }
        if (this.b == 0) {
            throw new NoSuchElementException();
        }
        my myVar = this.e;
        oy.h(myVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.e = null;
        this.b = -1;
        return myVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
