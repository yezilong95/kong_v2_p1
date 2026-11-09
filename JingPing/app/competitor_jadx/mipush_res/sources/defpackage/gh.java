package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class gh extends y0 {
    public final Lambda E;
    public final /* synthetic */ hh F;

    /* JADX WARN: Multi-variable type inference failed */
    public gh(hh hhVar, d10 d10Var) {
        this.F = hhVar;
        this.E = (Lambda) d10Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override // defpackage.y0
    public final int getSize() {
        return this.F.size();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [d10, kotlin.jvm.internal.Lambda] */
    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        eh ehVar = (eh) hh.G.get(this.F);
        ehVar.getClass();
        return new dh(ehVar, this.E);
    }
}
