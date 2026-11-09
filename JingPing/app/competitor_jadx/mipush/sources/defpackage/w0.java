package defpackage;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class w0 extends AbstractList implements List {
    public abstract int getSize();

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i) {
        return removeAt(i);
    }

    public abstract Object removeAt(int i);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
