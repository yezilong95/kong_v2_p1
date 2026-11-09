package kotlin.collections;

import defpackage.b10;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements b10 {
    public final /* synthetic */ b10 E;
    public final /* synthetic */ Comparable F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionsKt__CollectionsKt$binarySearchBy$1(b10 b10Var, Comparable<Object> comparable) {
        super(1);
        this.E = b10Var;
        this.F = comparable;
    }

    @Override // defpackage.b10
    public final Integer invoke(Object obj) {
        Comparable comparable = (Comparable) this.E.invoke(obj);
        Comparable comparable2 = this.F;
        return Integer.valueOf(comparable == comparable2 ? 0 : comparable == null ? -1 : comparable2 == null ? 1 : comparable.compareTo(comparable2));
    }
}
