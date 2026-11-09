package com.google.gson.internal;

import defpackage.p90;
import defpackage.q4;
import defpackage.q90;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final q4 L = new q4(5);
    public final Comparator E;
    public q90 F;
    public int G;
    public int H;
    public final q90 I;
    public p90 J;
    public p90 K;

    public LinkedTreeMap() {
        this(L);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final q90 a(Object obj, boolean z) {
        int iCompareTo;
        q90 q90Var;
        q90 q90Var2 = this.F;
        q4 q4Var = L;
        Comparator comparator = this.E;
        if (q90Var2 != null) {
            Comparable comparable = comparator == q4Var ? (Comparable) obj : null;
            while (true) {
                Object obj2 = q90Var2.J;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return q90Var2;
                }
                q90 q90Var3 = iCompareTo < 0 ? q90Var2.F : q90Var2.G;
                if (q90Var3 == null) {
                    break;
                }
                q90Var2 = q90Var3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        q90 q90Var4 = this.I;
        if (q90Var2 != null) {
            q90Var = new q90(q90Var2, obj, q90Var4, q90Var4.I);
            if (iCompareTo < 0) {
                q90Var2.F = q90Var;
            } else {
                q90Var2.G = q90Var;
            }
            b(q90Var2, true);
        } else {
            if (comparator == q4Var && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            q90Var = new q90(q90Var2, obj, q90Var4, q90Var4.I);
            this.F = q90Var;
        }
        this.G++;
        this.H++;
        return q90Var;
    }

    public final void b(q90 q90Var, boolean z) {
        while (q90Var != null) {
            q90 q90Var2 = q90Var.F;
            q90 q90Var3 = q90Var.G;
            int i = q90Var2 != null ? q90Var2.L : 0;
            int i2 = q90Var3 != null ? q90Var3.L : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                q90 q90Var4 = q90Var3.F;
                q90 q90Var5 = q90Var3.G;
                int i4 = (q90Var4 != null ? q90Var4.L : 0) - (q90Var5 != null ? q90Var5.L : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    e(q90Var);
                } else {
                    f(q90Var3);
                    e(q90Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                q90 q90Var6 = q90Var2.F;
                q90 q90Var7 = q90Var2.G;
                int i5 = (q90Var6 != null ? q90Var6.L : 0) - (q90Var7 != null ? q90Var7.L : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    f(q90Var);
                } else {
                    e(q90Var2);
                    f(q90Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                q90Var.L = i + 1;
                if (z) {
                    return;
                }
            } else {
                q90Var.L = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            q90Var = q90Var.E;
        }
    }

    public final void c(q90 q90Var, boolean z) {
        q90 q90Var2;
        q90 q90Var3;
        int i;
        if (z) {
            q90 q90Var4 = q90Var.I;
            q90Var4.H = q90Var.H;
            q90Var.H.I = q90Var4;
        }
        q90 q90Var5 = q90Var.F;
        q90 q90Var6 = q90Var.G;
        q90 q90Var7 = q90Var.E;
        int i2 = 0;
        if (q90Var5 == null || q90Var6 == null) {
            if (q90Var5 != null) {
                d(q90Var, q90Var5);
                q90Var.F = null;
            } else if (q90Var6 != null) {
                d(q90Var, q90Var6);
                q90Var.G = null;
            } else {
                d(q90Var, null);
            }
            b(q90Var7, false);
            this.G--;
            this.H++;
            return;
        }
        if (q90Var5.L > q90Var6.L) {
            q90 q90Var8 = q90Var5.G;
            while (true) {
                q90 q90Var9 = q90Var8;
                q90Var3 = q90Var5;
                q90Var5 = q90Var9;
                if (q90Var5 == null) {
                    break;
                } else {
                    q90Var8 = q90Var5.G;
                }
            }
        } else {
            q90 q90Var10 = q90Var6.F;
            while (true) {
                q90Var2 = q90Var6;
                q90Var6 = q90Var10;
                if (q90Var6 == null) {
                    break;
                } else {
                    q90Var10 = q90Var6.F;
                }
            }
            q90Var3 = q90Var2;
        }
        c(q90Var3, false);
        q90 q90Var11 = q90Var.F;
        if (q90Var11 != null) {
            i = q90Var11.L;
            q90Var3.F = q90Var11;
            q90Var11.E = q90Var3;
            q90Var.F = null;
        } else {
            i = 0;
        }
        q90 q90Var12 = q90Var.G;
        if (q90Var12 != null) {
            i2 = q90Var12.L;
            q90Var3.G = q90Var12;
            q90Var12.E = q90Var3;
            q90Var.G = null;
        }
        q90Var3.L = Math.max(i, i2) + 1;
        d(q90Var, q90Var3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.F = null;
        this.G = 0;
        this.H++;
        q90 q90Var = this.I;
        q90Var.I = q90Var;
        q90Var.H = q90Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        q90 q90VarA = null;
        if (obj != null) {
            try {
                q90VarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return q90VarA != null;
    }

    public final void d(q90 q90Var, q90 q90Var2) {
        q90 q90Var3 = q90Var.E;
        q90Var.E = null;
        if (q90Var2 != null) {
            q90Var2.E = q90Var3;
        }
        if (q90Var3 == null) {
            this.F = q90Var2;
        } else if (q90Var3.F == q90Var) {
            q90Var3.F = q90Var2;
        } else {
            q90Var3.G = q90Var2;
        }
    }

    public final void e(q90 q90Var) {
        q90 q90Var2 = q90Var.F;
        q90 q90Var3 = q90Var.G;
        q90 q90Var4 = q90Var3.F;
        q90 q90Var5 = q90Var3.G;
        q90Var.G = q90Var4;
        if (q90Var4 != null) {
            q90Var4.E = q90Var;
        }
        d(q90Var, q90Var3);
        q90Var3.F = q90Var;
        q90Var.E = q90Var3;
        int iMax = Math.max(q90Var2 != null ? q90Var2.L : 0, q90Var4 != null ? q90Var4.L : 0) + 1;
        q90Var.L = iMax;
        q90Var3.L = Math.max(iMax, q90Var5 != null ? q90Var5.L : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        p90 p90Var = this.J;
        if (p90Var != null) {
            return p90Var;
        }
        p90 p90Var2 = new p90(this, 0);
        this.J = p90Var2;
        return p90Var2;
    }

    public final void f(q90 q90Var) {
        q90 q90Var2 = q90Var.F;
        q90 q90Var3 = q90Var.G;
        q90 q90Var4 = q90Var2.F;
        q90 q90Var5 = q90Var2.G;
        q90Var.F = q90Var5;
        if (q90Var5 != null) {
            q90Var5.E = q90Var;
        }
        d(q90Var, q90Var2);
        q90Var2.G = q90Var;
        q90Var.E = q90Var2;
        int iMax = Math.max(q90Var3 != null ? q90Var3.L : 0, q90Var5 != null ? q90Var5.L : 0) + 1;
        q90Var.L = iMax;
        q90Var2.L = Math.max(iMax, q90Var4 != null ? q90Var4.L : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        q90 q90VarA;
        if (obj != null) {
            try {
                q90VarA = a(obj, false);
            } catch (ClassCastException unused) {
                q90VarA = null;
            }
        } else {
            q90VarA = null;
        }
        if (q90VarA != null) {
            return (V) q90VarA.K;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        p90 p90Var = this.K;
        if (p90Var != null) {
            return p90Var;
        }
        p90 p90Var2 = new p90(this, 1);
        this.K = p90Var2;
        return p90Var2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        q90 q90VarA = a(k, true);
        V v2 = (V) q90VarA.K;
        q90VarA.K = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        q90 q90VarA;
        if (obj != null) {
            try {
                q90VarA = a(obj, false);
            } catch (ClassCastException unused) {
                q90VarA = null;
            }
        } else {
            q90VarA = null;
        }
        if (q90VarA != null) {
            c(q90VarA, true);
        }
        if (q90VarA != null) {
            return (V) q90VarA.K;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.G;
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.G = 0;
        this.H = 0;
        this.I = new q90();
        this.E = comparator == null ? L : comparator;
    }
}
