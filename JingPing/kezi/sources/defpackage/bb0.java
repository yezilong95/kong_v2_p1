package defpackage;

import java.util.RandomAccess;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class bb0 extends l implements RandomAccess {
    public final ByteString[] b;
    public final int[] c;

    public bb0(ByteString[] byteStringArr, int[] iArr) {
        this.b = byteStringArr;
        this.c = iArr;
    }

    @Override // defpackage.l
    public final int a() {
        return this.b.length;
    }

    @Override // defpackage.l, java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return super.contains((ByteString) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.b[i];
    }

    @Override // defpackage.l, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.indexOf((ByteString) obj);
        }
        return -1;
    }

    @Override // defpackage.l, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.lastIndexOf((ByteString) obj);
        }
        return -1;
    }
}
