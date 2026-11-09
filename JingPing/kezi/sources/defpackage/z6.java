package defpackage;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class z6 extends m {
    public static final Object[] e = new Object[0];
    public int b;
    public Object[] c = e;
    public int d;

    public final void a(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.c.length;
        while (i < length && it.hasNext()) {
            this.c[i] = it.next();
            i++;
        }
        int i2 = this.b;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.c[i3] = it.next();
        }
        this.d = collection.size() + size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        oy.j(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        g();
        b(collection.size() + size());
        a(f(size() + this.b), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        g();
        b(size() + 1);
        int length = this.b;
        if (length == 0) {
            Object[] objArr = this.c;
            oy.j(objArr, "<this>");
            length = objArr.length;
        }
        int i = length - 1;
        this.b = i;
        this.c[i] = obj;
        this.d = size() + 1;
    }

    public final void addLast(Object obj) {
        g();
        b(size() + 1);
        this.c[f(size() + this.b)] = obj;
        this.d = size() + 1;
    }

    public final void b(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.c;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == e) {
            if (i < 10) {
                i = 10;
            }
            this.c = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        g7.Y(objArr, objArr2, 0, this.b, objArr.length);
        Object[] objArr3 = this.c;
        int length2 = objArr3.length;
        int i3 = this.b;
        g7.Y(objArr3, objArr2, length2 - i3, 0, i3);
        this.b = 0;
        this.c = objArr2;
    }

    public final int c(int i) {
        oy.j(this.c, "<this>");
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            g();
            e(this.b, f(size() + this.b));
        }
        this.b = 0;
        this.d = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i) {
        return i < 0 ? i + this.c.length : i;
    }

    public final void e(int i, int i2) {
        if (i < i2) {
            g7.b0(this.c, null, i, i2);
            return;
        }
        Object[] objArr = this.c;
        Arrays.fill(objArr, i, objArr.length, (Object) null);
        g7.b0(this.c, null, 0, i2);
    }

    public final int f(int i) {
        Object[] objArr = this.c;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final void g() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(hk.f(i, size, "index: ", ", size: "));
        }
        return this.c[f(this.b + i)];
    }

    @Override // defpackage.m
    public final int getSize() {
        return this.d;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iF = f(size() + this.b);
        int length = this.b;
        if (length < iF) {
            while (length < iF) {
                if (oy.c(obj, this.c[length])) {
                    i = this.b;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iF) {
            return -1;
        }
        int length2 = this.c.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iF; i2++) {
                    if (oy.c(obj, this.c[i2])) {
                        length = i2 + this.c.length;
                        i = this.b;
                    }
                }
                return -1;
            }
            if (oy.c(obj, this.c[length])) {
                i = this.b;
                break;
            }
            length++;
        }
        return length - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int iF = f(size() + this.b);
        int i2 = this.b;
        if (i2 < iF) {
            length = iF - 1;
            if (i2 <= length) {
                while (!oy.c(obj, this.c[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.b;
                return length - i;
            }
            return -1;
        }
        if (i2 > iF) {
            int i3 = iF - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.c;
                    oy.j(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.b;
                    if (i4 <= length) {
                        while (!oy.c(obj, this.c[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.b;
                    }
                } else {
                    if (oy.c(obj, this.c[i3])) {
                        length = i3 + this.c.length;
                        i = this.b;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iF;
        oy.j(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.c.length != 0) {
            int iF2 = f(size() + this.b);
            int i = this.b;
            if (i < iF2) {
                iF = i;
                while (i < iF2) {
                    Object obj = this.c[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.c[iF] = obj;
                        iF++;
                    }
                    i++;
                }
                g7.b0(this.c, null, iF, iF2);
            } else {
                int length = this.c.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.c;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.c[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr2 = this.c;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.c[iF] = obj3;
                        iF = c(iF);
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.d = d(iF - this.b);
            }
        }
        return z;
    }

    @Override // defpackage.m
    public final Object removeAt(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(hk.f(i, size, "index: ", ", size: "));
        }
        if (i == size() - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        g();
        int iF = f(this.b + i);
        Object obj = this.c[iF];
        if (i < (size() >> 1)) {
            int i2 = this.b;
            if (iF >= i2) {
                Object[] objArr = this.c;
                g7.Y(objArr, objArr, i2 + 1, i2, iF);
            } else {
                Object[] objArr2 = this.c;
                g7.Y(objArr2, objArr2, 1, 0, iF);
                Object[] objArr3 = this.c;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.b;
                g7.Y(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.c;
            int i4 = this.b;
            objArr4[i4] = null;
            this.b = c(i4);
        } else {
            int iF2 = f((size() - 1) + this.b);
            if (iF <= iF2) {
                Object[] objArr5 = this.c;
                g7.Y(objArr5, objArr5, iF, iF + 1, iF2 + 1);
            } else {
                Object[] objArr6 = this.c;
                g7.Y(objArr6, objArr6, iF, iF + 1, objArr6.length);
                Object[] objArr7 = this.c;
                objArr7[objArr7.length - 1] = objArr7[0];
                g7.Y(objArr7, objArr7, 0, 1, iF2 + 1);
            }
            this.c[iF2] = null;
        }
        this.d = size() - 1;
        return obj;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        Object[] objArr = this.c;
        int i = this.b;
        Object obj = objArr[i];
        objArr[i] = null;
        this.b = c(i);
        this.d = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        int iF = f(be.W(this) + this.b);
        Object[] objArr = this.c;
        Object obj = objArr[iF];
        objArr[iF] = null;
        this.d = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        za0.h(i, i2, size());
        int i3 = i2 - i;
        if (i3 == 0) {
            return;
        }
        if (i3 == size()) {
            clear();
            return;
        }
        if (i3 == 1) {
            remove(i);
            return;
        }
        g();
        if (i < size() - i2) {
            int iF = f((i - 1) + this.b);
            int iF2 = f((i2 - 1) + this.b);
            while (i > 0) {
                int i4 = iF + 1;
                int iMin = Math.min(i, Math.min(i4, iF2 + 1));
                Object[] objArr = this.c;
                int i5 = iF2 - iMin;
                int i6 = iF - iMin;
                g7.Y(objArr, objArr, i5 + 1, i6 + 1, i4);
                iF = d(i6);
                iF2 = d(i5);
                i -= iMin;
            }
            int iF3 = f(this.b + i3);
            e(this.b, iF3);
            this.b = iF3;
        } else {
            int iF4 = f(this.b + i2);
            int iF5 = f(this.b + i);
            int size = size();
            while (true) {
                size -= i2;
                if (size <= 0) {
                    break;
                }
                Object[] objArr2 = this.c;
                i2 = Math.min(size, Math.min(objArr2.length - iF4, objArr2.length - iF5));
                Object[] objArr3 = this.c;
                int i7 = iF4 + i2;
                g7.Y(objArr3, objArr3, iF5, iF4, i7);
                iF4 = f(i7);
                iF5 = f(iF5 + i2);
            }
            int iF6 = f(size() + this.b);
            e(d(iF6 - i3), iF6);
        }
        this.d = size() - i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iF;
        oy.j(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.c.length != 0) {
            int iF2 = f(size() + this.b);
            int i = this.b;
            if (i < iF2) {
                iF = i;
                while (i < iF2) {
                    Object obj = this.c[i];
                    if (collection.contains(obj)) {
                        this.c[iF] = obj;
                        iF++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                g7.b0(this.c, null, iF, iF2);
            } else {
                int length = this.c.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.c;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.c[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr2 = this.c;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.c[iF] = obj3;
                        iF = c(iF);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.d = d(iF - this.b);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(hk.f(i, size, "index: ", ", size: "));
        }
        int iF = f(this.b + i);
        Object[] objArr = this.c;
        Object obj2 = objArr[iF];
        objArr[iF] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        oy.j(objArr, "array");
        if (objArr.length < size()) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size());
            oy.h(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iF = f(size() + this.b);
        int i = this.b;
        if (i < iF) {
            g7.Y(this.c, objArr, 0, i, iF);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.c;
            g7.Y(objArr2, objArr, 0, this.b, objArr2.length);
            Object[] objArr3 = this.c;
            g7.Y(objArr3, objArr, objArr3.length - this.b, 0, iF);
        }
        int size = size();
        if (size < objArr.length) {
            objArr[size] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int length;
        int size = size();
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException(hk.f(i, size, "index: ", ", size: "));
        }
        if (i == size()) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            addFirst(obj);
            return;
        }
        g();
        b(size() + 1);
        int iF = f(this.b + i);
        if (i < ((size() + 1) >> 1)) {
            if (iF == 0) {
                Object[] objArr = this.c;
                oy.j(objArr, "<this>");
                iF = objArr.length;
            }
            int i2 = iF - 1;
            int i3 = this.b;
            if (i3 == 0) {
                Object[] objArr2 = this.c;
                oy.j(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = i3 - 1;
            }
            int i4 = this.b;
            if (i2 >= i4) {
                Object[] objArr3 = this.c;
                objArr3[length] = objArr3[i4];
                g7.Y(objArr3, objArr3, i4, i4 + 1, i2 + 1);
            } else {
                Object[] objArr4 = this.c;
                g7.Y(objArr4, objArr4, i4 - 1, i4, objArr4.length);
                Object[] objArr5 = this.c;
                objArr5[objArr5.length - 1] = objArr5[0];
                g7.Y(objArr5, objArr5, 0, 1, i2 + 1);
            }
            this.c[i2] = obj;
            this.b = length;
        } else {
            int iF2 = f(size() + this.b);
            if (iF < iF2) {
                Object[] objArr6 = this.c;
                g7.Y(objArr6, objArr6, iF + 1, iF, iF2);
            } else {
                Object[] objArr7 = this.c;
                g7.Y(objArr7, objArr7, 1, 0, iF2);
                Object[] objArr8 = this.c;
                objArr8[0] = objArr8[objArr8.length - 1];
                g7.Y(objArr8, objArr8, iF + 1, iF, objArr8.length - 1);
            }
            this.c[iF] = obj;
        }
        this.d = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        oy.j(collection, "elements");
        int size = size();
        if (i >= 0 && i <= size) {
            if (collection.isEmpty()) {
                return false;
            }
            if (i == size()) {
                return addAll(collection);
            }
            g();
            b(collection.size() + size());
            int iF = f(size() + this.b);
            int iF2 = f(this.b + i);
            int size2 = collection.size();
            if (i < ((size() + 1) >> 1)) {
                int i2 = this.b;
                int length = i2 - size2;
                if (iF2 < i2) {
                    Object[] objArr = this.c;
                    g7.Y(objArr, objArr, length, i2, objArr.length);
                    if (size2 >= iF2) {
                        Object[] objArr2 = this.c;
                        g7.Y(objArr2, objArr2, objArr2.length - size2, 0, iF2);
                    } else {
                        Object[] objArr3 = this.c;
                        g7.Y(objArr3, objArr3, objArr3.length - size2, 0, size2);
                        Object[] objArr4 = this.c;
                        g7.Y(objArr4, objArr4, 0, size2, iF2);
                    }
                } else if (length >= 0) {
                    Object[] objArr5 = this.c;
                    g7.Y(objArr5, objArr5, length, i2, iF2);
                } else {
                    Object[] objArr6 = this.c;
                    length += objArr6.length;
                    int i3 = iF2 - i2;
                    int length2 = objArr6.length - length;
                    if (length2 >= i3) {
                        g7.Y(objArr6, objArr6, length, i2, iF2);
                    } else {
                        g7.Y(objArr6, objArr6, length, i2, i2 + length2);
                        Object[] objArr7 = this.c;
                        g7.Y(objArr7, objArr7, 0, this.b + length2, iF2);
                    }
                }
                this.b = length;
                a(d(iF2 - size2), collection);
                return true;
            }
            int i4 = iF2 + size2;
            if (iF2 < iF) {
                int i5 = size2 + iF;
                Object[] objArr8 = this.c;
                if (i5 <= objArr8.length) {
                    g7.Y(objArr8, objArr8, i4, iF2, iF);
                } else if (i4 >= objArr8.length) {
                    g7.Y(objArr8, objArr8, i4 - objArr8.length, iF2, iF);
                } else {
                    int length3 = iF - (i5 - objArr8.length);
                    g7.Y(objArr8, objArr8, 0, length3, iF);
                    Object[] objArr9 = this.c;
                    g7.Y(objArr9, objArr9, i4, iF2, length3);
                }
            } else {
                Object[] objArr10 = this.c;
                g7.Y(objArr10, objArr10, size2, 0, iF);
                Object[] objArr11 = this.c;
                if (i4 >= objArr11.length) {
                    g7.Y(objArr11, objArr11, i4 - objArr11.length, iF2, objArr11.length);
                } else {
                    g7.Y(objArr11, objArr11, 0, objArr11.length - size2, objArr11.length);
                    Object[] objArr12 = this.c;
                    g7.Y(objArr12, objArr12, i4, iF2, objArr12.length - size2);
                }
            }
            a(iF2, collection);
            return true;
        }
        throw new IndexOutOfBoundsException(hk.f(i, size, "index: ", ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
