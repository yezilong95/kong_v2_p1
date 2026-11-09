package kotlin.collections.builders;

import defpackage.aj;
import defpackage.g7;
import defpackage.hk;
import defpackage.m;
import defpackage.n20;
import defpackage.oy;
import defpackage.rp0;
import defpackage.za0;
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
public final class ListBuilder<E> extends m implements List<E>, RandomAccess, Serializable {
    public static final ListBuilder e;
    public Object[] b;
    public int c;
    public boolean d;

    public static final class BuilderSubList<E> extends m implements List<E>, RandomAccess, Serializable {
        public Object[] b;
        public final int c;
        public int d;
        public final BuilderSubList e;
        public final ListBuilder f;

        public BuilderSubList(E[] eArr, int i, int i2, BuilderSubList<E> builderSubList, ListBuilder<E> listBuilder) {
            oy.j(eArr, "backing");
            oy.j(listBuilder, "root");
            this.b = eArr;
            this.c = i;
            this.d = i2;
            this.e = builderSubList;
            this.f = listBuilder;
            ((AbstractList) this).modCount = ((AbstractList) listBuilder).modCount;
        }

        private final Object writeReplace() throws NotSerializableException {
            if (this.f.d) {
                return new SerializedCollection(this, 0);
            }
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
        }

        public final void a(int i, Collection collection, int i2) {
            ((AbstractList) this).modCount++;
            ListBuilder listBuilder = this.f;
            BuilderSubList builderSubList = this.e;
            if (builderSubList != null) {
                builderSubList.a(i, collection, i2);
            } else {
                listBuilder.a(i, collection, i2);
            }
            this.b = listBuilder.b;
            this.d += i2;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E e) {
            d();
            c();
            b(this.c + this.d, e);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> collection) {
            oy.j(collection, "elements");
            d();
            c();
            int size = collection.size();
            a(this.c + this.d, collection, size);
            return size > 0;
        }

        public final void b(int i, Object obj) {
            ((AbstractList) this).modCount++;
            ListBuilder listBuilder = this.f;
            BuilderSubList builderSubList = this.e;
            if (builderSubList != null) {
                builderSubList.b(i, obj);
            } else {
                ListBuilder.access$addAtInternal(listBuilder, i, obj);
            }
            this.b = listBuilder.b;
            this.d++;
        }

        public final void c() {
            if (((AbstractList) this.f).modCount != ((AbstractList) this).modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            d();
            c();
            f(this.c, this.d);
        }

        public final void d() {
            if (this.f.d) {
                throw new UnsupportedOperationException();
            }
        }

        public final Object e(int i) {
            ((AbstractList) this).modCount++;
            BuilderSubList builderSubList = this.e;
            this.d--;
            return builderSubList != null ? builderSubList.e(i) : this.f.d(i);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            c();
            if (obj == this) {
                return true;
            }
            if (obj instanceof List) {
                return rp0.a(this.b, this.c, this.d, (List) obj);
            }
            return false;
        }

        public final void f(int i, int i2) {
            if (i2 > 0) {
                ((AbstractList) this).modCount++;
            }
            BuilderSubList builderSubList = this.e;
            if (builderSubList != null) {
                builderSubList.f(i, i2);
            } else {
                this.f.e(i, i2);
            }
            this.d -= i2;
        }

        public final int g(int i, int i2, Collection collection, boolean z) {
            BuilderSubList builderSubList = this.e;
            int iG = builderSubList != null ? builderSubList.g(i, i2, collection, z) : this.f.f(i, i2, collection, z);
            if (iG > 0) {
                ((AbstractList) this).modCount++;
            }
            this.d -= iG;
            return iG;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            c();
            int i2 = this.d;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
            }
            return (E) this.b[this.c + i];
        }

        @Override // defpackage.m
        public int getSize() {
            c();
            return this.d;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            c();
            Object[] objArr = this.b;
            int i = this.d;
            int iHashCode = 1;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[this.c + i2];
                iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            c();
            for (int i = 0; i < this.d; i++) {
                if (oy.c(this.b[this.c + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            c();
            return this.d == 0;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            c();
            for (int i = this.d - 1; i >= 0; i--) {
                if (oy.c(this.b[this.c + i], obj)) {
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
            oy.j(collection, "elements");
            d();
            c();
            return g(this.c, this.d, collection, false) > 0;
        }

        @Override // defpackage.m
        public E removeAt(int i) {
            d();
            c();
            int i2 = this.d;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
            }
            return (E) e(this.c + i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<? extends Object> collection) {
            oy.j(collection, "elements");
            d();
            c();
            return g(this.c, this.d, collection, true) > 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public E set(int i, E e) {
            d();
            c();
            int i2 = this.d;
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
            }
            Object[] objArr = this.b;
            int i3 = this.c;
            E e2 = (E) objArr[i3 + i];
            objArr[i3 + i] = e;
            return e2;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<E> subList(int i, int i2) {
            za0.h(i, i2, this.d);
            return new BuilderSubList(this.b, this.c + i, i2 - i, this, this.f);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            oy.j(tArr, "array");
            c();
            int length = tArr.length;
            int i = this.d;
            int i2 = this.c;
            if (length < i) {
                T[] tArr2 = (T[]) Arrays.copyOfRange(this.b, i2, i + i2, tArr.getClass());
                oy.i(tArr2, "copyOfRange(...)");
                return tArr2;
            }
            g7.Y(this.b, tArr, 0, i2, i + i2);
            int i3 = this.d;
            if (i3 < tArr.length) {
                tArr[i3] = null;
            }
            return tArr;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            c();
            return rp0.b(this.b, this.c, this.d, this);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int i) {
            c();
            int i2 = this.d;
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
            }
            return new a(this, i);
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, E e) {
            d();
            c();
            int i2 = this.d;
            if (i >= 0 && i <= i2) {
                b(this.c + i, e);
                return;
            }
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            oy.j(collection, "elements");
            d();
            c();
            int i2 = this.d;
            if (i >= 0 && i <= i2) {
                int size = collection.size();
                a(this.c + i, collection, size);
                return size > 0;
            }
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            c();
            Object[] objArr = this.b;
            int i = this.d;
            int i2 = this.c;
            return g7.a0(objArr, i2, i + i2);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.d = true;
        e = listBuilder;
    }

    public ListBuilder() {
        this(0, 1, null);
    }

    public static final void access$addAtInternal(ListBuilder listBuilder, int i, Object obj) {
        ((AbstractList) listBuilder).modCount++;
        listBuilder.c(i, 1);
        listBuilder.b[i] = obj;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.d) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    public final void a(int i, Collection collection, int i2) {
        ((AbstractList) this).modCount++;
        c(i, i2);
        Iterator<E> it = collection.iterator();
        for (int i3 = 0; i3 < i2; i3++) {
            this.b[i + i3] = it.next();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        b();
        int i = this.c;
        ((AbstractList) this).modCount++;
        c(i, 1);
        this.b[i] = e2;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        oy.j(collection, "elements");
        b();
        int size = collection.size();
        a(this.c, collection, size);
        return size > 0;
    }

    public final void b() {
        if (this.d) {
            throw new UnsupportedOperationException();
        }
    }

    public final List<E> build() {
        b();
        this.d = true;
        return this.c > 0 ? this : e;
    }

    public final void c(int i, int i2) {
        int i3 = this.c + i2;
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.b;
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
            oy.i(objArrCopyOf, "copyOf(...)");
            this.b = objArrCopyOf;
        }
        Object[] objArr2 = this.b;
        g7.Y(objArr2, objArr2, i + i2, i, this.c);
        this.c += i2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        b();
        e(0, this.c);
    }

    public final Object d(int i) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.b;
        Object obj = objArr[i];
        g7.Y(objArr, objArr, i, i + 1, this.c);
        Object[] objArr2 = this.b;
        int i2 = this.c - 1;
        oy.j(objArr2, "<this>");
        objArr2[i2] = null;
        this.c--;
        return obj;
    }

    public final void e(int i, int i2) {
        if (i2 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.b;
        g7.Y(objArr, objArr, i, i + i2, this.c);
        Object[] objArr2 = this.b;
        int i3 = this.c;
        rp0.S(objArr2, i3 - i2, i3);
        this.c -= i2;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (rp0.a(this.b, 0, this.c, (List) obj)) {
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
            if (collection.contains(this.b[i5]) == z) {
                Object[] objArr = this.b;
                i3++;
                objArr[i4 + i] = objArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        Object[] objArr2 = this.b;
        g7.Y(objArr2, objArr2, i + i4, i2 + i, this.c);
        Object[] objArr3 = this.b;
        int i7 = this.c;
        rp0.S(objArr3, i7 - i6, i7);
        if (i6 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.c -= i6;
        return i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }
        return (E) this.b[i];
    }

    @Override // defpackage.m
    public int getSize() {
        return this.c;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        Object[] objArr = this.b;
        int i = this.c;
        int iHashCode = 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        for (int i = 0; i < this.c; i++) {
            if (oy.c(this.b[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        for (int i = this.c - 1; i >= 0; i--) {
            if (oy.c(this.b[i], obj)) {
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
        oy.j(collection, "elements");
        b();
        return f(0, this.c, collection, false) > 0;
    }

    @Override // defpackage.m
    public E removeAt(int i) {
        b();
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }
        return (E) d(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> collection) {
        oy.j(collection, "elements");
        b();
        return f(0, this.c, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e2) {
        b();
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }
        Object[] objArr = this.b;
        E e3 = (E) objArr[i];
        objArr[i] = e2;
        return e3;
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int i, int i2) {
        za0.h(i, i2, this.c);
        return new BuilderSubList(this.b, i, i2 - i, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        oy.j(tArr, "array");
        int length = tArr.length;
        int i = this.c;
        if (length < i) {
            T[] tArr2 = (T[]) Arrays.copyOfRange(this.b, 0, i, tArr.getClass());
            oy.i(tArr2, "copyOfRange(...)");
            return tArr2;
        }
        g7.Y(this.b, tArr, 0, 0, i);
        int i2 = this.c;
        if (i2 < tArr.length) {
            tArr[i2] = null;
        }
        return tArr;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return rp0.b(this.b, 0, this.c, this);
    }

    public /* synthetic */ ListBuilder(int i, int i2, aj ajVar) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int i) {
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
        }
        return new n20(this, i);
    }

    public ListBuilder(int i) {
        if (i >= 0) {
            this.b = new Object[i];
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        oy.j(collection, "elements");
        b();
        int i2 = this.c;
        if (i >= 0 && i <= i2) {
            int size = collection.size();
            a(i, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e2) {
        b();
        int i2 = this.c;
        if (i >= 0 && i <= i2) {
            ((AbstractList) this).modCount++;
            c(i, 1);
            this.b[i] = e2;
            return;
        }
        throw new IndexOutOfBoundsException(hk.f(i, i2, "index: ", ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return g7.a0(this.b, 0, this.c);
    }
}
