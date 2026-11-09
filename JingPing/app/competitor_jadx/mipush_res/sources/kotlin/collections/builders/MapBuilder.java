package kotlin.collections.builders;

import defpackage.rb0;
import defpackage.ru0;
import defpackage.s90;
import defpackage.sb0;
import defpackage.t40;
import defpackage.vb0;
import defpackage.wb0;
import defpackage.xb0;
import defpackage.yb0;
import io.nats.client.support.JsonUtils;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable {
    public static final rb0 Companion = new rb0();
    public static final MapBuilder R;
    public Object[] E;
    public Object[] F;
    public int[] G;
    public int[] H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public xb0 N;
    public yb0 O;
    public xb0 P;
    public boolean Q;

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.Q = true;
        R = mapBuilder;
    }

    public MapBuilder() {
        this(8);
    }

    public static final Object[] access$allocateValuesArray(MapBuilder mapBuilder) {
        Object[] objArr = mapBuilder.F;
        if (objArr != null) {
            return objArr;
        }
        int capacity$kotlin_stdlib = mapBuilder.getCapacity$kotlin_stdlib();
        if (capacity$kotlin_stdlib < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr2 = new Object[capacity$kotlin_stdlib];
        mapBuilder.F = objArr2;
        return objArr2;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (!this.Q) {
            throw new NotSerializableException("The map cannot be serialized while it is being built.");
        }
        ru0 ru0Var = new ru0();
        ru0Var.E = this;
        return ru0Var;
    }

    public final void a(boolean z) {
        int i;
        Object[] objArr = this.F;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.J;
            if (i2 >= i) {
                break;
            }
            int[] iArr = this.G;
            int i4 = iArr[i2];
            if (i4 >= 0) {
                Object[] objArr2 = this.E;
                objArr2[i3] = objArr2[i2];
                if (objArr != null) {
                    objArr[i3] = objArr[i2];
                }
                if (z) {
                    iArr[i3] = i4;
                    this.H[i4] = i3 + 1;
                }
                i3++;
            }
            i2++;
        }
        s90.R(this.E, i3, i);
        if (objArr != null) {
            s90.R(objArr, i3, this.J);
        }
        this.J = i3;
    }

    public final int addKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int iE = e(k);
            int i = this.I * 2;
            int length = this.H.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int i3 = this.H[iE];
                if (i3 <= 0) {
                    if (this.J < getCapacity$kotlin_stdlib()) {
                        int i4 = this.J;
                        int i5 = i4 + 1;
                        this.J = i5;
                        this.E[i4] = k;
                        this.G[i4] = iE;
                        this.H[iE] = i5;
                        this.M = size() + 1;
                        this.L++;
                        if (i2 > this.I) {
                            this.I = i2;
                        }
                        return i4;
                    }
                    b(1);
                } else {
                    if (t40.f(this.E[i3 - 1], k)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        f(this.H.length * 2);
                        break;
                    }
                    iE = iE == 0 ? this.H.length - 1 : iE - 1;
                }
            }
        }
    }

    public final void b(int i) {
        Object[] objArrCopyOf;
        int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
        int i2 = this.J;
        int i3 = capacity$kotlin_stdlib - i2;
        int size = i2 - size();
        if (i3 < i && i3 + size >= i && size >= getCapacity$kotlin_stdlib() / 4) {
            a(true);
            return;
        }
        int i4 = this.J + i;
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
            Object[] objArr = this.E;
            t40.n(objArr, "<this>");
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i5);
            t40.m(objArrCopyOf2, "copyOf(...)");
            this.E = objArrCopyOf2;
            Object[] objArr2 = this.F;
            if (objArr2 != null) {
                objArrCopyOf = Arrays.copyOf(objArr2, i5);
                t40.m(objArrCopyOf, "copyOf(...)");
            } else {
                objArrCopyOf = null;
            }
            this.F = objArrCopyOf;
            int[] iArrCopyOf = Arrays.copyOf(this.G, i5);
            t40.m(iArrCopyOf, "copyOf(...)");
            this.G = iArrCopyOf;
            Companion.getClass();
            int iHighestOneBit = Integer.highestOneBit((i5 >= 1 ? i5 : 1) * 3);
            if (iHighestOneBit > this.H.length) {
                f(iHighestOneBit);
            }
        }
    }

    public final Map<K, V> build() {
        checkIsMutable$kotlin_stdlib();
        this.Q = true;
        if (size() > 0) {
            return this;
        }
        MapBuilder mapBuilder = R;
        t40.l(mapBuilder, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return mapBuilder;
    }

    public final int c(Object obj) {
        int iE = e(obj);
        int i = this.I;
        while (true) {
            int i2 = this.H[iE];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (t40.f(this.E[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iE = iE == 0 ? this.H.length - 1 : iE - 1;
        }
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.Q) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public void clear() {
        checkIsMutable$kotlin_stdlib();
        int i = this.J - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.G;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    this.H[i3] = 0;
                    iArr[i2] = -1;
                }
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        s90.R(this.E, 0, this.J);
        Object[] objArr = this.F;
        if (objArr != null) {
            s90.R(objArr, 0, this.J);
        }
        this.M = 0;
        this.J = 0;
        this.L++;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection<?> collection) {
        t40.n(collection, "m");
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
        t40.n(entry, "entry");
        int iC = c(entry.getKey());
        if (iC < 0) {
            return false;
        }
        Object[] objArr = this.F;
        t40.k(objArr);
        return t40.f(objArr[iC], entry.getValue());
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
        int i = this.J;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.G[i] >= 0) {
                Object[] objArr = this.F;
                t40.k(objArr);
                if (t40.f(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    public final int e(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.K;
    }

    public final sb0 entriesIterator$kotlin_stdlib() {
        return new sb0(this);
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
        r5.G[r2] = r0;
        r2 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(int r6) {
        /*
            r5 = this;
            int r0 = r5.L
            int r0 = r0 + 1
            r5.L = r0
            int r0 = r5.J
            int r1 = r5.size()
            r2 = 0
            if (r0 <= r1) goto L12
            r5.a(r2)
        L12:
            int[] r0 = new int[r6]
            r5.H = r0
            rb0 r0 = kotlin.collections.builders.MapBuilder.Companion
            r0.getClass()
            int r6 = java.lang.Integer.numberOfLeadingZeros(r6)
            int r6 = r6 + 1
            r5.K = r6
        L23:
            int r6 = r5.J
            if (r2 >= r6) goto L57
            int r6 = r2 + 1
            java.lang.Object[] r0 = r5.E
            r0 = r0[r2]
            int r0 = r5.e(r0)
            int r1 = r5.I
        L33:
            int[] r3 = r5.H
            r4 = r3[r0]
            if (r4 != 0) goto L41
            r3[r0] = r6
            int[] r1 = r5.G
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
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.E
            java.lang.String r1 = "<this>"
            defpackage.t40.n(r0, r1)
            r1 = 0
            r0[r12] = r1
            java.lang.Object[] r0 = r11.F
            if (r0 == 0) goto L10
            r0[r12] = r1
        L10:
            int[] r0 = r11.G
            r0 = r0[r12]
            int r1 = r11.I
            int r1 = r1 * 2
            int[] r2 = r11.H
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
            int[] r0 = r11.H
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L2f
        L2e:
            r0 = r5
        L2f:
            int r4 = r4 + 1
            int r5 = r11.I
            r6 = -1
            if (r4 <= r5) goto L3b
            int[] r0 = r11.H
            r0[r1] = r2
            goto L6c
        L3b:
            int[] r5 = r11.H
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
            java.lang.Object[] r5 = r11.E
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.e(r5)
            int r5 = r5 - r0
            int[] r9 = r11.H
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L65
            r9[r1] = r7
            int[] r4 = r11.G
            r4[r8] = r1
            goto L48
        L65:
            int r3 = r3 + r6
            if (r3 >= 0) goto L24
            int[] r0 = r11.H
            r0[r1] = r6
        L6c:
            int[] r0 = r11.G
            r0[r12] = r6
            int r12 = r11.size()
            int r12 = r12 + r6
            r11.M = r12
            int r12 = r11.L
            int r12 = r12 + 1
            r11.L = r12
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
        Object[] objArr = this.F;
        t40.k(objArr);
        return (V) objArr[iC];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.E.length;
    }

    public Set<Map.Entry<K, V>> getEntries() {
        xb0 xb0Var = this.P;
        if (xb0Var != null) {
            return xb0Var;
        }
        xb0 xb0Var2 = new xb0(this, 0);
        this.P = xb0Var2;
        return xb0Var2;
    }

    public Set<K> getKeys() {
        xb0 xb0Var = this.N;
        if (xb0Var != null) {
            return xb0Var;
        }
        xb0 xb0Var2 = new xb0(this, 1);
        this.N = xb0Var2;
        return xb0Var2;
    }

    public int getSize() {
        return this.M;
    }

    public Collection<V> getValues() {
        yb0 yb0Var = this.O;
        if (yb0Var != null) {
            return yb0Var;
        }
        yb0 yb0Var2 = new yb0(this);
        this.O = yb0Var2;
        return yb0Var2;
    }

    @Override // java.util.Map
    public int hashCode() {
        sb0 sb0VarEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (sb0VarEntriesIterator$kotlin_stdlib.hasNext()) {
            int i2 = sb0VarEntriesIterator$kotlin_stdlib.E;
            MapBuilder mapBuilder = (MapBuilder) sb0VarEntriesIterator$kotlin_stdlib.H;
            if (i2 >= mapBuilder.J) {
                throw new NoSuchElementException();
            }
            int i3 = sb0VarEntriesIterator$kotlin_stdlib.E;
            sb0VarEntriesIterator$kotlin_stdlib.E = i3 + 1;
            sb0VarEntriesIterator$kotlin_stdlib.F = i3;
            Object obj = mapBuilder.E[sb0VarEntriesIterator$kotlin_stdlib.F];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = mapBuilder.F;
            t40.k(objArr);
            Object obj2 = objArr[sb0VarEntriesIterator$kotlin_stdlib.F];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            sb0VarEntriesIterator$kotlin_stdlib.e();
            i += iHashCode ^ iHashCode2;
        }
        return i;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.Q;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    public final vb0 keysIterator$kotlin_stdlib() {
        return new vb0(this);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        checkIsMutable$kotlin_stdlib();
        int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(k);
        Object[] objArr = this.F;
        if (objArr == null) {
            int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
            if (capacity$kotlin_stdlib < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[capacity$kotlin_stdlib];
            this.F = objArr;
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
        t40.n(map, "from");
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry<? extends K, ? extends V>> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        b(setEntrySet.size());
        for (Map.Entry<? extends K, ? extends V> entry : setEntrySet) {
            int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
            Object[] objArr = this.F;
            if (objArr == null) {
                int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
                if (capacity$kotlin_stdlib < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[capacity$kotlin_stdlib];
                this.F = objArr;
            }
            if (iAddKey$kotlin_stdlib >= 0) {
                objArr[iAddKey$kotlin_stdlib] = entry.getValue();
            } else {
                int i = (-iAddKey$kotlin_stdlib) - 1;
                if (!t40.f(entry.getValue(), objArr[i])) {
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
        Object[] objArr = this.F;
        t40.k(objArr);
        V v = (V) objArr[iC];
        g(iC);
        return v;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> entry) {
        t40.n(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int iC = c(entry.getKey());
        if (iC < 0) {
            return false;
        }
        Object[] objArr = this.F;
        t40.k(objArr);
        if (!t40.f(objArr[iC], entry.getValue())) {
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
        sb0 sb0VarEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (sb0VarEntriesIterator$kotlin_stdlib.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = sb0VarEntriesIterator$kotlin_stdlib.E;
            MapBuilder mapBuilder = (MapBuilder) sb0VarEntriesIterator$kotlin_stdlib.H;
            if (i2 >= mapBuilder.J) {
                throw new NoSuchElementException();
            }
            int i3 = sb0VarEntriesIterator$kotlin_stdlib.E;
            sb0VarEntriesIterator$kotlin_stdlib.E = i3 + 1;
            sb0VarEntriesIterator$kotlin_stdlib.F = i3;
            Object obj = mapBuilder.E[sb0VarEntriesIterator$kotlin_stdlib.F];
            if (obj == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = mapBuilder.F;
            t40.k(objArr);
            Object obj2 = objArr[sb0VarEntriesIterator$kotlin_stdlib.F];
            if (obj2 == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            sb0VarEntriesIterator$kotlin_stdlib.e();
            i++;
        }
        sb.append(JsonUtils.CLOSE);
        String string = sb.toString();
        t40.m(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public final wb0 valuesIterator$kotlin_stdlib() {
        return new wb0(this);
    }

    public MapBuilder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i];
        int[] iArr = new int[i];
        Companion.getClass();
        int iHighestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.E = objArr;
        this.F = null;
        this.G = iArr;
        this.H = new int[iHighestOneBit];
        this.I = 2;
        this.J = 0;
        this.K = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
