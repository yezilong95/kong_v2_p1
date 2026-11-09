package defpackage;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class r90 implements ListIterator {
    public final ListBuilder E;
    public int F;
    public int G = -1;
    public int H;

    public r90(ListBuilder listBuilder, int i) {
        this.E = listBuilder;
        this.F = i;
        this.H = ((AbstractList) listBuilder).modCount;
    }

    public final void a() {
        if (((AbstractList) this.E).modCount != this.H) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        a();
        int i = this.F;
        this.F = i + 1;
        ListBuilder listBuilder = this.E;
        listBuilder.add(i, obj);
        this.G = -1;
        this.H = ((AbstractList) listBuilder).modCount;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.F < this.E.F;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.F > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        a();
        int i = this.F;
        ListBuilder listBuilder = this.E;
        if (i >= listBuilder.F) {
            throw new NoSuchElementException();
        }
        int i2 = this.F;
        this.F = i2 + 1;
        this.G = i2;
        return listBuilder.E[this.G];
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
        return this.E.E[this.G];
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
        ListBuilder listBuilder = this.E;
        listBuilder.remove(i);
        this.F = this.G;
        this.G = -1;
        this.H = ((AbstractList) listBuilder).modCount;
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
