package kotlin.collections.builders;

import defpackage.j31;
import defpackage.q8;
import defpackage.r90;
import defpackage.s90;
import defpackage.t40;
import defpackage.tm;
import defpackage.w0;
import defpackage.zn;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class ListBuilder<E> extends w0 implements List<E>, RandomAccess, Serializable {
    public static final ListBuilder H;
    public Object[] E;
    public int F;
    public boolean G;

    public static final class BuilderSubList<E> extends w0 implements List<E>, RandomAccess, Serializable {
        public Object[] E;
        public final int F;
        public int G;
        public final BuilderSubList H;
        public final ListBuilder I;

        public BuilderSubList(E[] eArr, int i, int i2, BuilderSubList<E> builderSubList, ListBuilder<E> listBuilder) {
            t40.n(eArr, "backing");
            t40.n(listBuilder, "root");
            this.E = eArr;
            this.F = i;
            this.G = i2;
            this.H = builderSubList;
            this.I = listBuilder;
            ((AbstractList) this).modCount = ((AbstractList) listBuilder).modCount;
        }

        private final Object writeReplace() throws NotSerializableException {
            if (this.I.G) {
                return new SerializedCollection(this, 0);
            }
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
        }

        public final void a(int i, Collection collection, int i2) {
            ((AbstractList) this).modCount++;
            ListBuilder listBuilder = this.I;
            BuilderSubList builderSubList = this.H;
            if (builderSubList != null) {
                builderSubList.a(i, collection, i2);
            } else {
                listBuilder.a(i, collection, i2);
            }
            this.E = listBuilder.E;
            this.G += i2;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E e) {
            d();
            c();
            b(this.F + this.G, e);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> collection) {
            t40.n(collection, "elements");
            d();
            c();
            int size = collection.size();
            a(this.F + this.G, collection, size);
            return size > 0;
        }

        public final void b(int i, Object obj) {
            ((AbstractList) this).modCount++;
            ListBuilder listBuilder = this.I;
            BuilderSubList builderSubList = this.H;
            if (builderSubList != null) {
                builderSubList.b(i, obj);
            } else {
                ListBuilder.access$addAtInternal(listBuilder, i, obj);
            }
            this.E = listBuilder.E;
            this.G++;
        }

        public final void c() {
            if (((AbstractList) this.I).modCount != ((AbstractList) this).modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            d();
            c();
            f(this.F, this.G);
        }

        public final void d() {
            if (this.I.G) {
                throw new UnsupportedOperationException();
            }
        }

        public final Object e(int i) {
            ((AbstractList) this).modCount++;
            BuilderSubList builderSubList = this.H;
            this.G--;
            return builderSubList != null ? builderSubList.e(i) : this.I.d(i);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            c();
            if (obj == this) {
                return true;
            }
            if (obj instanceof List) {
                return s90.f(this.E, this.F, this.G, (List) obj);
            }
            return false;
        }

        public final void f(int i, int i2) {
            if (i2 > 0) {
                ((AbstractList) this).modCount++;
            }
            BuilderSubList builderSubList = this.H;
            if (builderSubList != null) {
                builderSubList.f(i, i2);
            } else {
                this.I.e(i, i2);
            }
            this.G -= i2;
        }

        public final int g(int i, int i2, Collection collection, boolean z) {
            BuilderSubList builderSubList = this.H;
            int iG = builderSubList != null ? builderSubList.g(i, i2, collection, z) : this.I.f(i, i2, collection, z);
            if (iG > 0) {
                ((AbstractList) this).modCount++;
            }
            this.G -= iG;
            return iG;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            c();
            int i2 = this.G;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
            }
            return (E) this.E[this.F + i];
        }

        @Override // defpackage.w0
        public int getSize() {
            c();
            return this.G;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            c();
            Object[] objArr = this.E;
            int i = this.G;
            int iHashCode = 1;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[this.F + i2];
                iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            c();
            for (int i = 0; i < this.G; i++) {
                if (t40.f(this.E[this.F + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            c();
            return this.G == 0;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            c();
            for (int i = this.G - 1; i >= 0; i--) {
                if (t40.f(this.E[this.F + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            d();
            c();
            int iIndexOf = indexOf(obj);
            if (iIndexOf >= 0) {
                remove(iIndexOf);
            }
            return iIndexOf >= 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<? extends Object> collection) {
            t40.n(collection, "elements");
            d();
            c();
            return g(this.F, this.G, collection, false) > 0;
        }

        @Override // defpackage.w0
        public E removeAt(int i) {
            d();
            c();
            int i2 = this.G;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
            }
            return (E) e(this.F + i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<? extends Object> collection) {
            t40.n(collection, "elements");
            d();
            c();
            return g(this.F, this.G, collection, true) > 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public E set(int i, E e) {
            d();
            c();
            int i2 = this.G;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
            }
            Object[] objArr = this.E;
            int i3 = this.F;
            E e2 = (E) objArr[i3 + i];
            objArr[i3 + i] = e;
            return e2;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<E> subList(int i, int i2) {
            j31.k(i, i2, this.G);
            return new BuilderSubList(this.E, this.F + i, i2 - i, this, this.I);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            t40.n(tArr, "array");
            c();
            int length = tArr.length;
            int i = this.G;
            int i2 = this.F;
            if (length < i) {
                T[] tArr2 = (T[]) Arrays.copyOfRange(this.E, i2, i + i2, tArr.getClass());
                t40.m(tArr2, "copyOfRange(...)");
                return tArr2;
            }
            q8.O0(this.E, tArr, 0, i2, i + i2);
            int i3 = this.G;
            if (i3 < tArr.length) {
                tArr[i3] = null;
            }
            return tArr;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            c();
            return s90.g(this.E, this.F, this.G, this);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int i) {
            c();
            int i2 = this.G;
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
            }
            return new a(this, i);
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, E e) {
            d();
            c();
            int i2 = this.G;
            if (i >= 0 && i <= i2) {
                b(this.F + i, e);
                return;
            }
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            t40.n(collection, "elements");
            d();
            c();
            int i2 = this.G;
            if (i >= 0 && i <= i2) {
                int size = collection.size();
                a(this.F + i, collection, size);
                return size > 0;
            }
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            c();
            Object[] objArr = this.E;
            int i = this.G;
            int i2 = this.F;
            return q8.P0(objArr, i2, i + i2);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.G = true;
        H = listBuilder;
    }

    public ListBuilder() {
        this(0, 1, null);
    }

    public static final void access$addAtInternal(ListBuilder listBuilder, int i, Object obj) {
        ((AbstractList) listBuilder).modCount++;
        listBuilder.c(i, 1);
        listBuilder.E[i] = obj;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.G) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    public final void a(int i, Collection collection, int i2) {
        ((AbstractList) this).modCount++;
        c(i, i2);
        Iterator<E> it = collection.iterator();
        for (int i3 = 0; i3 < i2; i3++) {
            this.E[i + i3] = it.next();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        b();
        int i = this.F;
        ((AbstractList) this).modCount++;
        c(i, 1);
        this.E[i] = e;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        t40.n(collection, "elements");
        b();
        int size = collection.size();
        a(this.F, collection, size);
        return size > 0;
    }

    public final void b() {
        if (this.G) {
            throw new UnsupportedOperationException();
        }
    }

    public final List<E> build() {
        b();
        this.G = true;
        return this.F > 0 ? this : H;
    }

    public final void c(int i, int i2) {
        int i3 = this.F + i2;
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.E;
        if (i3 > objArr.length) {
            int length = objArr.length;
            int i4 = length + (length >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                i4 = i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf = Arrays.copyOf(objArr, i4);
            t40.m(objArrCopyOf, "copyOf(...)");
            this.E = objArrCopyOf;
        }
        Object[] objArr2 = this.E;
        q8.O0(objArr2, objArr2, i + i2, i, this.F);
        this.F += i2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        b();
        e(0, this.F);
    }

    public final Object d(int i) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.E;
        Object obj = objArr[i];
        q8.O0(objArr, objArr, i, i + 1, this.F);
        Object[] objArr2 = this.E;
        int i2 = this.F - 1;
        t40.n(objArr2, "<this>");
        objArr2[i2] = null;
        this.F--;
        return obj;
    }

    public final void e(int i, int i2) {
        if (i2 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.E;
        q8.O0(objArr, objArr, i, i + i2, this.F);
        Object[] objArr2 = this.E;
        int i3 = this.F;
        s90.R(objArr2, i3 - i2, i3);
        this.F -= i2;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (s90.f(this.E, 0, this.F, (List) obj)) {
                return true;
            }
        }
        return false;
    }

    public final int f(int i, int i2, Collection collection, boolean z) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.E[i5]) == z) {
                Object[] objArr = this.E;
                i3++;
                objArr[i4 + i] = objArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        Object[] objArr2 = this.E;
        q8.O0(objArr2, objArr2, i + i4, i2 + i, this.F);
        Object[] objArr3 = this.E;
        int i7 = this.F;
        s90.R(objArr3, i7 - i6, i7);
        if (i6 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.F -= i6;
        return i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2 = this.F;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }
        return (E) this.E[i];
    }

    @Override // defpackage.w0
    public int getSize() {
        return this.F;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        Object[] objArr = this.E;
        int i = this.F;
        int iHashCode = 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        for (int i = 0; i < this.F; i++) {
            if (t40.f(this.E[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.F == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        for (int i = this.F - 1; i >= 0; i--) {
            if (t40.f(this.E[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        b();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            remove(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> collection) {
        t40.n(collection, "elements");
        b();
        return f(0, this.F, collection, false) > 0;
    }

    @Override // defpackage.w0
    public E removeAt(int i) {
        b();
        int i2 = this.F;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }
        return (E) d(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> collection) {
        t40.n(collection, "elements");
        b();
        return f(0, this.F, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        b();
        int i2 = this.F;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }
        Object[] objArr = this.E;
        E e2 = (E) objArr[i];
        objArr[i] = e;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int i, int i2) {
        j31.k(i, i2, this.F);
        return new BuilderSubList(this.E, i, i2 - i, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        t40.n(tArr, "array");
        int length = tArr.length;
        int i = this.F;
        if (length < i) {
            T[] tArr2 = (T[]) Arrays.copyOfRange(this.E, 0, i, tArr.getClass());
            t40.m(tArr2, "copyOfRange(...)");
            return tArr2;
        }
        q8.O0(this.E, tArr, 0, 0, i);
        int i2 = this.F;
        if (i2 < tArr.length) {
            tArr[i2] = null;
        }
        return tArr;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return s90.g(this.E, 0, this.F, this);
    }

    public /* synthetic */ ListBuilder(int i, int i2, tm tmVar) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int i) {
        int i2 = this.F;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
        }
        return new r90(this, i);
    }

    public ListBuilder(int i) {
        if (i >= 0) {
            this.E = new Object[i];
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        t40.n(collection, "elements");
        b();
        int i2 = this.F;
        if (i >= 0 && i <= i2) {
            int size = collection.size();
            a(i, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        b();
        int i2 = this.F;
        if (i >= 0 && i <= i2) {
            ((AbstractList) this).modCount++;
            c(i, 1);
            this.E[i] = e;
            return;
        }
        throw new IndexOutOfBoundsException(zn.l("index: ", i, i2, ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return q8.P0(this.E, 0, this.F);
    }
}
