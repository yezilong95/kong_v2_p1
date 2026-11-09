package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class dm implements xh0 {
    public final xh0 a;
    public final int b;

    public dm(xh0 xh0Var, int i) {
        this.a = xh0Var;
        this.b = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
    }

    @Override // defpackage.xh0
    public final Iterator iterator() {
        return new i(this);
    }
}
