package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class i implements Iterator, wz {
    public final /* synthetic */ int b = 2;
    public int c;
    public final Object d;

    public i(Object[] objArr) {
        oy.j(objArr, "array");
        this.d = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Iterator it;
        switch (this.b) {
            case 0:
                return this.c < ((l) this.d).a();
            case 1:
                return this.c < ((Object[]) this.d).length;
        }
        while (true) {
            int i = this.c;
            it = (Iterator) this.d;
            if (i > 0 && it.hasNext()) {
                it.next();
                this.c--;
            }
        }
        return it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Iterator it;
        switch (this.b) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i = this.c;
                this.c = i + 1;
                return ((l) this.d).get(i);
            case 1:
                try {
                    Object[] objArr = (Object[]) this.d;
                    int i2 = this.c;
                    this.c = i2 + 1;
                    return objArr[i2];
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.c--;
                    throw new NoSuchElementException(e.getMessage());
                }
        }
        while (true) {
            int i3 = this.c;
            it = (Iterator) this.d;
            if (i3 > 0 && it.hasNext()) {
                it.next();
                this.c--;
            }
        }
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public i(l lVar) {
        this.d = lVar;
    }

    public i(dm dmVar) {
        this.d = dmVar.a.iterator();
        this.c = dmVar.b;
    }
}
