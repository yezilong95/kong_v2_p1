package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class i20 implements Iterator {
    public int c;
    public Map.Entry d;
    public final /* synthetic */ AbstractMap f;
    public final /* synthetic */ int b = 0;
    public Map.Entry e = null;

    public i20(LinkedTreeMap linkedTreeMap) {
        this.f = linkedTreeMap;
        this.d = linkedTreeMap.f.e;
        this.c = linkedTreeMap.e;
    }

    public j20 a() {
        j20 j20Var = (j20) this.d;
        LinkedHashTreeMap linkedHashTreeMap = (LinkedHashTreeMap) this.f;
        if (j20Var == linkedHashTreeMap.d) {
            throw new NoSuchElementException();
        }
        if (linkedHashTreeMap.f != this.c) {
            throw new ConcurrentModificationException();
        }
        this.d = j20Var.e;
        this.e = j20Var;
        return j20Var;
    }

    public m20 b() {
        m20 m20Var = (m20) this.d;
        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.f;
        if (m20Var == linkedTreeMap.f) {
            throw new NoSuchElementException();
        }
        if (linkedTreeMap.e != this.c) {
            throw new ConcurrentModificationException();
        }
        this.d = m20Var.e;
        this.e = m20Var;
        return m20Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.b) {
            case 0:
                if (((j20) this.d) != ((LinkedHashTreeMap) this.f).d) {
                }
                break;
            default:
                if (((m20) this.d) != ((LinkedTreeMap) this.f).f) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.b) {
            case 0:
                return a();
            default:
                return b();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.b) {
            case 0:
                j20 j20Var = (j20) this.e;
                if (j20Var == null) {
                    throw new IllegalStateException();
                }
                LinkedHashTreeMap linkedHashTreeMap = (LinkedHashTreeMap) this.f;
                linkedHashTreeMap.c(j20Var, true);
                this.e = null;
                this.c = linkedHashTreeMap.f;
                return;
            default:
                m20 m20Var = (m20) this.e;
                if (m20Var == null) {
                    throw new IllegalStateException();
                }
                LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.f;
                linkedTreeMap.c(m20Var, true);
                this.e = null;
                this.c = linkedTreeMap.e;
                return;
        }
    }

    public i20(LinkedHashTreeMap linkedHashTreeMap) {
        this.f = linkedHashTreeMap;
        this.d = linkedHashTreeMap.d.e;
        this.c = linkedHashTreeMap.f;
    }
}
