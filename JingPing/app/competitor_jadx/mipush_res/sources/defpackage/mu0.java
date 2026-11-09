package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class mu0 implements Iterator, ui {
    public int E;
    public String F;
    public ui G;

    public final RuntimeException a() {
        int i = this.E;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.E);
    }

    @Override // defpackage.ui
    public final bj getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.E;
            if (i != 0) {
                break;
            }
            this.E = 5;
            ui uiVar = this.G;
            t40.k(uiVar);
            this.G = null;
            uiVar.resumeWith(Result.m3constructorimpl(y21.a));
        }
        if (i == 1) {
            t40.k(null);
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
        int i = this.E;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.E = 1;
            t40.k(null);
            throw null;
        }
        if (i != 3) {
            throw a();
        }
        this.E = 0;
        String str = this.F;
        this.F = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        a.b(obj);
        this.E = 4;
    }
}
