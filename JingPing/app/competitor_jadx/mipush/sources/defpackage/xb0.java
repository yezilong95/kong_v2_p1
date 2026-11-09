package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class xb0 extends y0 {
    public final /* synthetic */ int E;
    public final MapBuilder F;

    public /* synthetic */ xb0(MapBuilder mapBuilder, int i) {
        this.E = i;
        this.F = mapBuilder;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.E) {
            case 0:
                t40.n((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.E) {
            case 0:
                t40.n(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                t40.n(collection, "elements");
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.E) {
            case 0:
                this.F.clear();
                break;
            default:
                this.F.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.E) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                t40.n(entry, "element");
                return this.F.containsEntry$kotlin_stdlib(entry);
            default:
                return this.F.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.E) {
            case 0:
                t40.n(collection, "elements");
                return this.F.containsAllEntries$kotlin_stdlib(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // defpackage.y0
    public final int getSize() {
        switch (this.E) {
        }
        return this.F.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.E) {
        }
        return this.F.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.E) {
            case 0:
                return this.F.entriesIterator$kotlin_stdlib();
            default:
                return this.F.keysIterator$kotlin_stdlib();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.E) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                t40.n(entry, "element");
                return this.F.removeEntry$kotlin_stdlib(entry);
            default:
                return this.F.removeKey$kotlin_stdlib(obj);
        }
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.E) {
            case 0:
                t40.n(collection, "elements");
                this.F.checkIsMutable$kotlin_stdlib();
                break;
            default:
                t40.n(collection, "elements");
                this.F.checkIsMutable$kotlin_stdlib();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.E) {
            case 0:
                t40.n(collection, "elements");
                this.F.checkIsMutable$kotlin_stdlib();
                break;
            default:
                t40.n(collection, "elements");
                this.F.checkIsMutable$kotlin_stdlib();
                break;
        }
        return super.retainAll(collection);
    }
}
