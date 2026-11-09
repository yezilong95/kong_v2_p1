package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class m90 implements Iterator {
    public int F;
    public Map.Entry G;
    public final /* synthetic */ AbstractMap I;
    public final /* synthetic */ int E = 0;
    public Map.Entry H = null;

    public m90(LinkedTreeMap linkedTreeMap) {
        this.I = linkedTreeMap;
        this.G = linkedTreeMap.I.H;
        this.F = linkedTreeMap.H;
    }

    public n90 a() {
        n90 n90Var = (n90) this.G;
        LinkedHashTreeMap linkedHashTreeMap = (LinkedHashTreeMap) this.I;
        if (n90Var == linkedHashTreeMap.G) {
            throw new NoSuchElementException();
        }
        if (linkedHashTreeMap.I != this.F) {
            throw new ConcurrentModificationException();
        }
        this.G = n90Var.H;
        this.H = n90Var;
        return n90Var;
    }

    public q90 b() {
        q90 q90Var = (q90) this.G;
        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.I;
        if (q90Var == linkedTreeMap.I) {
            throw new NoSuchElementException();
        }
        if (linkedTreeMap.H != this.F) {
            throw new ConcurrentModificationException();
        }
        this.G = q90Var.H;
        this.H = q90Var;
        return q90Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.E) {
            case 0:
                if (((n90) this.G) != ((LinkedHashTreeMap) this.I).G) {
                }
                break;
            default:
                if (((q90) this.G) != ((LinkedTreeMap) this.I).I) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.E) {
            case 0:
                return a();
            default:
                return b();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.E) {
            case 0:
                n90 n90Var = (n90) this.H;
                if (n90Var == null) {
                    throw new IllegalStateException();
                }
                LinkedHashTreeMap linkedHashTreeMap = (LinkedHashTreeMap) this.I;
                linkedHashTreeMap.c(n90Var, true);
                this.H = null;
                this.F = linkedHashTreeMap.I;
                return;
            default:
                q90 q90Var = (q90) this.H;
                if (q90Var == null) {
                    throw new IllegalStateException();
                }
                LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.I;
                linkedTreeMap.c(q90Var, true);
                this.H = null;
                this.F = linkedTreeMap.H;
                return;
        }
    }

    public m90(LinkedHashTreeMap linkedHashTreeMap) {
        this.I = linkedHashTreeMap;
        this.G = linkedHashTreeMap.G.H;
        this.F = linkedHashTreeMap.I;
    }
}
