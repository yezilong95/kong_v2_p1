package defpackage;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h8 extends w0 {
    public static final Object[] H = new Object[0];
    public int E;
    public Object[] F = H;
    public int G;

    public final void a(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.F.length;
        while (i < length && it.hasNext()) {
            this.F[i] = it.next();
            i++;
        }
        int i2 = this.E;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.F[i3] = it.next();
        }
        this.G = collection.size() + size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        t40.n(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        g();
        b(collection.size() + size());
        a(f(size() + this.E), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        g();
        b(size() + 1);
        int length = this.E;
        if (length == 0) {
            Object[] objArr = this.F;
            t40.n(objArr, "<this>");
            length = objArr.length;
        }
        int i = length - 1;
        this.E = i;
        this.F[i] = obj;
        this.G = size() + 1;
    }

    public final void addLast(Object obj) {
        g();
        b(size() + 1);
        this.F[f(size() + this.E)] = obj;
        this.G = size() + 1;
    }

    public final void b(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.F;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == H) {
            if (i < 10) {
                i = 10;
            }
            this.F = new Object[i];
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
        q8.O0(objArr, objArr2, 0, this.E, objArr.length);
        Object[] objArr3 = this.F;
        int length2 = objArr3.length;
        int i3 = this.E;
        q8.O0(objArr3, objArr2, length2 - i3, 0, i3);
        this.E = 0;
        this.F = objArr2;
    }

    public final int c(int i) {
        t40.n(this.F, "<this>");
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            g();
            e(this.E, f(size() + this.E));
        }
        this.E = 0;
        this.G = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i) {
        return i < 0 ? i + this.F.length : i;
    }

    public final void e(int i, int i2) {
        if (i < i2) {
            q8.Q0(this.F, null, i, i2);
            return;
        }
        Object[] objArr = this.F;
        Arrays.fill(objArr, i, objArr.length, (Object) null);
        q8.Q0(this.F, null, 0, i2);
    }

    public final int f(int i) {
        Object[] objArr = this.F;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final void g() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, size, ", size: "));
        }
        return this.F[f(this.E + i)];
    }

    @Override // defpackage.w0
    public final int getSize() {
        return this.G;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iF = f(size() + this.E);
        int length = this.E;
        if (length < iF) {
            while (length < iF) {
                if (t40.f(obj, this.F[length])) {
                    i = this.E;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iF) {
            return -1;
        }
        int length2 = this.F.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iF; i2++) {
                    if (t40.f(obj, this.F[i2])) {
                        length = i2 + this.F.length;
                        i = this.E;
                    }
                }
                return -1;
            }
            if (t40.f(obj, this.F[length])) {
                i = this.E;
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
        int iF = f(size() + this.E);
        int i2 = this.E;
        if (i2 < iF) {
            length = iF - 1;
            if (i2 <= length) {
                while (!t40.f(obj, this.F[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.E;
                return length - i;
            }
            return -1;
        }
        if (i2 > iF) {
            int i3 = iF - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.F;
                    t40.n(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.E;
                    if (i4 <= length) {
                        while (!t40.f(obj, this.F[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.E;
                    }
                } else {
                    if (t40.f(obj, this.F[i3])) {
                        length = i3 + this.F.length;
                        i = this.E;
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
        t40.n(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.F.length != 0) {
            int iF2 = f(size() + this.E);
            int i = this.E;
            if (i < iF2) {
                iF = i;
                while (i < iF2) {
                    Object obj = this.F[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.F[iF] = obj;
                        iF++;
                    }
                    i++;
                }
                q8.Q0(this.F, null, iF, iF2);
            } else {
                int length = this.F.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.F;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.F[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr2 = this.F;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.F[iF] = obj3;
                        iF = c(iF);
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.G = d(iF - this.E);
            }
        }
        return z;
    }

    @Override // defpackage.w0
    public final Object removeAt(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, size, ", size: "));
        }
        if (i == size() - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        g();
        int iF = f(this.E + i);
        Object obj = this.F[iF];
        if (i < (size() >> 1)) {
            int i2 = this.E;
            if (iF >= i2) {
                Object[] objArr = this.F;
                q8.O0(objArr, objArr, i2 + 1, i2, iF);
            } else {
                Object[] objArr2 = this.F;
                q8.O0(objArr2, objArr2, 1, 0, iF);
                Object[] objArr3 = this.F;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.E;
                q8.O0(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.F;
            int i4 = this.E;
            objArr4[i4] = null;
            this.E = c(i4);
        } else {
            int iF2 = f((size() - 1) + this.E);
            if (iF <= iF2) {
                Object[] objArr5 = this.F;
                q8.O0(objArr5, objArr5, iF, iF + 1, iF2 + 1);
            } else {
                Object[] objArr6 = this.F;
                q8.O0(objArr6, objArr6, iF, iF + 1, objArr6.length);
                Object[] objArr7 = this.F;
                objArr7[objArr7.length - 1] = objArr7[0];
                q8.O0(objArr7, objArr7, 0, 1, iF2 + 1);
            }
            this.F[iF2] = null;
        }
        this.G = size() - 1;
        return obj;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        Object[] objArr = this.F;
        int i = this.E;
        Object obj = objArr[i];
        objArr[i] = null;
        this.E = c(i);
        this.G = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        g();
        int iF = f((size() - 1) + this.E);
        Object[] objArr = this.F;
        Object obj = objArr[iF];
        objArr[iF] = null;
        this.G = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        j31.k(i, i2, size());
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
            int iF = f((i - 1) + this.E);
            int iF2 = f((i2 - 1) + this.E);
            while (i > 0) {
                int i4 = iF + 1;
                int iMin = Math.min(i, Math.min(i4, iF2 + 1));
                Object[] objArr = this.F;
                int i5 = iF2 - iMin;
                int i6 = iF - iMin;
                q8.O0(objArr, objArr, i5 + 1, i6 + 1, i4);
                iF = d(i6);
                iF2 = d(i5);
                i -= iMin;
            }
            int iF3 = f(this.E + i3);
            e(this.E, iF3);
            this.E = iF3;
        } else {
            int iF4 = f(this.E + i2);
            int iF5 = f(this.E + i);
            int size = size();
            while (true) {
                size -= i2;
                if (size <= 0) {
                    break;
                }
                Object[] objArr2 = this.F;
                i2 = Math.min(size, Math.min(objArr2.length - iF4, objArr2.length - iF5));
                Object[] objArr3 = this.F;
                int i7 = iF4 + i2;
                q8.O0(objArr3, objArr3, iF5, iF4, i7);
                iF4 = f(i7);
                iF5 = f(iF5 + i2);
            }
            int iF6 = f(size() + this.E);
            e(d(iF6 - i3), iF6);
        }
        this.G = size() - i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iF;
        t40.n(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.F.length != 0) {
            int iF2 = f(size() + this.E);
            int i = this.E;
            if (i < iF2) {
                iF = i;
                while (i < iF2) {
                    Object obj = this.F[i];
                    if (collection.contains(obj)) {
                        this.F[iF] = obj;
                        iF++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                q8.Q0(this.F, null, iF, iF2);
            } else {
                int length = this.F.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.F;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.F[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iF = f(i2);
                for (int i3 = 0; i3 < iF2; i3++) {
                    Object[] objArr2 = this.F;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.F[iF] = obj3;
                        iF = c(iF);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                g();
                this.G = d(iF - this.E);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, size, ", size: "));
        }
        int iF = f(this.E + i);
        Object[] objArr = this.F;
        Object obj2 = objArr[iF];
        objArr[iF] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        t40.n(objArr, "array");
        if (objArr.length < size()) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size());
            t40.l(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iF = f(size() + this.E);
        int i = this.E;
        if (i < iF) {
            q8.O0(this.F, objArr, 0, i, iF);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.F;
            q8.O0(objArr2, objArr, 0, this.E, objArr2.length);
            Object[] objArr3 = this.F;
            q8.O0(objArr3, objArr, objArr3.length - this.E, 0, iF);
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
            throw new IndexOutOfBoundsException(zn.l("index: ", i, size, ", size: "));
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
        int iF = f(this.E + i);
        if (i < ((size() + 1) >> 1)) {
            if (iF == 0) {
                Object[] objArr = this.F;
                t40.n(objArr, "<this>");
                iF = objArr.length;
            }
            int i2 = iF - 1;
            int i3 = this.E;
            if (i3 == 0) {
                Object[] objArr2 = this.F;
                t40.n(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = i3 - 1;
            }
            int i4 = this.E;
            if (i2 >= i4) {
                Object[] objArr3 = this.F;
                objArr3[length] = objArr3[i4];
                q8.O0(objArr3, objArr3, i4, i4 + 1, i2 + 1);
            } else {
                Object[] objArr4 = this.F;
                q8.O0(objArr4, objArr4, i4 - 1, i4, objArr4.length);
                Object[] objArr5 = this.F;
                objArr5[objArr5.length - 1] = objArr5[0];
                q8.O0(objArr5, objArr5, 0, 1, i2 + 1);
            }
            this.F[i2] = obj;
            this.E = length;
        } else {
            int iF2 = f(size() + this.E);
            if (iF < iF2) {
                Object[] objArr6 = this.F;
                q8.O0(objArr6, objArr6, iF + 1, iF, iF2);
            } else {
                Object[] objArr7 = this.F;
                q8.O0(objArr7, objArr7, 1, 0, iF2);
                Object[] objArr8 = this.F;
                objArr8[0] = objArr8[objArr8.length - 1];
                q8.O0(objArr8, objArr8, iF + 1, iF, objArr8.length - 1);
            }
            this.F[iF] = obj;
        }
        this.G = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        t40.n(collection, "elements");
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
            int iF = f(size() + this.E);
            int iF2 = f(this.E + i);
            int size2 = collection.size();
            if (i < ((size() + 1) >> 1)) {
                int i2 = this.E;
                int length = i2 - size2;
                if (iF2 < i2) {
                    Object[] objArr = this.F;
                    q8.O0(objArr, objArr, length, i2, objArr.length);
                    if (size2 >= iF2) {
                        Object[] objArr2 = this.F;
                        q8.O0(objArr2, objArr2, objArr2.length - size2, 0, iF2);
                    } else {
                        Object[] objArr3 = this.F;
                        q8.O0(objArr3, objArr3, objArr3.length - size2, 0, size2);
                        Object[] objArr4 = this.F;
                        q8.O0(objArr4, objArr4, 0, size2, iF2);
                    }
                } else if (length >= 0) {
                    Object[] objArr5 = this.F;
                    q8.O0(objArr5, objArr5, length, i2, iF2);
                } else {
                    Object[] objArr6 = this.F;
                    length += objArr6.length;
                    int i3 = iF2 - i2;
                    int length2 = objArr6.length - length;
                    if (length2 >= i3) {
                        q8.O0(objArr6, objArr6, length, i2, iF2);
                    } else {
                        q8.O0(objArr6, objArr6, length, i2, i2 + length2);
                        Object[] objArr7 = this.F;
                        q8.O0(objArr7, objArr7, 0, this.E + length2, iF2);
                    }
                }
                this.E = length;
                a(d(iF2 - size2), collection);
                return true;
            }
            int i4 = iF2 + size2;
            if (iF2 < iF) {
                int i5 = size2 + iF;
                Object[] objArr8 = this.F;
                if (i5 <= objArr8.length) {
                    q8.O0(objArr8, objArr8, i4, iF2, iF);
                } else if (i4 >= objArr8.length) {
                    q8.O0(objArr8, objArr8, i4 - objArr8.length, iF2, iF);
                } else {
                    int length3 = iF - (i5 - objArr8.length);
                    q8.O0(objArr8, objArr8, 0, length3, iF);
                    Object[] objArr9 = this.F;
                    q8.O0(objArr9, objArr9, i4, iF2, length3);
                }
            } else {
                Object[] objArr10 = this.F;
                q8.O0(objArr10, objArr10, size2, 0, iF);
                Object[] objArr11 = this.F;
                if (i4 >= objArr11.length) {
                    q8.O0(objArr11, objArr11, i4 - objArr11.length, iF2, objArr11.length);
                } else {
                    q8.O0(objArr11, objArr11, 0, objArr11.length - size2, objArr11.length);
                    Object[] objArr12 = this.F;
                    q8.O0(objArr12, objArr12, i4, iF2, objArr12.length - size2);
                }
            }
            a(iF2, collection);
            return true;
        }
        throw new IndexOutOfBoundsException(zn.l("index: ", i, size, ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
