package kotlin.collections.builders;

import defpackage.wz;
import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ListIterator, wz {
    public final ListBuilder.BuilderSubList b;
    public int c;
    public int d = -1;
    public int e;

    public a(ListBuilder.BuilderSubList builderSubList, int i) {
        this.b = builderSubList;
        this.c = i;
        this.e = ((AbstractList) builderSubList).modCount;
    }

    public final void a() {
        if (((AbstractList) this.b.f).modCount != this.e) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        a();
        int i = this.c;
        this.c = i + 1;
        ListBuilder.BuilderSubList builderSubList = this.b;
        builderSubList.add(i, obj);
        this.d = -1;
        this.e = ((AbstractList) builderSubList).modCount;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.b.d;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        a();
        int i = this.c;
        ListBuilder.BuilderSubList builderSubList = this.b;
        if (i >= builderSubList.d) {
            throw new NoSuchElementException();
        }
        int i2 = this.c;
        this.c = i2 + 1;
        this.d = i2;
        return builderSubList.b[builderSubList.c + this.d];
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.c;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        a();
        int i = this.c;
        if (i <= 0) {
            throw new NoSuchElementException();
        }
        int i2 = i - 1;
        this.c = i2;
        this.d = i2;
        ListBuilder.BuilderSubList builderSubList = this.b;
        return builderSubList.b[builderSubList.c + this.d];
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.c - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        a();
        int i = this.d;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
        }
        ListBuilder.BuilderSubList builderSubList = this.b;
        builderSubList.remove(i);
        this.c = this.d;
        this.d = -1;
        this.e = ((AbstractList) builderSubList).modCount;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        a();
        int i = this.d;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
        }
        this.b.set(i, obj);
    }
}
