package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class m40 implements Iterator, Map.Entry {
    public int b;
    public final /* synthetic */ ux e;
    public boolean d = false;
    public int c = -1;

    public m40(ux uxVar) {
        this.e = uxVar;
        this.b = uxVar.e() - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.d) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i = this.c;
        ux uxVar = this.e;
        Object objC = uxVar.c(i, 0);
        if (key != objC && (key == null || !key.equals(objC))) {
            return false;
        }
        Object value = entry.getValue();
        Object objC2 = uxVar.c(this.c, 1);
        return value == objC2 || (value != null && value.equals(objC2));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (!this.d) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.e.c(this.c, 0);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (!this.d) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.e.c(this.c, 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.d) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i = this.c;
        ux uxVar = this.e;
        Object objC = uxVar.c(i, 0);
        Object objC2 = uxVar.c(this.c, 1);
        return (objC == null ? 0 : objC.hashCode()) ^ (objC2 != null ? objC2.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.c++;
        this.d = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.d) {
            throw new IllegalStateException();
        }
        this.e.i(this.c);
        this.c--;
        this.b--;
        this.d = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.d) {
            return this.e.j(this.c, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
