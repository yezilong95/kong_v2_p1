package kotlin.collections;

import defpackage.pu;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements pu {
    public final /* synthetic */ pu b;
    public final /* synthetic */ Comparable c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionsKt__CollectionsKt$binarySearchBy$1(pu puVar, Comparable<Object> comparable) {
        super(1);
        this.b = puVar;
        this.c = comparable;
    }

    @Override // defpackage.pu
    public final Integer invoke(Object obj) {
        Comparable comparable = (Comparable) this.b.invoke(obj);
        Comparable comparable2 = this.c;
        return Integer.valueOf(comparable == comparable2 ? 0 : comparable == null ? -1 : comparable2 == null ? 1 : comparable.compareTo(comparable2));
    }
}
