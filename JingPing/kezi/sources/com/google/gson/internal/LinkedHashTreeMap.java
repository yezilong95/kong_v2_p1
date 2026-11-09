package com.google.gson.internal;

import defpackage.h20;
import defpackage.j20;
import defpackage.l3;
import defpackage.zu;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final l3 j = new l3(5);
    public final Comparator b;
    public j20[] c;
    public final j20 d;
    public int e;
    public int f;
    public int g;
    public h20 h;
    public h20 i;

    public LinkedHashTreeMap() {
        this(j);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final j20 a(Object obj, boolean z) {
        int iCompareTo;
        j20 j20Var;
        boolean z2;
        j20 j20Var2;
        j20 j20Var3;
        j20 j20Var4;
        j20 j20Var5;
        j20 j20Var6;
        j20[] j20VarArr = this.c;
        int iHashCode = obj.hashCode();
        int i = iHashCode ^ ((iHashCode >>> 20) ^ (iHashCode >>> 12));
        int i2 = ((i >>> 7) ^ i) ^ (i >>> 4);
        boolean z3 = true;
        int length = i2 & (j20VarArr.length - 1);
        j20 j20Var7 = j20VarArr[length];
        l3 l3Var = j;
        Comparator comparator = this.b;
        j20 j20Var8 = null;
        if (j20Var7 != null) {
            Comparable comparable = comparator == l3Var ? (Comparable) obj : null;
            while (true) {
                Object obj2 = j20Var7.g;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return j20Var7;
                }
                j20 j20Var9 = iCompareTo < 0 ? j20Var7.c : j20Var7.d;
                if (j20Var9 == null) {
                    break;
                }
                j20Var7 = j20Var9;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        j20 j20Var10 = this.d;
        if (j20Var7 != null) {
            j20 j20Var11 = j20Var7;
            j20Var = new j20(j20Var11, obj, i2, j20Var10, j20Var10.f);
            if (iCompareTo < 0) {
                j20Var11.c = j20Var;
            } else {
                j20Var11.d = j20Var;
            }
            b(j20Var11, true);
        } else {
            if (comparator == l3Var && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            j20Var = new j20(j20Var7, obj, i2, j20Var10, j20Var10.f);
            j20VarArr[length] = j20Var;
        }
        int i3 = this.e;
        this.e = i3 + 1;
        if (i3 > this.g) {
            j20[] j20VarArr2 = this.c;
            int length2 = j20VarArr2.length;
            int i4 = length2 * 2;
            j20[] j20VarArr3 = new j20[i4];
            zu zuVar = new zu();
            zu zuVar2 = new zu();
            int i5 = 0;
            while (i5 < length2) {
                j20 j20Var12 = j20VarArr2[i5];
                if (j20Var12 == null) {
                    z2 = z3;
                    j20Var3 = j20Var8;
                } else {
                    j20 j20Var13 = j20Var8;
                    for (j20 j20Var14 = j20Var12; j20Var14 != null; j20Var14 = j20Var14.c) {
                        j20Var14.b = j20Var13;
                        j20Var13 = j20Var14;
                    }
                    int i6 = 0;
                    int i7 = 0;
                    while (true) {
                        if (j20Var13 != null) {
                            z2 = z3;
                            j20 j20Var15 = j20Var13.b;
                            j20Var13.b = j20Var8;
                            j20 j20Var16 = j20Var13.d;
                            while (true) {
                                j20 j20Var17 = j20Var16;
                                j20Var2 = j20Var15;
                                j20Var15 = j20Var17;
                                if (j20Var15 == null) {
                                    break;
                                }
                                j20Var15.b = j20Var2;
                                j20Var16 = j20Var15.c;
                            }
                        } else {
                            j20 j20Var18 = j20Var13;
                            j20Var13 = j20Var8;
                            j20Var2 = j20Var18;
                            z2 = z3;
                        }
                        if (j20Var13 == null) {
                            break;
                        }
                        if ((j20Var13.h & length2) == 0) {
                            i6++;
                        } else {
                            i7++;
                        }
                        j20Var13 = j20Var2;
                        z3 = z2;
                        j20Var8 = null;
                    }
                    zuVar.a = ((Integer.highestOneBit(i6) * 2) - 1) - i6;
                    zuVar.c = 0;
                    zuVar.b = 0;
                    j20Var3 = null;
                    zuVar.d = null;
                    zuVar2.a = ((Integer.highestOneBit(i7) * 2) - 1) - i7;
                    zuVar2.c = 0;
                    zuVar2.b = 0;
                    zuVar2.d = null;
                    j20 j20Var19 = null;
                    while (j20Var12 != null) {
                        j20Var12.b = j20Var19;
                        j20 j20Var20 = j20Var12;
                        j20Var12 = j20Var12.c;
                        j20Var19 = j20Var20;
                    }
                    while (true) {
                        if (j20Var19 != null) {
                            j20 j20Var21 = j20Var19.b;
                            j20Var19.b = null;
                            j20 j20Var22 = j20Var19.d;
                            while (true) {
                                j20 j20Var23 = j20Var22;
                                j20Var4 = j20Var21;
                                j20Var21 = j20Var23;
                                if (j20Var21 == null) {
                                    break;
                                }
                                j20Var21.b = j20Var4;
                                j20Var22 = j20Var21.c;
                            }
                        } else {
                            j20Var4 = j20Var19;
                            j20Var19 = null;
                        }
                        if (j20Var19 == null) {
                            break;
                        }
                        if ((j20Var19.h & length2) == 0) {
                            zuVar.a(j20Var19);
                        } else {
                            zuVar2.a(j20Var19);
                        }
                        j20Var19 = j20Var4;
                    }
                    if (i6 > 0) {
                        j20Var5 = (j20) zuVar.d;
                        if (j20Var5.b != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        j20Var5 = null;
                    }
                    j20VarArr3[i5] = j20Var5;
                    int i8 = i5 + length2;
                    if (i7 > 0) {
                        j20Var6 = (j20) zuVar2.d;
                        if (j20Var6.b != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        j20Var6 = null;
                    }
                    j20VarArr3[i8] = j20Var6;
                }
                i5++;
                j20Var8 = j20Var3;
                z3 = z2;
            }
            this.c = j20VarArr3;
            this.g = (i4 / 4) + (i4 / 2);
        }
        this.f++;
        return j20Var;
    }

    public final void b(j20 j20Var, boolean z) {
        while (j20Var != null) {
            j20 j20Var2 = j20Var.c;
            j20 j20Var3 = j20Var.d;
            int i = j20Var2 != null ? j20Var2.j : 0;
            int i2 = j20Var3 != null ? j20Var3.j : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                j20 j20Var4 = j20Var3.c;
                j20 j20Var5 = j20Var3.d;
                int i4 = (j20Var4 != null ? j20Var4.j : 0) - (j20Var5 != null ? j20Var5.j : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    e(j20Var);
                } else {
                    f(j20Var3);
                    e(j20Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                j20 j20Var6 = j20Var2.c;
                j20 j20Var7 = j20Var2.d;
                int i5 = (j20Var6 != null ? j20Var6.j : 0) - (j20Var7 != null ? j20Var7.j : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    f(j20Var);
                } else {
                    e(j20Var2);
                    f(j20Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                j20Var.j = i + 1;
                if (z) {
                    return;
                }
            } else {
                j20Var.j = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            j20Var = j20Var.b;
        }
    }

    public final void c(j20 j20Var, boolean z) {
        j20 j20Var2;
        j20 j20Var3;
        int i;
        if (z) {
            j20 j20Var4 = j20Var.f;
            j20Var4.e = j20Var.e;
            j20Var.e.f = j20Var4;
            j20Var.f = null;
            j20Var.e = null;
        }
        j20 j20Var5 = j20Var.c;
        j20 j20Var6 = j20Var.d;
        j20 j20Var7 = j20Var.b;
        int i2 = 0;
        if (j20Var5 == null || j20Var6 == null) {
            if (j20Var5 != null) {
                d(j20Var, j20Var5);
                j20Var.c = null;
            } else if (j20Var6 != null) {
                d(j20Var, j20Var6);
                j20Var.d = null;
            } else {
                d(j20Var, null);
            }
            b(j20Var7, false);
            this.e--;
            this.f++;
            return;
        }
        if (j20Var5.j > j20Var6.j) {
            j20 j20Var8 = j20Var5.d;
            while (true) {
                j20 j20Var9 = j20Var8;
                j20Var3 = j20Var5;
                j20Var5 = j20Var9;
                if (j20Var5 == null) {
                    break;
                } else {
                    j20Var8 = j20Var5.d;
                }
            }
        } else {
            j20 j20Var10 = j20Var6.c;
            while (true) {
                j20Var2 = j20Var6;
                j20Var6 = j20Var10;
                if (j20Var6 == null) {
                    break;
                } else {
                    j20Var10 = j20Var6.c;
                }
            }
            j20Var3 = j20Var2;
        }
        c(j20Var3, false);
        j20 j20Var11 = j20Var.c;
        if (j20Var11 != null) {
            i = j20Var11.j;
            j20Var3.c = j20Var11;
            j20Var11.b = j20Var3;
            j20Var.c = null;
        } else {
            i = 0;
        }
        j20 j20Var12 = j20Var.d;
        if (j20Var12 != null) {
            i2 = j20Var12.j;
            j20Var3.d = j20Var12;
            j20Var12.b = j20Var3;
            j20Var.d = null;
        }
        j20Var3.j = Math.max(i, i2) + 1;
        d(j20Var, j20Var3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.c, (Object) null);
        this.e = 0;
        this.f++;
        j20 j20Var = this.d;
        j20 j20Var2 = j20Var.e;
        while (j20Var2 != j20Var) {
            j20 j20Var3 = j20Var2.e;
            j20Var2.f = null;
            j20Var2.e = null;
            j20Var2 = j20Var3;
        }
        j20Var.f = j20Var;
        j20Var.e = j20Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        j20 j20VarA = null;
        if (obj != null) {
            try {
                j20VarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return j20VarA != null;
    }

    public final void d(j20 j20Var, j20 j20Var2) {
        j20 j20Var3 = j20Var.b;
        j20Var.b = null;
        if (j20Var2 != null) {
            j20Var2.b = j20Var3;
        }
        if (j20Var3 == null) {
            this.c[j20Var.h & (r0.length - 1)] = j20Var2;
        } else if (j20Var3.c == j20Var) {
            j20Var3.c = j20Var2;
        } else {
            j20Var3.d = j20Var2;
        }
    }

    public final void e(j20 j20Var) {
        j20 j20Var2 = j20Var.c;
        j20 j20Var3 = j20Var.d;
        j20 j20Var4 = j20Var3.c;
        j20 j20Var5 = j20Var3.d;
        j20Var.d = j20Var4;
        if (j20Var4 != null) {
            j20Var4.b = j20Var;
        }
        d(j20Var, j20Var3);
        j20Var3.c = j20Var;
        j20Var.b = j20Var3;
        int iMax = Math.max(j20Var2 != null ? j20Var2.j : 0, j20Var4 != null ? j20Var4.j : 0) + 1;
        j20Var.j = iMax;
        j20Var3.j = Math.max(iMax, j20Var5 != null ? j20Var5.j : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        h20 h20Var = this.h;
        if (h20Var != null) {
            return h20Var;
        }
        h20 h20Var2 = new h20(this, 0);
        this.h = h20Var2;
        return h20Var2;
    }

    public final void f(j20 j20Var) {
        j20 j20Var2 = j20Var.c;
        j20 j20Var3 = j20Var.d;
        j20 j20Var4 = j20Var2.c;
        j20 j20Var5 = j20Var2.d;
        j20Var.c = j20Var5;
        if (j20Var5 != null) {
            j20Var5.b = j20Var;
        }
        d(j20Var, j20Var2);
        j20Var2.d = j20Var;
        j20Var.b = j20Var2;
        int iMax = Math.max(j20Var3 != null ? j20Var3.j : 0, j20Var5 != null ? j20Var5.j : 0) + 1;
        j20Var.j = iMax;
        j20Var2.j = Math.max(iMax, j20Var4 != null ? j20Var4.j : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        j20 j20VarA;
        if (obj != null) {
            try {
                j20VarA = a(obj, false);
            } catch (ClassCastException unused) {
                j20VarA = null;
            }
        } else {
            j20VarA = null;
        }
        if (j20VarA != null) {
            return (V) j20VarA.i;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        h20 h20Var = this.i;
        if (h20Var != null) {
            return h20Var;
        }
        h20 h20Var2 = new h20(this, 1);
        this.i = h20Var2;
        return h20Var2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        j20 j20VarA = a(k, true);
        V v2 = (V) j20VarA.i;
        j20VarA.i = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        j20 j20VarA;
        if (obj != null) {
            try {
                j20VarA = a(obj, false);
            } catch (ClassCastException unused) {
                j20VarA = null;
            }
        } else {
            j20VarA = null;
        }
        if (j20VarA != null) {
            c(j20VarA, true);
        }
        if (j20VarA != null) {
            return (V) j20VarA.i;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.e;
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.e = 0;
        this.f = 0;
        this.b = comparator == null ? j : comparator;
        this.d = new j20();
        this.c = new j20[16];
        this.g = 12;
    }
}
