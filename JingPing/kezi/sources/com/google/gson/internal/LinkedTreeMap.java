package com.google.gson.internal;

import defpackage.l20;
import defpackage.l3;
import defpackage.m20;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final l3 i = new l3(6);
    public final Comparator b;
    public m20 c;
    public int d;
    public int e;
    public final m20 f;
    public l20 g;
    public l20 h;

    public LinkedTreeMap() {
        this(i);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final m20 a(Object obj, boolean z) {
        int iCompareTo;
        m20 m20Var;
        m20 m20Var2 = this.c;
        l3 l3Var = i;
        Comparator comparator = this.b;
        if (m20Var2 != null) {
            Comparable comparable = comparator == l3Var ? (Comparable) obj : null;
            while (true) {
                Object obj2 = m20Var2.g;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return m20Var2;
                }
                m20 m20Var3 = iCompareTo < 0 ? m20Var2.c : m20Var2.d;
                if (m20Var3 == null) {
                    break;
                }
                m20Var2 = m20Var3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        m20 m20Var4 = this.f;
        if (m20Var2 != null) {
            m20Var = new m20(m20Var2, obj, m20Var4, m20Var4.f);
            if (iCompareTo < 0) {
                m20Var2.c = m20Var;
            } else {
                m20Var2.d = m20Var;
            }
            b(m20Var2, true);
        } else {
            if (comparator == l3Var && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            m20Var = new m20(m20Var2, obj, m20Var4, m20Var4.f);
            this.c = m20Var;
        }
        this.d++;
        this.e++;
        return m20Var;
    }

    public final void b(m20 m20Var, boolean z) {
        while (m20Var != null) {
            m20 m20Var2 = m20Var.c;
            m20 m20Var3 = m20Var.d;
            int i2 = m20Var2 != null ? m20Var2.i : 0;
            int i3 = m20Var3 != null ? m20Var3.i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                m20 m20Var4 = m20Var3.c;
                m20 m20Var5 = m20Var3.d;
                int i5 = (m20Var4 != null ? m20Var4.i : 0) - (m20Var5 != null ? m20Var5.i : 0);
                if (i5 == -1 || (i5 == 0 && !z)) {
                    e(m20Var);
                } else {
                    f(m20Var3);
                    e(m20Var);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                m20 m20Var6 = m20Var2.c;
                m20 m20Var7 = m20Var2.d;
                int i6 = (m20Var6 != null ? m20Var6.i : 0) - (m20Var7 != null ? m20Var7.i : 0);
                if (i6 == 1 || (i6 == 0 && !z)) {
                    f(m20Var);
                } else {
                    e(m20Var2);
                    f(m20Var);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                m20Var.i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                m20Var.i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            m20Var = m20Var.b;
        }
    }

    public final void c(m20 m20Var, boolean z) {
        m20 m20Var2;
        m20 m20Var3;
        int i2;
        if (z) {
            m20 m20Var4 = m20Var.f;
            m20Var4.e = m20Var.e;
            m20Var.e.f = m20Var4;
        }
        m20 m20Var5 = m20Var.c;
        m20 m20Var6 = m20Var.d;
        m20 m20Var7 = m20Var.b;
        int i3 = 0;
        if (m20Var5 == null || m20Var6 == null) {
            if (m20Var5 != null) {
                d(m20Var, m20Var5);
                m20Var.c = null;
            } else if (m20Var6 != null) {
                d(m20Var, m20Var6);
                m20Var.d = null;
            } else {
                d(m20Var, null);
            }
            b(m20Var7, false);
            this.d--;
            this.e++;
            return;
        }
        if (m20Var5.i > m20Var6.i) {
            m20 m20Var8 = m20Var5.d;
            while (true) {
                m20 m20Var9 = m20Var8;
                m20Var3 = m20Var5;
                m20Var5 = m20Var9;
                if (m20Var5 == null) {
                    break;
                } else {
                    m20Var8 = m20Var5.d;
                }
            }
        } else {
            m20 m20Var10 = m20Var6.c;
            while (true) {
                m20Var2 = m20Var6;
                m20Var6 = m20Var10;
                if (m20Var6 == null) {
                    break;
                } else {
                    m20Var10 = m20Var6.c;
                }
            }
            m20Var3 = m20Var2;
        }
        c(m20Var3, false);
        m20 m20Var11 = m20Var.c;
        if (m20Var11 != null) {
            i2 = m20Var11.i;
            m20Var3.c = m20Var11;
            m20Var11.b = m20Var3;
            m20Var.c = null;
        } else {
            i2 = 0;
        }
        m20 m20Var12 = m20Var.d;
        if (m20Var12 != null) {
            i3 = m20Var12.i;
            m20Var3.d = m20Var12;
            m20Var12.b = m20Var3;
            m20Var.d = null;
        }
        m20Var3.i = Math.max(i2, i3) + 1;
        d(m20Var, m20Var3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.c = null;
        this.d = 0;
        this.e++;
        m20 m20Var = this.f;
        m20Var.f = m20Var;
        m20Var.e = m20Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        m20 m20VarA = null;
        if (obj != null) {
            try {
                m20VarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return m20VarA != null;
    }

    public final void d(m20 m20Var, m20 m20Var2) {
        m20 m20Var3 = m20Var.b;
        m20Var.b = null;
        if (m20Var2 != null) {
            m20Var2.b = m20Var3;
        }
        if (m20Var3 == null) {
            this.c = m20Var2;
        } else if (m20Var3.c == m20Var) {
            m20Var3.c = m20Var2;
        } else {
            m20Var3.d = m20Var2;
        }
    }

    public final void e(m20 m20Var) {
        m20 m20Var2 = m20Var.c;
        m20 m20Var3 = m20Var.d;
        m20 m20Var4 = m20Var3.c;
        m20 m20Var5 = m20Var3.d;
        m20Var.d = m20Var4;
        if (m20Var4 != null) {
            m20Var4.b = m20Var;
        }
        d(m20Var, m20Var3);
        m20Var3.c = m20Var;
        m20Var.b = m20Var3;
        int iMax = Math.max(m20Var2 != null ? m20Var2.i : 0, m20Var4 != null ? m20Var4.i : 0) + 1;
        m20Var.i = iMax;
        m20Var3.i = Math.max(iMax, m20Var5 != null ? m20Var5.i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        l20 l20Var = this.g;
        if (l20Var != null) {
            return l20Var;
        }
        l20 l20Var2 = new l20(this, 0);
        this.g = l20Var2;
        return l20Var2;
    }

    public final void f(m20 m20Var) {
        m20 m20Var2 = m20Var.c;
        m20 m20Var3 = m20Var.d;
        m20 m20Var4 = m20Var2.c;
        m20 m20Var5 = m20Var2.d;
        m20Var.c = m20Var5;
        if (m20Var5 != null) {
            m20Var5.b = m20Var;
        }
        d(m20Var, m20Var2);
        m20Var2.d = m20Var;
        m20Var.b = m20Var2;
        int iMax = Math.max(m20Var3 != null ? m20Var3.i : 0, m20Var5 != null ? m20Var5.i : 0) + 1;
        m20Var.i = iMax;
        m20Var2.i = Math.max(iMax, m20Var4 != null ? m20Var4.i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        m20 m20VarA;
        if (obj != null) {
            try {
                m20VarA = a(obj, false);
            } catch (ClassCastException unused) {
                m20VarA = null;
            }
        } else {
            m20VarA = null;
        }
        if (m20VarA != null) {
            return (V) m20VarA.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        l20 l20Var = this.h;
        if (l20Var != null) {
            return l20Var;
        }
        l20 l20Var2 = new l20(this, 1);
        this.h = l20Var2;
        return l20Var2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        m20 m20VarA = a(k, true);
        V v2 = (V) m20VarA.h;
        m20VarA.h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        m20 m20VarA;
        if (obj != null) {
            try {
                m20VarA = a(obj, false);
            } catch (ClassCastException unused) {
                m20VarA = null;
            }
        } else {
            m20VarA = null;
        }
        if (m20VarA != null) {
            c(m20VarA, true);
        }
        if (m20VarA != null) {
            return (V) m20VarA.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.d;
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.d = 0;
        this.e = 0;
        this.f = new m20();
        this.b = comparator == null ? i : comparator;
    }
}
