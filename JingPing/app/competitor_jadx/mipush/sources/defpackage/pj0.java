package defpackage;

import java.util.concurrent.Executors;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pj0 implements Supplier {
    @Override // java.util.function.Supplier
    public final Object get() {
        return Executors.newSingleThreadExecutor();
    }
}
