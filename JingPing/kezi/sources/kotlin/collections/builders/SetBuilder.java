package kotlin.collections.builders;

import defpackage.o;
import defpackage.oy;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class SetBuilder<E> extends o implements Set<E>, Serializable {
    public static final SetBuilder c;
    public final MapBuilder b;

    static {
        MapBuilder.Companion.getClass();
        c = new SetBuilder(MapBuilder.o);
    }

    public SetBuilder(MapBuilder<E, ?> mapBuilder) {
        oy.j(mapBuilder, "backing");
        this.b = mapBuilder;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.b.isReadOnly$kotlin_stdlib()) {
            return new SerializedCollection(this, 1);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.b.addKey$kotlin_stdlib(e) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        oy.j(collection, "elements");
        this.b.checkIsMutable$kotlin_stdlib();
        return super.addAll(collection);
    }

    public final Set<E> build() {
        this.b.build();
        return size() > 0 ? this : c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // defpackage.o
    public int getSize() {
        return this.b.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.b.keysIterator$kotlin_stdlib();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.b.removeKey$kotlin_stdlib(obj);
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> collection) {
        oy.j(collection, "elements");
        this.b.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> collection) {
        oy.j(collection, "elements");
        this.b.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int i) {
        this(new MapBuilder(i));
    }
}
