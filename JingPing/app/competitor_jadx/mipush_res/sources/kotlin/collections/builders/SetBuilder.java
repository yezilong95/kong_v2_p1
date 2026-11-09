package kotlin.collections.builders;

import defpackage.t40;
import defpackage.y0;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class SetBuilder<E> extends y0 implements Set<E>, Serializable {
    public static final SetBuilder F;
    public final MapBuilder E;

    static {
        MapBuilder.Companion.getClass();
        F = new SetBuilder(MapBuilder.R);
    }

    public SetBuilder(MapBuilder<E, ?> mapBuilder) {
        t40.n(mapBuilder, "backing");
        this.E = mapBuilder;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.E.isReadOnly$kotlin_stdlib()) {
            return new SerializedCollection(this, 1);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.E.addKey$kotlin_stdlib(e) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        t40.n(collection, "elements");
        this.E.checkIsMutable$kotlin_stdlib();
        return super.addAll(collection);
    }

    public final Set<E> build() {
        this.E.build();
        return size() > 0 ? this : F;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.E.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.E.containsKey(obj);
    }

    @Override // defpackage.y0
    public int getSize() {
        return this.E.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.E.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.E.keysIterator$kotlin_stdlib();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.E.removeKey$kotlin_stdlib(obj);
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> collection) {
        t40.n(collection, "elements");
        this.E.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> collection) {
        t40.n(collection, "elements");
        this.E.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int i) {
        this(new MapBuilder(i));
    }
}
