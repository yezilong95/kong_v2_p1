package defpackage;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class vj extends ContentObserver {
    public final /* synthetic */ xx0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public vj(xx0 xx0Var) {
        super(new Handler());
        this.a = xx0Var;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return true;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        Cursor cursor;
        xx0 xx0Var = this.a;
        if (!xx0Var.F || (cursor = xx0Var.G) == null || cursor.isClosed()) {
            return;
        }
        xx0Var.E = xx0Var.G.requery();
    }
}
