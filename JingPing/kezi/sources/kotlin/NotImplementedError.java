package kotlin;

import defpackage.aj;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class NotImplementedError extends Error {
    public NotImplementedError() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotImplementedError(String str) {
        super(str);
        oy.j(str, "message");
    }

    public /* synthetic */ NotImplementedError(String str, int i, aj ajVar) {
        this((i & 1) != 0 ? "An operation is not implemented." : str);
    }
}
