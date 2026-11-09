package kotlin;

import defpackage.t40;
import defpackage.tm;
import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
public final class NotImplementedError extends Error {
    /* JADX WARN: Multi-variable type inference failed */
    public NotImplementedError() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotImplementedError(String str) {
        super(str);
        t40.n(str, ApiConstants.MESSAGE);
    }

    public /* synthetic */ NotImplementedError(String str, int i, tm tmVar) {
        this((i & 1) != 0 ? "An operation is not implemented." : str);
    }
}
