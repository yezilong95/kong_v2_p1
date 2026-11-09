package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class yh0 implements Iterator, fh, wz {
    public int b;
    public String c;
    public fh d;

    public final RuntimeException a() {
        int i = this.b;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.b);
    }

    @Override // defpackage.fh
    public final nh getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.b;
            if (i != 0) {
                break;
            }
            this.b = 5;
            fh fhVar = this.d;
            oy.g(fhVar);
            this.d = null;
            fhVar.resumeWith(Result.m3constructorimpl(bq0.a));
        }
        if (i == 1) {
            oy.g(null);
            throw null;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        throw a();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.b;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.b = 1;
            oy.g(null);
            throw null;
        }
        if (i != 3) {
            throw a();
        }
        this.b = 0;
        String str = this.c;
        this.c = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) throws Throwable {
        a.b(obj);
        this.b = 4;
    }
}
