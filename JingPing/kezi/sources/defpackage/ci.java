package defpackage;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class ci extends ContentObserver {
    public final /* synthetic */ jl0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ci(jl0 jl0Var) {
        super(new Handler());
        this.a = jl0Var;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return true;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        Cursor cursor;
        jl0 jl0Var = this.a;
        if (!jl0Var.c || (cursor = jl0Var.d) == null || cursor.isClosed()) {
            return;
        }
        jl0Var.b = jl0Var.d.requery();
    }
}
