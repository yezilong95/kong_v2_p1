package com.google.gson.internal;

import defpackage.l90;
import defpackage.n90;
import defpackage.q4;
import defpackage.w10;
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
    public static final q4 M = new q4(4);
    public final Comparator E;
    public n90[] F;
    public final n90 G;
    public int H;
    public int I;
    public int J;
    public l90 K;
    public l90 L;

    public LinkedHashTreeMap() {
        this(M);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final n90 a(Object obj, boolean z) {
        int iCompareTo;
        n90 n90Var;
        boolean z2;
        n90 n90Var2;
        n90 n90Var3;
        n90 n90Var4;
        n90 n90Var5;
        n90 n90Var6;
        n90[] n90VarArr = this.F;
        int iHashCode = obj.hashCode();
        int i = iHashCode ^ ((iHashCode >>> 20) ^ (iHashCode >>> 12));
        int i2 = ((i >>> 7) ^ i) ^ (i >>> 4);
        boolean z3 = true;
        int length = i2 & (n90VarArr.length - 1);
        n90 n90Var7 = n90VarArr[length];
        q4 q4Var = M;
        Comparator comparator = this.E;
        n90 n90Var8 = null;
        if (n90Var7 != null) {
            Comparable comparable = comparator == q4Var ? (Comparable) obj : null;
            while (true) {
                Object obj2 = n90Var7.J;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return n90Var7;
                }
                n90 n90Var9 = iCompareTo < 0 ? n90Var7.F : n90Var7.G;
                if (n90Var9 == null) {
                    break;
                }
                n90Var7 = n90Var9;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        n90 n90Var10 = this.G;
        if (n90Var7 != null) {
            n90 n90Var11 = n90Var7;
            n90Var = new n90(n90Var11, obj, i2, n90Var10, n90Var10.I);
            if (iCompareTo < 0) {
                n90Var11.F = n90Var;
            } else {
                n90Var11.G = n90Var;
            }
            b(n90Var11, true);
        } else {
            if (comparator == q4Var && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            n90Var = new n90(n90Var7, obj, i2, n90Var10, n90Var10.I);
            n90VarArr[length] = n90Var;
        }
        int i3 = this.H;
        this.H = i3 + 1;
        if (i3 > this.J) {
            n90[] n90VarArr2 = this.F;
            int length2 = n90VarArr2.length;
            int i4 = length2 * 2;
            n90[] n90VarArr3 = new n90[i4];
            w10 w10Var = new w10();
            w10 w10Var2 = new w10();
            int i5 = 0;
            while (i5 < length2) {
                n90 n90Var12 = n90VarArr2[i5];
                if (n90Var12 == null) {
                    z2 = z3;
                    n90Var3 = n90Var8;
                } else {
                    n90 n90Var13 = n90Var8;
                    for (n90 n90Var14 = n90Var12; n90Var14 != null; n90Var14 = n90Var14.F) {
                        n90Var14.E = n90Var13;
                        n90Var13 = n90Var14;
                    }
                    int i6 = 0;
                    int i7 = 0;
                    while (true) {
                        if (n90Var13 != null) {
                            z2 = z3;
                            n90 n90Var15 = n90Var13.E;
                            n90Var13.E = n90Var8;
                            n90 n90Var16 = n90Var13.G;
                            while (true) {
                                n90 n90Var17 = n90Var16;
                                n90Var2 = n90Var15;
                                n90Var15 = n90Var17;
                                if (n90Var15 == null) {
                                    break;
                                }
                                n90Var15.E = n90Var2;
                                n90Var16 = n90Var15.F;
                            }
                        } else {
                            n90 n90Var18 = n90Var13;
                            n90Var13 = n90Var8;
                            n90Var2 = n90Var18;
                            z2 = z3;
                        }
                        if (n90Var13 == null) {
                            break;
                        }
                        if ((n90Var13.K & length2) == 0) {
                            i6++;
                        } else {
                            i7++;
                        }
                        n90Var13 = n90Var2;
                        z3 = z2;
                        n90Var8 = null;
                    }
                    w10Var.a = ((Integer.highestOneBit(i6) * 2) - 1) - i6;
                    w10Var.c = 0;
                    w10Var.b = 0;
                    n90Var3 = null;
                    w10Var.d = null;
                    w10Var2.a = ((Integer.highestOneBit(i7) * 2) - 1) - i7;
                    w10Var2.c = 0;
                    w10Var2.b = 0;
                    w10Var2.d = null;
                    n90 n90Var19 = null;
                    while (n90Var12 != null) {
                        n90Var12.E = n90Var19;
                        n90 n90Var20 = n90Var12;
                        n90Var12 = n90Var12.F;
                        n90Var19 = n90Var20;
                    }
                    while (true) {
                        if (n90Var19 != null) {
                            n90 n90Var21 = n90Var19.E;
                            n90Var19.E = null;
                            n90 n90Var22 = n90Var19.G;
                            while (true) {
                                n90 n90Var23 = n90Var22;
                                n90Var4 = n90Var21;
                                n90Var21 = n90Var23;
                                if (n90Var21 == null) {
                                    break;
                                }
                                n90Var21.E = n90Var4;
                                n90Var22 = n90Var21.F;
                            }
                        } else {
                            n90Var4 = n90Var19;
                            n90Var19 = null;
                        }
                        if (n90Var19 == null) {
                            break;
                        }
                        if ((n90Var19.K & length2) == 0) {
                            w10Var.a(n90Var19);
                        } else {
                            w10Var2.a(n90Var19);
                        }
                        n90Var19 = n90Var4;
                    }
                    if (i6 > 0) {
                        n90Var5 = (n90) w10Var.d;
                        if (n90Var5.E != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        n90Var5 = null;
                    }
                    n90VarArr3[i5] = n90Var5;
                    int i8 = i5 + length2;
                    if (i7 > 0) {
                        n90Var6 = (n90) w10Var2.d;
                        if (n90Var6.E != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        n90Var6 = null;
                    }
                    n90VarArr3[i8] = n90Var6;
                }
                i5++;
                n90Var8 = n90Var3;
                z3 = z2;
            }
            this.F = n90VarArr3;
            this.J = (i4 / 4) + (i4 / 2);
        }
        this.I++;
        return n90Var;
    }

    public final void b(n90 n90Var, boolean z) {
        while (n90Var != null) {
            n90 n90Var2 = n90Var.F;
            n90 n90Var3 = n90Var.G;
            int i = n90Var2 != null ? n90Var2.M : 0;
            int i2 = n90Var3 != null ? n90Var3.M : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                n90 n90Var4 = n90Var3.F;
                n90 n90Var5 = n90Var3.G;
                int i4 = (n90Var4 != null ? n90Var4.M : 0) - (n90Var5 != null ? n90Var5.M : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    e(n90Var);
                } else {
                    f(n90Var3);
                    e(n90Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                n90 n90Var6 = n90Var2.F;
                n90 n90Var7 = n90Var2.G;
                int i5 = (n90Var6 != null ? n90Var6.M : 0) - (n90Var7 != null ? n90Var7.M : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    f(n90Var);
                } else {
                    e(n90Var2);
                    f(n90Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                n90Var.M = i + 1;
                if (z) {
                    return;
                }
            } else {
                n90Var.M = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            n90Var = n90Var.E;
        }
    }

    public final void c(n90 n90Var, boolean z) {
        n90 n90Var2;
        n90 n90Var3;
        int i;
        if (z) {
            n90 n90Var4 = n90Var.I;
            n90Var4.H = n90Var.H;
            n90Var.H.I = n90Var4;
            n90Var.I = null;
            n90Var.H = null;
        }
        n90 n90Var5 = n90Var.F;
        n90 n90Var6 = n90Var.G;
        n90 n90Var7 = n90Var.E;
        int i2 = 0;
        if (n90Var5 == null || n90Var6 == null) {
            if (n90Var5 != null) {
                d(n90Var, n90Var5);
                n90Var.F = null;
            } else if (n90Var6 != null) {
                d(n90Var, n90Var6);
                n90Var.G = null;
            } else {
                d(n90Var, null);
            }
            b(n90Var7, false);
            this.H--;
            this.I++;
            return;
        }
        if (n90Var5.M > n90Var6.M) {
            n90 n90Var8 = n90Var5.G;
            while (true) {
                n90 n90Var9 = n90Var8;
                n90Var3 = n90Var5;
                n90Var5 = n90Var9;
                if (n90Var5 == null) {
                    break;
                } else {
                    n90Var8 = n90Var5.G;
                }
            }
        } else {
            n90 n90Var10 = n90Var6.F;
            while (true) {
                n90Var2 = n90Var6;
                n90Var6 = n90Var10;
                if (n90Var6 == null) {
                    break;
                } else {
                    n90Var10 = n90Var6.F;
                }
            }
            n90Var3 = n90Var2;
        }
        c(n90Var3, false);
        n90 n90Var11 = n90Var.F;
        if (n90Var11 != null) {
            i = n90Var11.M;
            n90Var3.F = n90Var11;
            n90Var11.E = n90Var3;
            n90Var.F = null;
        } else {
            i = 0;
        }
        n90 n90Var12 = n90Var.G;
        if (n90Var12 != null) {
            i2 = n90Var12.M;
            n90Var3.G = n90Var12;
            n90Var12.E = n90Var3;
            n90Var.G = null;
        }
        n90Var3.M = Math.max(i, i2) + 1;
        d(n90Var, n90Var3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.F, (Object) null);
        this.H = 0;
        this.I++;
        n90 n90Var = this.G;
        n90 n90Var2 = n90Var.H;
        while (n90Var2 != n90Var) {
            n90 n90Var3 = n90Var2.H;
            n90Var2.I = null;
            n90Var2.H = null;
            n90Var2 = n90Var3;
        }
        n90Var.I = n90Var;
        n90Var.H = n90Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        n90 n90VarA = null;
        if (obj != null) {
            try {
                n90VarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return n90VarA != null;
    }

    public final void d(n90 n90Var, n90 n90Var2) {
        n90 n90Var3 = n90Var.E;
        n90Var.E = null;
        if (n90Var2 != null) {
            n90Var2.E = n90Var3;
        }
        if (n90Var3 == null) {
            this.F[n90Var.K & (r0.length - 1)] = n90Var2;
        } else if (n90Var3.F == n90Var) {
            n90Var3.F = n90Var2;
        } else {
            n90Var3.G = n90Var2;
        }
    }

    public final void e(n90 n90Var) {
        n90 n90Var2 = n90Var.F;
        n90 n90Var3 = n90Var.G;
        n90 n90Var4 = n90Var3.F;
        n90 n90Var5 = n90Var3.G;
        n90Var.G = n90Var4;
        if (n90Var4 != null) {
            n90Var4.E = n90Var;
        }
        d(n90Var, n90Var3);
        n90Var3.F = n90Var;
        n90Var.E = n90Var3;
        int iMax = Math.max(n90Var2 != null ? n90Var2.M : 0, n90Var4 != null ? n90Var4.M : 0) + 1;
        n90Var.M = iMax;
        n90Var3.M = Math.max(iMax, n90Var5 != null ? n90Var5.M : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        l90 l90Var = this.K;
        if (l90Var != null) {
            return l90Var;
        }
        l90 l90Var2 = new l90(this, 0);
        this.K = l90Var2;
        return l90Var2;
    }

    public final void f(n90 n90Var) {
        n90 n90Var2 = n90Var.F;
        n90 n90Var3 = n90Var.G;
        n90 n90Var4 = n90Var2.F;
        n90 n90Var5 = n90Var2.G;
        n90Var.F = n90Var5;
        if (n90Var5 != null) {
            n90Var5.E = n90Var;
        }
        d(n90Var, n90Var2);
        n90Var2.G = n90Var;
        n90Var.E = n90Var2;
        int iMax = Math.max(n90Var3 != null ? n90Var3.M : 0, n90Var5 != null ? n90Var5.M : 0) + 1;
        n90Var.M = iMax;
        n90Var2.M = Math.max(iMax, n90Var4 != null ? n90Var4.M : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        n90 n90VarA;
        if (obj != null) {
            try {
                n90VarA = a(obj, false);
            } catch (ClassCastException unused) {
                n90VarA = null;
            }
        } else {
            n90VarA = null;
        }
        if (n90VarA != null) {
            return (V) n90VarA.L;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        l90 l90Var = this.L;
        if (l90Var != null) {
            return l90Var;
        }
        l90 l90Var2 = new l90(this, 1);
        this.L = l90Var2;
        return l90Var2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        n90 n90VarA = a(k, true);
        V v2 = (V) n90VarA.L;
        n90VarA.L = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        n90 n90VarA;
        if (obj != null) {
            try {
                n90VarA = a(obj, false);
            } catch (ClassCastException unused) {
                n90VarA = null;
            }
        } else {
            n90VarA = null;
        }
        if (n90VarA != null) {
            c(n90VarA, true);
        }
        if (n90VarA != null) {
            return (V) n90VarA.L;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.H;
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.H = 0;
        this.I = 0;
        this.E = comparator == null ? M : comparator;
        this.G = new n90();
        this.F = new n90[16];
        this.J = 12;
    }
}
