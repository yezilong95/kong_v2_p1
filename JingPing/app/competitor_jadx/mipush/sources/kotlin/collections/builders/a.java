package kotlin.collections.builders;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ListIterator {
    public final ListBuilder.BuilderSubList E;
    public int F;
    public int G = -1;
    public int H;

    public a(ListBuilder.BuilderSubList builderSubList, int i) {
        this.E = builderSubList;
        this.F = i;
        this.H = ((AbstractList) builderSubList).modCount;
    }

    public final void a() {
        if (((AbstractList) this.E.I).modCount != this.H) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        a();
        int i = this.F;
        this.F = i + 1;
        ListBuilder.BuilderSubList builderSubList = this.E;
        builderSubList.add(i, obj);
        this.G = -1;
        this.H = ((AbstractList) builderSubList).modCount;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.F < this.E.G;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.F > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        a();
        int i = this.F;
        ListBuilder.BuilderSubList builderSubList = this.E;
        if (i >= builderSubList.G) {
            throw new NoSuchElementException();
        }
        int i2 = this.F;
        this.F = i2 + 1;
        this.G = i2;
        return builderSubList.E[builderSubList.F + this.G];
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.F;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        a();
        int i = this.F;
        if (i <= 0) {
            throw new NoSuchElementException();
        }
        int i2 = i - 1;
        this.F = i2;
        this.G = i2;
        ListBuilder.BuilderSubList builderSubList = this.E;
        return builderSubList.E[builderSubList.F + this.G];
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.F - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        a();
        int i = this.G;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
        }
        ListBuilder.BuilderSubList builderSubList = this.E;
        builderSubList.remove(i);
        this.F = this.G;
        this.G = -1;
        this.H = ((AbstractList) builderSubList).modCount;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        a();
        int i = this.G;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
        }
        this.E.set(i, obj);
    }
}
