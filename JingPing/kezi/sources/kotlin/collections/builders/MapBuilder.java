package kotlin.collections.builders;

import defpackage.c40;
import defpackage.d40;
import defpackage.di0;
import defpackage.g40;
import defpackage.h40;
import defpackage.i40;
import defpackage.j40;
import defpackage.oy;
import defpackage.rp0;
import defpackage.wz;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, wz {
    public static final c40 Companion = new c40();
    public static final MapBuilder o;
    public Object[] b;
    public Object[] c;
    public int[] d;
    public int[] e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public i40 k;
    public j40 l;
    public i40 m;
    public boolean n;

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.n = true;
        o = mapBuilder;
    }

    public MapBuilder() {
        this(8);
    }

    public static final Object[] access$allocateValuesArray(MapBuilder mapBuilder) {
        Object[] objArr = mapBuilder.c;
        if (objArr != null) {
            return objArr;
        }
        int capacity$kotlin_stdlib = mapBuilder.getCapacity$kotlin_stdlib();
        if (capacity$kotlin_stdlib < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr2 = new Object[capacity$kotlin_stdlib];
        mapBuilder.c = objArr2;
        return objArr2;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (!this.n) {
            throw new NotSerializableException("The map cannot be serialized while it is being built.");
        }
        di0 di0Var = new di0();
        di0Var.b = this;
        return di0Var;
    }

    public final void a(boolean z) {
        int i;
        Object[] objArr = this.c;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.g;
            if (i2 >= i) {
                break;
            }
            int[] iArr = this.d;
            int i4 = iArr[i2];
            if (i4 >= 0) {
                Object[] objArr2 = this.b;
                objArr2[i3] = objArr2[i2];
                if (objArr != null) {
                    objArr[i3] = objArr[i2];
                }
                if (z) {
                    iArr[i3] = i4;
                    this.e[i4] = i3 + 1;
                }
                i3++;
            }
            i2++;
        }
        rp0.S(this.b, i3, i);
        if (objArr != null) {
            rp0.S(objArr, i3, this.g);
        }
        this.g = i3;
    }

    public final int addKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int iE = e(k);
            int i = this.f * 2;
            int length = this.e.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int i3 = this.e[iE];
                if (i3 <= 0) {
                    if (this.g < getCapacity$kotlin_stdlib()) {
                        int i4 = this.g;
                        int i5 = i4 + 1;
                        this.g = i5;
                        this.b[i4] = k;
                        this.d[i4] = iE;
                        this.e[iE] = i5;
                        this.j = size() + 1;
                        this.i++;
                        if (i2 > this.f) {
                            this.f = i2;
                        }
                        return i4;
                    }
                    b(1);
                } else {
                    if (oy.c(this.b[i3 - 1], k)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        f(this.e.length * 2);
                        break;
                    }
                    iE = iE == 0 ? this.e.length - 1 : iE - 1;
                }
            }
        }
    }

    public final void b(int i) {
        Object[] objArrCopyOf;
        int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
        int i2 = this.g;
        int i3 = capacity$kotlin_stdlib - i2;
        int size = i2 - size();
        if (i3 < i && i3 + size >= i && size >= getCapacity$kotlin_stdlib() / 4) {
            a(true);
            return;
        }
        int i4 = this.g + i;
        if (i4 < 0) {
            throw new OutOfMemoryError();
        }
        if (i4 > getCapacity$kotlin_stdlib()) {
            int capacity$kotlin_stdlib2 = getCapacity$kotlin_stdlib();
            int i5 = capacity$kotlin_stdlib2 + (capacity$kotlin_stdlib2 >> 1);
            if (i5 - i4 < 0) {
                i5 = i4;
            }
            if (i5 - 2147483639 > 0) {
                i5 = i4 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArr = this.b;
            oy.j(objArr, "<this>");
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i5);
            oy.i(objArrCopyOf2, "copyOf(...)");
            this.b = objArrCopyOf2;
            Object[] objArr2 = this.c;
            if (objArr2 != null) {
                objArrCopyOf = Arrays.copyOf(objArr2, i5);
                oy.i(objArrCopyOf, "copyOf(...)");
            } else {
                objArrCopyOf = null;
            }
            this.c = objArrCopyOf;
            int[] iArrCopyOf = Arrays.copyOf(this.d, i5);
            oy.i(iArrCopyOf, "copyOf(...)");
            this.d = iArrCopyOf;
            Companion.getClass();
            int iHighestOneBit = Integer.highestOneBit((i5 >= 1 ? i5 : 1) * 3);
            if (iHighestOneBit > this.e.length) {
                f(iHighestOneBit);
            }
        }
    }

    public final Map<K, V> build() {
        checkIsMutable$kotlin_stdlib();
        this.n = true;
        if (size() > 0) {
            return this;
        }
        MapBuilder mapBuilder = o;
        oy.h(mapBuilder, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return mapBuilder;
    }

    public final int c(Object obj) {
        int iE = e(obj);
        int i = this.f;
        while (true) {
            int i2 = this.e[iE];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (oy.c(this.b[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iE = iE == 0 ? this.e.length - 1 : iE - 1;
        }
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.n) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public void clear() {
        checkIsMutable$kotlin_stdlib();
        int i = this.g - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.d;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    this.e[i3] = 0;
                    iArr[i2] = -1;
                }
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        rp0.S(this.b, 0, this.g);
        Object[] objArr = this.c;
        if (objArr != null) {
            rp0.S(objArr, 0, this.g);
        }
        this.j = 0;
        this.g = 0;
        this.i++;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection<?> collection) {
        oy.j(collection, "m");
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> entry) {
        oy.j(entry, "entry");
        int iC = c(entry.getKey());
        if (iC < 0) {
            return false;
        }
        Object[] objArr = this.c;
        oy.g(objArr);
        return oy.c(objArr[iC], entry.getValue());
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return c(obj) >= 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return d(obj) >= 0;
    }

    public final int d(Object obj) {
        int i = this.g;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.d[i] >= 0) {
                Object[] objArr = this.c;
                oy.g(objArr);
                if (oy.c(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    public final int e(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.h;
    }

    public final d40 entriesIterator$kotlin_stdlib() {
        return new d40(this);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        return size() == map.size() && containsAllEntries$kotlin_stdlib(map.entrySet());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        r3[r0] = r6;
        r5.d[r2] = r0;
        r2 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r6) {
        /*
            r5 = this;
            int r0 = r5.i
            int r0 = r0 + 1
            r5.i = r0
            int r0 = r5.g
            int r1 = r5.size()
            r2 = 0
            if (r0 <= r1) goto L12
            r5.a(r2)
        L12:
            int[] r0 = new int[r6]
            r5.e = r0
            c40 r0 = kotlin.collections.builders.MapBuilder.Companion
            r0.getClass()
            int r6 = java.lang.Integer.numberOfLeadingZeros(r6)
            int r6 = r6 + 1
            r5.h = r6
        L23:
            int r6 = r5.g
            if (r2 >= r6) goto L57
            int r6 = r2 + 1
            java.lang.Object[] r0 = r5.b
            r0 = r0[r2]
            int r0 = r5.e(r0)
            int r1 = r5.f
        L33:
            int[] r3 = r5.e
            r4 = r3[r0]
            if (r4 != 0) goto L41
            r3[r0] = r6
            int[] r1 = r5.d
            r1[r2] = r0
            r2 = r6
            goto L23
        L41:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L4f
            int r4 = r0 + (-1)
            if (r0 != 0) goto L4d
            int r0 = r3.length
            int r0 = r0 + (-1)
            goto L33
        L4d:
            r0 = r4
            goto L33
        L4f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r6.<init>(r0)
            throw r6
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.f(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[LOOP:0: B:9:0x0024->B:33:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.b
            java.lang.String r1 = "<this>"
            defpackage.oy.j(r0, r1)
            r1 = 0
            r0[r12] = r1
            java.lang.Object[] r0 = r11.c
            if (r0 == 0) goto L10
            r0[r12] = r1
        L10:
            int[] r0 = r11.d
            r0 = r0[r12]
            int r1 = r11.f
            int r1 = r1 * 2
            int[] r2 = r11.e
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L20
            r1 = r2
        L20:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L24:
            int r5 = r0 + (-1)
            if (r0 != 0) goto L2e
            int[] r0 = r11.e
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L2f
        L2e:
            r0 = r5
        L2f:
            int r4 = r4 + 1
            int r5 = r11.f
            r6 = -1
            if (r4 <= r5) goto L3b
            int[] r0 = r11.e
            r0[r1] = r2
            goto L6c
        L3b:
            int[] r5 = r11.e
            r7 = r5[r0]
            if (r7 != 0) goto L44
            r5[r1] = r2
            goto L6c
        L44:
            if (r7 >= 0) goto L4b
            r5[r1] = r6
        L48:
            r1 = r0
            r4 = r2
            goto L65
        L4b:
            java.lang.Object[] r5 = r11.b
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.e(r5)
            int r5 = r5 - r0
            int[] r9 = r11.e
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L65
            r9[r1] = r7
            int[] r4 = r11.d
            r4[r8] = r1
            goto L48
        L65:
            int r3 = r3 + r6
            if (r3 >= 0) goto L24
            int[] r0 = r11.e
            r0[r1] = r6
        L6c:
            int[] r0 = r11.d
            r0[r12] = r6
            int r12 = r11.size()
            int r12 = r12 + r6
            r11.j = r12
            int r12 = r11.i
            int r12 = r12 + 1
            r11.i = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.g(int):void");
    }

    @Override // java.util.Map
    public V get(Object obj) {
        int iC = c(obj);
        if (iC < 0) {
            return null;
        }
        Object[] objArr = this.c;
        oy.g(objArr);
        return (V) objArr[iC];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.b.length;
    }

    public Set<Map.Entry<K, V>> getEntries() {
        i40 i40Var = this.m;
        if (i40Var != null) {
            return i40Var;
        }
        i40 i40Var2 = new i40(this, 0);
        this.m = i40Var2;
        return i40Var2;
    }

    public Set<K> getKeys() {
        i40 i40Var = this.k;
        if (i40Var != null) {
            return i40Var;
        }
        i40 i40Var2 = new i40(this, 1);
        this.k = i40Var2;
        return i40Var2;
    }

    public int getSize() {
        return this.j;
    }

    public Collection<V> getValues() {
        j40 j40Var = this.l;
        if (j40Var != null) {
            return j40Var;
        }
        j40 j40Var2 = new j40(this);
        this.l = j40Var2;
        return j40Var2;
    }

    @Override // java.util.Map
    public int hashCode() {
        d40 d40VarEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (d40VarEntriesIterator$kotlin_stdlib.hasNext()) {
            int i2 = d40VarEntriesIterator$kotlin_stdlib.b;
            MapBuilder mapBuilder = (MapBuilder) d40VarEntriesIterator$kotlin_stdlib.e;
            if (i2 >= mapBuilder.g) {
                throw new NoSuchElementException();
            }
            int i3 = d40VarEntriesIterator$kotlin_stdlib.b;
            d40VarEntriesIterator$kotlin_stdlib.b = i3 + 1;
            d40VarEntriesIterator$kotlin_stdlib.c = i3;
            Object obj = mapBuilder.b[d40VarEntriesIterator$kotlin_stdlib.c];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = mapBuilder.c;
            oy.g(objArr);
            Object obj2 = objArr[d40VarEntriesIterator$kotlin_stdlib.c];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            d40VarEntriesIterator$kotlin_stdlib.e();
            i += iHashCode ^ iHashCode2;
        }
        return i;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.n;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    public final g40 keysIterator$kotlin_stdlib() {
        return new g40(this);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        checkIsMutable$kotlin_stdlib();
        int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(k);
        Object[] objArr = this.c;
        if (objArr == null) {
            int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
            if (capacity$kotlin_stdlib < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[capacity$kotlin_stdlib];
            this.c = objArr;
        }
        if (iAddKey$kotlin_stdlib >= 0) {
            objArr[iAddKey$kotlin_stdlib] = v;
            return null;
        }
        int i = (-iAddKey$kotlin_stdlib) - 1;
        V v2 = (V) objArr[i];
        objArr[i] = v;
        return v2;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        oy.j(map, "from");
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry<? extends K, ? extends V>> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        b(setEntrySet.size());
        for (Map.Entry<? extends K, ? extends V> entry : setEntrySet) {
            int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
            Object[] objArr = this.c;
            if (objArr == null) {
                int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
                if (capacity$kotlin_stdlib < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[capacity$kotlin_stdlib];
                this.c = objArr;
            }
            if (iAddKey$kotlin_stdlib >= 0) {
                objArr[iAddKey$kotlin_stdlib] = entry.getValue();
            } else {
                int i = (-iAddKey$kotlin_stdlib) - 1;
                if (!oy.c(entry.getValue(), objArr[i])) {
                    objArr[i] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int iC = c(obj);
        if (iC < 0) {
            return null;
        }
        Object[] objArr = this.c;
        oy.g(objArr);
        V v = (V) objArr[iC];
        g(iC);
        return v;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> entry) {
        oy.j(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int iC = c(entry.getKey());
        if (iC < 0) {
            return false;
        }
        Object[] objArr = this.c;
        oy.g(objArr);
        if (!oy.c(objArr[iC], entry.getValue())) {
            return false;
        }
        g(iC);
        return true;
    }

    public final boolean removeKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        int iC = c(k);
        if (iC < 0) {
            return false;
        }
        g(iC);
        return true;
    }

    public final boolean removeValue$kotlin_stdlib(V v) {
        checkIsMutable$kotlin_stdlib();
        int iD = d(v);
        if (iD < 0) {
            return false;
        }
        g(iD);
        return true;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        d40 d40VarEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (d40VarEntriesIterator$kotlin_stdlib.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = d40VarEntriesIterator$kotlin_stdlib.b;
            MapBuilder mapBuilder = (MapBuilder) d40VarEntriesIterator$kotlin_stdlib.e;
            if (i2 >= mapBuilder.g) {
                throw new NoSuchElementException();
            }
            int i3 = d40VarEntriesIterator$kotlin_stdlib.b;
            d40VarEntriesIterator$kotlin_stdlib.b = i3 + 1;
            d40VarEntriesIterator$kotlin_stdlib.c = i3;
            Object obj = mapBuilder.b[d40VarEntriesIterator$kotlin_stdlib.c];
            if (obj == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = mapBuilder.c;
            oy.g(objArr);
            Object obj2 = objArr[d40VarEntriesIterator$kotlin_stdlib.c];
            if (obj2 == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            d40VarEntriesIterator$kotlin_stdlib.e();
            i++;
        }
        sb.append("}");
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public final h40 valuesIterator$kotlin_stdlib() {
        return new h40(this);
    }

    public MapBuilder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i];
        int[] iArr = new int[i];
        Companion.getClass();
        int iHighestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.b = objArr;
        this.c = null;
        this.d = iArr;
        this.e = new int[iHighestOneBit];
        this.f = 2;
        this.g = 0;
        this.h = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
