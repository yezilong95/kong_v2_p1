package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class i40 extends o {
    public final /* synthetic */ int b;
    public final MapBuilder c;

    public /* synthetic */ i40(MapBuilder mapBuilder, int i) {
        this.b = i;
        this.c = mapBuilder;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.b) {
            case 0:
                oy.j((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.b) {
            case 0:
                oy.j(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                oy.j(collection, "elements");
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.b) {
            case 0:
                this.c.clear();
                break;
            default:
                this.c.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.b) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                oy.j(entry, "element");
                return this.c.containsEntry$kotlin_stdlib(entry);
            default:
                return this.c.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.b) {
            case 0:
                oy.j(collection, "elements");
                return this.c.containsAllEntries$kotlin_stdlib(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // defpackage.o
    public final int getSize() {
        switch (this.b) {
        }
        return this.c.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.b) {
        }
        return this.c.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.b) {
            case 0:
                return this.c.entriesIterator$kotlin_stdlib();
            default:
                return this.c.keysIterator$kotlin_stdlib();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.b) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                oy.j(entry, "element");
                return this.c.removeEntry$kotlin_stdlib(entry);
            default:
                return this.c.removeKey$kotlin_stdlib(obj);
        }
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.b) {
            case 0:
                oy.j(collection, "elements");
                this.c.checkIsMutable$kotlin_stdlib();
                break;
            default:
                oy.j(collection, "elements");
                this.c.checkIsMutable$kotlin_stdlib();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.b) {
            case 0:
                oy.j(collection, "elements");
                this.c.checkIsMutable$kotlin_stdlib();
                break;
            default:
                oy.j(collection, "elements");
                this.c.checkIsMutable$kotlin_stdlib();
                break;
        }
        return super.retainAll(collection);
    }
}
